package com.test.util;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * @author js.ding
 * @Title: BocCertificate
 * @ProjectName bc-authorize
 * @Description: TODO
 * @date 2019/4/315:06
 */
public class BocCertificate {
    public static final String RSA_PADDING_TYPE = "RSA/ECB/PKCS1Padding";
    private static final String digestAlgorithm = "SHA256";
    private static final String signingAlgorithm = "SHA256withRSA";


    private static boolean matchUsage(boolean[] keyUsage, int usage) {
        if (usage != 0 && keyUsage != null) {
            for(int i = 0; i < Math.min(keyUsage.length, 32); ++i) {
                if ((usage & 1 << i) != 0 && !keyUsage[i]) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }

    public static Object[] initSigner(String keyStorePath, String keyStorePassword, String keyPassword) throws Exception {
        KeyStore keyStore = null;
        if (keyStorePath.toLowerCase().endsWith(".p12")) {
            keyStore = KeyStore.getInstance("PKCS12");
        } else {
            keyStore = KeyStore.getInstance("JKS");
        }

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(keyStorePath);
            keyStore.load(fis, keyStorePassword.toCharArray());
        } finally {
            if (fis != null) {
                fis.close();
            }

        }

        Enumeration<String> aliases = keyStore.aliases();
        String keyAlias = null;
        if (aliases != null) {
            label171:
            while(true) {
                X509Certificate cert;
                do {
                    Certificate[] certs;
                    do {
                        do {
                            if (!aliases.hasMoreElements()) {
                                break label171;
                            }

                            keyAlias = (String)aliases.nextElement();
                            certs = keyStore.getCertificateChain(keyAlias);
                        } while(certs == null);
                    } while(certs.length == 0);

                    cert = (X509Certificate)certs[0];
                } while(!matchUsage(cert.getKeyUsage(), 1));

                try {
                    cert.checkValidity();
                    break;
                } catch (CertificateException var14) {
                    ;
                }
            }
        }

        Object[] objects = new Object[2];
        X509Certificate[] certificates = null;
        if (keyStore.isKeyEntry(keyAlias)) {
            Certificate[] certs = keyStore.getCertificateChain(keyAlias);

            int i;
            for(i = 0; i < certs.length; ++i) {
                if (!(certs[i] instanceof X509Certificate)) {
                    throw new IllegalAccessError("证书链接所指的证书不符合x509格式！");
                }
            }

            certificates = new X509Certificate[certs.length];

            for(i = 0; i < certs.length; ++i) {
                certificates[i] = (X509Certificate)certs[i];
            }
        } else {
            if (!keyStore.isCertificateEntry(keyAlias)) {
                throw new IllegalAccessError("keystore存储的证书不合法！");
            }

            Certificate cert = keyStore.getCertificate(keyAlias);
            if (cert instanceof X509Certificate) {
                certificates = new X509Certificate[]{(X509Certificate)cert};
            }
        }

        objects[0] = (PrivateKey)keyStore.getKey(keyAlias, keyPassword.toCharArray());
        objects[1] = certificates;
        return objects;
    }

    public static Certificate initVerifier(String publicKeyCertificatePath) throws Exception {
        FileInputStream fis = null;
        Certificate publicKeyCertificate = null;

        try {
            fis = new FileInputStream(publicKeyCertificatePath);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");

            try {
                publicKeyCertificate = certificateFactory.generateCertificate(fis);
            } catch (Exception var9) {
                InputStream is = new ByteArrayInputStream((new BASE64Decoder()).decodeBuffer(fis));
                publicKeyCertificate = certificateFactory.generateCertificate(is);
            }
        } finally {
            if (fis != null) {
                fis.close();
            }

        }

        return publicKeyCertificate;
    }

    public static byte[] encryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, publicKey);
        return cipher.doFinal(data);
    }

//    public static byte[] decryptByPrivateKey(String cipherText, PrivateKey privateKey) throws Exception {
//        byte[] sKey = Conver.decodeBase64(Conver.replaceBaseStr(cipherText));
//        Cipher CIPHER = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//        CIPHER.init(2, privateKey);
//        return CIPHER.doFinal(sKey);
//    }
//
//    public static void p7DetacheVerifyMsg(String signature, String plainText, String dn) throws Exception {
//        byte[] sign = Conver.decodeBase64(Conver.replaceBaseStr(signature));
//        byte[] data = Conver.decodeBase64(Conver.replaceBaseStr(plainText));
//        PKCS7 p7 = new PKCS7(sign);
//        SignerInfo[] sis = null;
//
//        try {
//            sis = p7.verify(data);
//        } catch (Exception var12) {
//            var12.printStackTrace();
//        }
//
//        if (sis == null) {
//            throw new IllegalAccessError("keystore存储的证书不合法！");
//        } else {
//            Certificate publicKeyCertificate = initVerifier("D:\\BOCjm11.cer");
//
//            try {
//                for(int i = 0; i < sis.length; ++i) {
//                    SignerInfo si = sis[i];
//                    X509Certificate cert = si.getCertificate(p7);
//                    cert.checkValidity();
//                    if (!cert.equals(publicKeyCertificate)) {
//                        cert.verify(publicKeyCertificate.getPublicKey());
//                    }
//
//                    if (i == 0 && dn != null) {
//                        X500Principal name = cert.getSubjectX500Principal();
//                        if (!dn.equals(name.getName("RFC1779")) && !(new X500Principal(dn)).equals(name)) {
//                            throw new SignatureException("签名证书" + name.getName("RFC1779") + "不匹配[" + dn + "]");
//                        }
//                    }
//                }
//            } catch (Exception var13) {
//                var13.printStackTrace();
//            }
//
//        }
//    }
//
//    public static String p7DetacheSignMsg(String plainText, PrivateKey privateKey, X509Certificate[] certificates) throws Exception {
//        byte[] data = Conver.decodeBase64(plainText);
//        Signature signature = Signature.getInstance("SHA256withRSA");
//        signature.initSign(privateKey);
//        signature.update(data, 0, data.length);
//        byte[] signedAttributes = signature.sign();
//        X509Certificate publicKey = certificates[0];
//        SignerInfo signerInfo = new SignerInfo(new X500Name(publicKey.getIssuerX500Principal().getName()), publicKey.getSerialNumber(), AlgorithmId.get("SHA256"), AlgorithmId.get(privateKey.getAlgorithm()), signedAttributes);
//        PKCS7 pkcs7 = new PKCS7(new AlgorithmId[]{AlgorithmId.get("SHA256")}, new ContentInfo(ContentInfo.DATA_OID, (DerValue)null), new X509Certificate[]{publicKey}, new SignerInfo[]{signerInfo});
//        ByteArrayOutputStream bout = new ByteArrayOutputStream();
//        pkcs7.encodeSignedData(bout);
//        String p7Siger = (new BASE64Encoder()).encode(bout.toByteArray());
//        return p7Siger;
//    }

    public static String encodeBase64(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    public static byte[] decodeBase64(String conent) {
        Object var1 = null;

        byte[] bytes;
        try {
            bytes = conent.getBytes("utf-8");
        } catch (UnsupportedEncodingException var3) {
            bytes = conent.getBytes(Charset.defaultCharset());
        }

        bytes = Base64.decodeBase64(bytes);
        return bytes;
    }
}
