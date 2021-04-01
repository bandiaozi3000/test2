//package com.test.util;
//
//import cfca.sadk.algorithm.common.PKIException;
//import cfca.sadk.cgb.toolkit.BASE64Toolkit;
//import cfca.sadk.cgb.toolkit.SM2Toolkit;
//
//import java.io.FileInputStream;
//import java.io.InputStream;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.util.Map;
//
///**
// *
// * 日期 : 2016年6月29日<br>
// * 作者 : 11150321050126<br>
// * 项目 : TestEncrypt<br>
// * 功能 : 工具类<br>
// */
//@SuppressWarnings("rawtypes")
//public class Utils {
//	private static SM2Toolkit sm2Toolkit = null;
//
//	/**
//	 *
//	 * Description : 生成单例的sm2工具
//	 *
//	 * @return
//	 */
//	public synchronized static SM2Toolkit getSM2Toolkit() {
//		if (sm2Toolkit == null) {
//			synchronized (Utils.class) {
//				if (sm2Toolkit == null) {
//					sm2Toolkit = new SM2Toolkit();
//				}
//			}
//		}
//		return sm2Toolkit;
//	}
//
//	/**
//	 *
//	 * Description : 读取公钥
//	 *
//	 * @param path
//	 * @return
//	 */
//	public static PublicKey readPublicKey(String path) {
//		PublicKey puk;
//		try {
//			puk = getSM2Toolkit().SM2BuildPublicKey(BASE64Toolkit.encode(read(path)));
//		} catch (PKIException e) {
//			throw new RuntimeException(e);
//		}
//		return puk;
//	}
//
//	/**
//	 *
//	 * Description : 读取私钥
//	 *
//	 * @param path
//	 * @return
//	 */
//	public static PrivateKey readPrivateKey(String path) {
//		PrivateKey pvk;
//		try {
//			pvk = getSM2Toolkit().SM2BuildPrivateKey(BASE64Toolkit.encode(read(path)));
//		} catch (PKIException e) {
//			throw new RuntimeException(e);
//		}
//		return pvk;
//	}
//
//	/**
//	 * @throws
//	 *
//	 * Description : 合成sign的字符串
//	 *
//	 * @param bean
//	 * @return
//	 * @throws
//	 */
//	public static String getSign(Map map) {
//		try {
//			if (map == null) {
//				throw new RuntimeException("获取sign时，bean不可为空");
//			}
//			String[] names = { "msgtype", "format", "version", "shopid", "timestamp", "method", "message" };
//			StringBuffer sb = new StringBuffer();
//			for (int i = 0; i < names.length; i++) {
//				sb.append(names[i]);
////				Field field = clazz.getDeclaredField(names[i]);
////				field.setAccessible(true);
//				Object obj = map.get(names[i]);
//				if (obj == null ) {
////					throw new RuntimeException(names[i]+" is null ");
//					obj = "notifymessage";
//				} else {
//					sb.append(String.valueOf(obj));
//				}
//			}
//			return sb.toString();
//		} catch (SecurityException | IllegalArgumentException  e) {
//			throw new RuntimeException(e);
//		}//|NoSuchFieldException |IllegalAccessException
//	}
//
//	/**
//	 *
//	 * Description : 读取文件
//	 *
//	 * @param keyPath
//	 * @return
//	 */
//	private static byte[] read(String keyPath) {
//		try {
//			InputStream inputStream =  new FileInputStream(keyPath);
//			byte result[] = new byte[inputStream.available()];
//			byte buffer[] = new byte[1024];
//			for (int length = 0, k = 0; (k = inputStream.read(buffer)) != -1; length += k) {
//				System.arraycopy(buffer, 0, result, length, k);
//			}
//			inputStream.close();
//			return result;
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//}
