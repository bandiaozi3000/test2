package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import com.test.aspect.MyAspect;
import com.test.bean.*;
import com.test.controller.ExceptionController;
import com.test.controller.TestController;
import com.test.exception.ExceptionWrapper;
import com.test.filter.MyAfterFilter;
import com.test.filter.MyValueFilter;
import com.test.filter.ProperFilter;
import com.test.method.MyTestMethod;
import com.test.service.AsyncService;
import com.test.service.HystrixService;
import com.test.service.MailService;
import com.test.service.TestInterface;
import com.test.service.impl.TestInterfaceImpl;
import com.test.util.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class TestApplicationTests {

    @Autowired
    MyAspect myAspect;

    @Autowired
    MyTestMethod myTestMethod;

    @Autowired
    HystrixService hystrixService;

    @Autowired
    private TestInterface testInterface;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private TestController testController;

    @Autowired
    private MailService mailService;




    /**
     * AfterFilter和BeforeFilter区别:
     * AfterFilter:在序列化之后操作
     * BeforeFilter:在序列化之前操作
     */
    @Test
    public void contextLoads() {
        List<People> list = new ArrayList<>();
        People p1 = new People(1, "张三");
        People p2 = new People(50, "张三");
        People p3 = new People(100, "张三");
        People p4 = new People(200, "张三");
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        String str = JSON.toJSONString(list, new ProperFilter());
        String jsonStr = JSON.toJSONString(list, new PascalNameFilter());
        String jsonStr1 = JSON.toJSONString(list, new MyValueFilter());
//        String jsonStr2 = JSON.toJSONString(list, new MyBeforeFilter());
        String jsonStr3 = JSON.toJSONString(list, new MyAfterFilter());
//		System.out.println(str);
//		System.out.println(jsonStr);
//		System.out.println(jsonStr1);
//        System.out.println(JSON.toJSONString(list));
//        System.out.println(jsonStr2);
//        System.out.println(JSON.toJSONString(list));
        System.out.println(jsonStr3);
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testDate() {
        Date date = new Date();
        DateTest dateTest1 = new DateTest(date, date);
        System.out.println(dateTest1);
        String jsonStr1 = JSON.toJSONString(dateTest1, SerializerFeature.WriteDateUseDateFormat);
//        DateTest dateTest3 = JSON.parseObject(jsonStr1, DateTest.class);
        System.out.println(jsonStr1);
//        System.out.println(dateTest3);

    }

    @Test
    public void testValid() {
        myTestMethod.test();
    }

    @Test
    public void testAsync(){
        System.out.println(testInterface.print("asa"));
    }


    @Test
    public void test121() throws IOException {
        double num = 110;
        System.out.println(String.format("%.2f", num));
        People people = new People();
        people.setId(21);
        people.setName("张三三as大打算打算");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.readValue(objectMapper.writeValueAsString(people),People.class));

    }

    @Test
    public void test() {
        byte[] a = "aaa".getBytes();
        System.out.println(a);
        System.out.println(new String(a));
    }

    @Test
    public void testHystrix() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(hystrixService.print(1));
        }
        Thread.sleep(20000);
        for (int i = 0; i < 10; i++) {
            System.out.println(hystrixService.print(2));
        }
    }

    @Test
    public void testIp() throws UnsupportedEncodingException {
        System.out.println(AddressUtils.getAddresses("103.242.168.151"));
    }

    @Test
    public void testStatic() {
        People people = People.getInstance();
        People people1 = People.getInstance();
        System.out.println(people.equals(people1));
    }

    @Test
    public void testFor() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        int size = list.size();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < size; j++) {
                if (i == list.get(j)) {
                    System.out.println(i);
                }
            }
        }
//        System.out.println(list.get(-5));
    }

    @Test
    public void testFinal() {
        int tradeId = 1;
        String key = "Id#taobao_" + tradeId;
        List<String> a = Arrays.asList("da", "da", "afa", "daa");
    }

    @Test
    public void testArray() {
//        String str = "a,b,c,,";
//        String[] ary = str.split(",");
//// 预期大于 3，结果是 3
//        System.out.println(ary.length);
        List<String> list = new ArrayList<String>(2);
        list.add("guan");
        list.add("bao");
        String[] array = new String[list.size()];
        array = list.toArray(array);
//        array = (String[])list.toArray();  //会报类型转换异常
        List<String> list1 = Arrays.asList("a", "b");
        System.out.println(list1.get(0));

    }

    @Test
    public void testList() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            arrayList.add(Integer.valueOf(i));
        }

        // 复现方法一
        Iterator<Integer> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer.intValue() == 5) {
//                arrayList.remove(integer);
                iterator.remove();
            }
        }
//        System.out.println(arrayList);
//
//        // 复现方法二
//        iterator = arrayList.iterator();
//        for (Integer value : arrayList) {
//            if (value.intValue() == 5) {
//                arrayList.remove(value);
//            }
//        }
//    }
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        list.add("4");
//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()) {
//            String item = iterator.next();
//            if ("2".equals(item)) {
//                iterator.remove();
//            }
//        }
//        System.out.println(list);
//    }
//        for (String item : list) {
//            if ("4".equals(item)) {
//                list.remove(item);
//            }
//        }
    }

    @Test
    public void testListMod() {
        /**
         * 当移除1时,不会报java.util.ConcurrentModificationException异常
         * 原因:异常出现原因有两个参数:modCount和expectedModCount,modCount记录list的修改记录,由于之前有两次add方法
         * 所以此时modCount为2,expectedModCount初始值和modCount一样,当list再次进行修改操作时,modCount加1,而expectedModCount
         * 值不变,通过源码可以得知异常原因为:  if (modCount != expectedModCount)
         *                                       throw new ConcurrentModificationException();
         * 所以此时理应报错.为什么为1时不报错,为2时报错,因为当移除1时,通过源码可知  hasNext()方法返回为false,源码可知
         * hasNext()方法判断cursor != size;此时cusor=1,size=1,所以此时方法不会继续向下执行,即不会走到报错那一步.
         * 拓展:为什么用iterator的remove()方法时就不会报错,因为该方法内部有expectedModCount = modCount该方法,会保持一致.
         * 结论:1.modCount 会随着调用List.remove方法而自动增减，而expectedModCount则不会变化，就导致modCount != expectedModCount。
         *　　  2.在删除倒数第二个元素后，cursor=size-1，此时size=size-1，导致hasNext方法认为遍历结束。即只有当移除倒数第二个元素
         *      时,才满足上述情况,才不会报错.
         *
         */
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
//        list.add("3");
        for (String item : list) {
            if ("2".equals(item)) {
                list.remove(item);
            }
        }
    }

    @Test
    public void testList3(){
        String[] str = new String[]{"aa","bb","cc"};
        List<String> list  = Arrays.asList(str);
        List<String> list2 = new ArrayList<String>(list);
        List<String> list3 = new ArrayList<>(str.length);
        Collections.addAll(list3,str);
        str[0] ="cc";
        System.out.println(list.get(0));
        System.out.println(list2.get(0));
        System.out.println(list3.get(0));
    }

    @Test
    public void testList4(){
        int[] myArray = { 1, 2, 3 };
        List myList = Arrays.asList(myArray);
        System.out.println(myList.size());//1
        System.out.println(myList.get(0));//数组地址值
        System.out.println(myList.get(1));//报错：ArrayIndexOutOfBoundsException
        int [] array=(int[]) myList.get(0);
        System.out.println(array[0]);//1
    }

    @Test
    public void testSerial() throws IOException, ClassNotFoundException {
//        SerialDemo serialDemo = new SerialDemo();
//        serialDemo.setName("张三");
//        ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream("C:\\Users\\lm.sun\\Desktop\\test.txt"));
//        oo.writeObject(serialDemo);
//        oo.close();
        ObjectInputStream oi = new ObjectInputStream(new FileInputStream("C:\\Users\\lm.sun\\Desktop\\test.txt"));
        SerialDemo serialDemo_back = (SerialDemo) oi.readObject();
        System.out.println("HI,My name is " + serialDemo_back.getName());
        oi.close();
    }

    @Test
    public void testProxy() {
        TestInterface testInterface = new TestInterfaceImpl();
        TestInterface testInterfaceProxy = (TestInterface) Proxy.newProxyInstance(testInterface.getClass().getClassLoader(), testInterface.getClass().getInterfaces(), (proxy, method, args) -> {
            if (method.getName().equals("print")) {
                System.out.println("代理......");
                return method.invoke(testInterface, args);
            }
            return null;
        });
        testInterfaceProxy.print("代理模式.....");
    }

    @Test
    public void testFormat() {
        System.out.println(String.format(("JWT for %s :%s"), "zhangsan", "aaa"));
    }

    @Test
    public void testContext() {
        ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class);
        ConfigurableEnvironment environment = context.getEnvironment();
        String appName = environment.getProperty("spring.application.name");
        String port = environment.getProperty("server.port");
        System.out.println(appName + " " + port);
    }

    @Test
    public void testProperties() {
        TestA testA = new TestA();
        testA.setName("张三");
        testA.setSex("男");
        TestB testB = new TestB();
        BeanUtils.copyProperties(testA, testB);
        testB.setName("李四");
        testB.setAge(17);
        BeanUtils.copyProperties(testB, testA);
        System.out.println(testA);
    }

    @Test
    public void testException() {
        ExceptionDemo exceptionDemo = new ExceptionDemo();
        System.out.println(exceptionDemo.devide(1, 5));
    }

    @Test
    public void testInteger() {
//        Integer x = 3;
//        Integer y = 3;
//        System.out.println(x == y);// true
//        Integer a = new Integer(3);
//        Integer b = new Integer(3);
//        System.out.println(a == b);//false
//        System.out.println(a.equals(b));//true
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        System.out.println(a);// 0.100000024
        System.out.println(b);// 0.099999964
        System.out.println(a == b);// false
    }

    @Test
    public void testClass() throws ClassNotFoundException {
        /**
         * Class.forName 加载类是将类进了初始化，而 ClassLoader 的 loadClass 并没有对类进行初始化，只是把类加载到了虚拟机中。
         */
        Class.forName("com.test.bean.ClassForName");
        ClassLoader.getSystemClassLoader().loadClass("com.test.bean.ClassForName");
    }

    @Test
    public void test1() {
        String str1 = "str";
        String str2 = "ing";
        String str3 = "str" + "ing";//常量池中的对象
        String str4 = str1 + str2; //在堆上创建的新的对象
        String str5 = "string";//常量池中的对象
        System.out.println(str3 == str4);//false
    }

    @Test
    public void testException2() throws ExceptionWrapper {
        try {
            Boolean status = false;
            if (status) {
                System.out.println("true");
            } else {
                throw new ExceptionWrapper("1111", "aaa");
            }
        } catch (Exception e) {
            System.out.println("系统异常");
        }
    }


    @Test
    public void count() throws ExceptionWrapper {
        try {
            Boolean boo = false;
            //若预取码成功,则执行取码操作
            if (boo) {
            } else {
                log.info("商品:[{}]库存不足", "weqewqewq");
                throw new ExceptionWrapper("dasd", "dadsad");
            }
        } catch (Exception e) {
            log.info("调用风火轮系统取码商品:[{}]失败,券码:[{}]撤销成功", "dad", "dada");
            throw new ExceptionWrapper("dadas", "dasdsadsa");
        }
    }

    @Test
    public void testJson() {
        JSONObject obj = new JSONObject();
        obj.put("activityNo", 1);
    }

    @Test
    public void testLambda() {
        List<MemberCouponsInfoVO> list = new ArrayList<>();
        MemberCouponsInfoVO memberCouponsInfoVO = new MemberCouponsInfoVO();
        memberCouponsInfoVO.setCouponsName("张三");
        memberCouponsInfoVO.setDiscountAmount(BigDecimal.valueOf(12));
        MemberCouponsInfoVO memberCouponsInfoVO1 = new MemberCouponsInfoVO();
        memberCouponsInfoVO1.setCouponsName("李四");
        memberCouponsInfoVO1.setDiscountAmount(BigDecimal.valueOf(16));
        MemberCouponsInfoVO memberCouponsInfoVO2 = new MemberCouponsInfoVO();
        memberCouponsInfoVO2.setCouponsName("王五");
        memberCouponsInfoVO2.setDiscountAmount(BigDecimal.valueOf(16));
        list.add(memberCouponsInfoVO);
        list.add(memberCouponsInfoVO1);
        list.add(memberCouponsInfoVO2);
        Optional<MemberCouponsInfoVO> max = list.stream().
                collect(Collectors.minBy((x, y) -> x.getDiscountAmount().compareTo(y.getDiscountAmount())));
        System.out.println(max.get());
    }

    @Test
    public void testLombok() {
        MemberCouponsInfoVO memberCouponsInfoVO = new MemberCouponsInfoVO();
        memberCouponsInfoVO.setCouponsBatchNo("dada");
        System.out.println(memberCouponsInfoVO);
    }

    @Test
    public void testBuilder() {
        NewComputer newComputer = NewComputer.Builder().cpu("da").build();
    }

    @Test
    public void testConcat() {
        String a = "dada";
        a = a.concat("dadada");
        System.out.println(a);
    }

    @Test
    public void testCollection() {
        List<String> a = new ArrayList<>();
        List<String> b = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        b.add("a");
        b.add("b");
        List<String> c = (ArrayList) CollectionUtil.getDiffent(a, b);
        System.out.println(c);
        System.out.println(StringUtils.join(a, ","));
    }

    @Test
    public void testArrays() {
//        String[] a = new String[]{"a", "b", "c"};
//        for (String str : a) {
//            System.out.println(str);
//        }
//        System.out.println(Arrays.asList("{a,b,c}"));
        int[] h = { 1, 2, 3, 3, 3, 3, 6, 6, 6, };
        int i[] = Arrays.copyOf(h, 30);
        System.out.println("Arrays.copyOf(h, 30);：");
        // 输出结果：123333
        for (int j : i) {
            System.out.print(j);
        }
    }

    @Test
    public void testDes() throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, BadPaddingException, IllegalBlockSizeException {
        String text = "123456";
        String key = "哈哈哈哈哈哈哈哈哈哈";
        //1.生成密钥
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory desKeyFactory = SecretKeyFactory.getInstance("des");
        SecretKey secretKey = desKeyFactory.generateSecret(desKeySpec);
        System.out.println(secretKey);
        //2.密钥上面已经生成，现在进行加密
        Cipher cipher = Cipher.getInstance("des");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new SecureRandom());//密钥在这里应用了
        byte[] encryptData = cipher.doFinal(text.getBytes());
        System.out.println(new String(encryptData));//打印的数据是：�b:���,成功加密
        //3.为了便于观察加密数据，再使用base64转换密文
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] encode = encoder.encode(encryptData);
        System.out.println(new String(encode));//打印的数据是:gQ33YjqHy+I=  （这样的数据就好看多了）
        //4.des解密
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new SecureRandom());//解密的时候这里再次用到了加密时候的secretKey
        byte[] decodeData = cipher.doFinal(encryptData);
        System.out.println(new String(decodeData));//打印的解密数据是:123456，成功解密
    }

    @Test
    public void testMD5() throws NoSuchAlgorithmException {
        String text = "CT00001"; //需要解密的数据
        //1.创建md5加密对象
        MessageDigest md5 = MessageDigest.getInstance("md5");
        //2.进行加密操作
        byte[] digest = md5.digest(text.getBytes());
        //3.转化为十六进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte data : digest) {
            String s = Integer.toHexString(data & 0xff); //byte类型的数据最高位是符号位，通过和0xff进行与操作，转换为int类型的正整数。
            stringBuilder.append(s.length() == 1 ? "0" + s : s);// 如果该正数小于16(长度为1个字符)，前面拼接0占位：确保最后生成的是32位字符串。
        }
        System.out.println(stringBuilder.toString()); //打印结果e10adc3949ba59abbe56e057f20f883e，成功加密
    }

    @Test
    public void testRSA() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, SignatureException {
        //RSA加密
        String text = "123456"; //需要解密的数据
        //1.创建密钥对KeyPair
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("rsa");
        keyPairGenerator.initialize(1024);//密钥长度是1024
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //2.获取公钥和私钥
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        //3.用私钥加密数据
        Cipher cipher = Cipher.getInstance("rsa");
        cipher.init(Cipher.ENCRYPT_MODE, privateKey, new SecureRandom());
        byte[] cipherData = cipher.doFinal(text.getBytes());
        System.out.println(new String(cipherData));//打印密文: ���i�,�2���WC1��b�m�S���&�����5�����WFB�����HJ��HN��]�;Pq�A������}G�
        //4.使用公钥解密
        cipher.init(Cipher.DECRYPT_MODE, publicKey, new SecureRandom());
        byte[] decodeData = cipher.doFinal(cipherData);
        System.out.println(new String(decodeData)); //解密数据：123456，解密成功
        //5.通过私钥和密文对传输的数据生成数字签名
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(cipherData);
        byte[] sign = signature.sign();
        System.out.println(new String(sign));//打印签名:���k��T��%�ȣ86o�ک!�W�7���
        //6.根据公钥和密文验证数据是否被修改过
        signature.initVerify(publicKey);
        signature.update(cipherData);
        boolean verify = signature.verify(sign);
        System.out.println(verify); //打印结果,验证结果:true
    }

    @Test
    public void testScanner() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String n = sc.next();
            System.out.println(n);
        }
    }

    @Test
    public void testUrl() throws UnsupportedEncodingException {
        String userInfo = String.format(("JWT for %s :%s"), "zhangsan", "aaa");
        System.out.println(userInfo);
        System.out.println(URLEncoder.encode(userInfo,"UTF-8"));
    }

    @Test
    public void testThread() throws InterruptedException {
        Lock lock  = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try{
                    System.out.println("begin await....");
                    condition.await();
                    System.out.println("end await");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });
        thread.start();
        thread.join();
        lock.lock();
        try{
            System.out.println("begin signal....");
            condition.signal();
            System.out.println("end signal");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    @Test
    public void testString(){
        String str= "2020年，愿您所求皆如愿，所行化坦途，多喜乐，常安宁。尾号{1}祝上，并送您{2}张卡券，请注意查收~";
        System.out.println(TemplateUtil.tpl(str,"123","123"));
    }

    @Test
    public void testDate2() {
        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        System.out.println(todayStart.getTime());

        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        System.out.println(todayEnd.getTime());
    }

    @Test
    public void testJoin(){
        String[] str = new String[]{"1","2","3"};
        System.out.println(StringUtils.join(str,"|"));
    }

    @Test
    public void testMap(){
        Map<String,Object> map = new HashMap<>();
        map.put("11","23w123");
        String str = (String)map.get("11");
        System.out.println(str);
    }

    @Test
    public void testRest(){
        testController.test();
    }

    @Test
    public void testMaifei(){
        Map<String, String> maps = new HashMap<>();
        maps.put("cusid", "14800200");
        maps.put("key", "835b4d90531949d6973e67c5984fada9");
        String searchUrl = "http://test.wopeixun.cn:8096/guangFaInf/authentication";
        String str = new RestTemplate().getForObject(searchUrl+"?cusid={cusid}&key={key}", String.class, maps);
        System.out.println(str);
    }

    @Test
    public void testMaifei2(){
        String searchUrl = "http://test.wopeixun.cn:8096/guangFaInf/authentication";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(searchUrl)
                .queryParam("cusid", "14800200")
                .queryParam("key", "835b4d90531949d6973e67c5984fada9");
        String str = new RestTemplate().getForObject(builder.build().encode().toUri(), String.class);
        System.out.println(str);
    }

    @Test
    public void testMaifei3(){

        String url = "http://test.wopeixun.cn:8096/guangFaInf/pushMemberInfo";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("token","b6954696adedab06bd0e3fb96c4b2d4e");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("name", "14800200")
                .queryParam("mobile", "14800200")
                .queryParam("couponCode", "835b4d90531949d6973e67c5984fada9");
        HttpEntity<String> request = new HttpEntity<>(headers);
        String response = new RestTemplate().postForObject(builder.build().encode().toUri(), request , String.class );
        System.out.println(response);
    }

    @Test
    public void testMail() throws UnsupportedEncodingException{
        mailService.sendAttachmentsMail(System.getProperty("user.dir") + "/2020-02-23麦菲退款申请订单数据.xlsx", "lm.sun@i-vpoints.com", "麦斐退款订单数据", "详情见附件");
    }

    @Test
    public void testIO(){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet xssfSheet = workbook.createSheet("麦斐退款订单");
        //自定义列标题
        String[] headers = {"姓名","联系电话","串码","订单号"};
        XSSFRow row = xssfSheet.createRow(0);
        FileOutputStream fos = null;
        try {
            String subjectName = DateFormatUtils.format(DateUtils.addDays(new Date(),0), "yyyy-MM-dd") + "麦斐退款申请订单数据";
            fos = new FileOutputStream(new File(System.getProperty("user.dir") + "/" + subjectName + ".xlsx"));
            workbook.write(fos);
            fos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testFileDelete(){
        String subjectName = DateFormatUtils.format(DateUtils.addDays(new Date(),0), "yyyy-MM-dd") + "麦斐退款申请订单数据";
        new File(System.getProperty("user.dir") + "/"+subjectName+".xlsx").delete();
    }

    @Test
    public void testSub(){
        String str = "卡号:1web914586109835;卡密:88995903";
        int index1 =str.indexOf(":");
        int index2 = str.indexOf(";");
        int index3 = str.lastIndexOf(":");
        String coupon = str.substring(index1+1,index2);
        String couponPass = str.substring(index3+1);
        System.out.println(coupon);
        System.out.println(couponPass);
    }

    @Test
    public void testSub2(){
        String decrypt = "卡号:1web914586109835;卡密:88995903";
        String decryptPass = null;
        //判断卡号卡密是否在一块,若在一块,以"卡号"打头
        if(decrypt.startsWith("卡号")){
            int startIndex= decrypt.indexOf(":");
            int endIndex =  decrypt.indexOf(";");
            int startIndex2 = decrypt.lastIndexOf(":");
            decryptPass  =decrypt.substring(startIndex2+1);
            decrypt =decrypt.substring(startIndex+1,endIndex);
        }
        System.out.println(decrypt);
        System.out.println(decryptPass);
    }

    @Test
    public void testByte(){
        System.out.println("hello,server1".getBytes(Charset.forName("utf-8")).length);
        System.out.println("hello,server1".length());
        System.out.println("你好,世界".getBytes(Charset.forName("utf-8")).length);
        System.out.println("你好,世界".getBytes(Charset.forName("Unicode")).length);
        System.out.println("你好,世界".getBytes(Charset.forName("ASCII")).length);
        System.out.println("你好,世界".getBytes(Charset.forName("GB2312")).length);
        System.out.println("你好,世界".getBytes().length);
        System.out.println("你好,世界".length());
        char a = 'a';
        System.out.println(a);
    }

    @Test
    public void testNull(){
        System.out.println(org.apache.commons.lang3.StringUtils.isNoneBlank(null));
    }

    @Test
    public void testEqual(){
        User user = new User();
        User user1 = new User();
        User user2 = new User();
        user1 = user;
        user2 = user;
        System.out.println(user2.equals(user1));
    }

    @Test
    public void testReturn(){
        System.out.println(MyTestMethod.f(3));
    }

    @Test
    public void testFinalPool(){
        /**
         * [-128，127] 此范围内可以,超过即为false.原理:常量池原理,等理于
         * String a = "abc";
         * String b = "abc;
         * 此时a==b,因为常量池为同一个.超出上述范围,即常量池不适用
         */
        Integer i11 = 128;
        Integer i22 = 128;
        System.out.println(i11 == i22);// 输出 false
        System.out.println(i11 .equals(i22) );// 输出 true
    }

    @Test
    public void testController() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new ExceptionController()).build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/test1").accept(MediaType.APPLICATION_JSON)).andReturn();

    }

    @Test
    public void testRandom(){
        //随机数的生成是根据seed种子来计算的,每次生成随机数的时候会重新计算seed的值,所以值会不同.
        //计算seed和计算随机值的函数是固定的,也就是说当random的seed值一样的时候,计算出的值也一样
        Random random = new Random(5);
//        Random random1 = new Random(5);
//        System.out.println(random.nextInt(5));
//        System.out.println(random1.nextInt(5));
        for(int i =0;i<10;i++){
            //因为seed值固定,所以生成的随机值一样
//            random.setSeed(10);
            System.out.print(random.nextInt(100)+" ");
        }
        System.out.println();
        random.setSeed(5);
        for(int i =0;i<10;i++){
            //因为seed值固定,所以生成的随机值一样
//            random.setSeed(10);
            System.out.print(random.nextInt(100)+" ");
        }
    }

    @Test
    public void testArr(){
        String[] a ;
        String[] b = new String[]{"a","b"};
        a=b;
        b=new String[]{"a"};
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }

    public  void putValueToMap(Map map,List list,String key){
        map.put(key,list);

    }

    @Test
    public void testMap2(){
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        HashMap<String,List> hashMap = new HashMap<>();
//        hashMap.put("key1",list);
//        hashMap.put("key2",list);
//        System.out.println(hashMap.get("key1").add("c"));
//        System.out.println(hashMap.get("key2").toString());
        putValueToMap(hashMap,list,"key1");
        putValueToMap(hashMap,list,"key2");
        System.out.println(hashMap.get("key1").add("c"));
        System.out.println(hashMap.get("key2"));
    }

    @Test
    public void testThreadRandom(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Random random1 = new Random();
        for(int i=0;i<10;i++){
            executorService.execute(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
               System.out.println(Thread.currentThread().getName()+"local---"+random.nextInt(10));
//                System.out.println(Thread.currentThread().getName()+"random---"+random1.nextInt(10));
            });
        }
        executorService.shutdown();
    }

    @Test
    public void testConcurrent() throws InterruptedException {
        RateLimiter limiter =  RateLimiter.create(5);
        for(int i = 0;i<7;i++){
        limiter.acquire(1);
//            TimeUnit.SECONDS.sleep(1);
        }
    }

    @Test
    public void testUser(){
        User user = new User();
        user.setName("  aaaaa");
        User user1 = new User();
        user1.setName(user.getName());
        System.out.println(user1);
    }

    @Test
    public void testStatic2(){
        ThreadLocalRandom threadLocalRandom1 = ThreadLocalRandom.current();
        ThreadLocalRandom threadLocalRandom2 = ThreadLocalRandom.current();
        System.out.println(threadLocalRandom1.equals(threadLocalRandom2));
        User user = new User();
        User user1 = new User();
        System.out.println(user.equals(user1));
    }

    @Test
    public void testCopyOnWrite(){
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
        arrayList.add("a");
        arrayList.add("b");
        Iterator<String> iterable = arrayList.iterator();
        arrayList.add("c");
        arrayList.add("d");
        while(iterable.hasNext()){
            System.out.println(iterable.next());
        }
        System.out.println(String.valueOf(System.currentTimeMillis()).length());
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testLock() throws InterruptedException {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        reentrantLock.lock();
        System.out.println("a");
        condition.await();
        reentrantLock.unlock();
    }


    /**
     * Spring加载Bean过程:
     *    SpringApplication.run(TestApplication.class, args)->SrpingApplication.refreshContext(ConfigurableApplicationContext context)->
     *    AbstractApplicationContext.invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory)
     *    作用:将容器中的Bean加载到容器,但是没有实例化,保存形式,存储在一个HasnMap中,key是bean名称,value是对应的BeanDefinition(可以看做是bean的元信息,包含bean本身描述信息)
     *    ->finishBeanFactoryInitialization(ConfigurableListableBeanFactory beanFactory)
     *    作用:初始化实例,将其方法展开
     *    ->beanFactory.preInstantiateSingletons(); 初始化实例,方法内部是一个do循环,实例完beanFactory里面的所有beanName
     *    ->DefaultListableBeanFactory.getBean(beanName);初始化实例,此步执行完毕后,加载完当前beanName的实例
     *    至此为止:Spring加载bean实例完毕
     *
     *    bean实例化具体步骤:
     *      beanFactory.preInstantiateSingletons()->DefaultListableBeanFactory.getBean(beanName)->AbstractBeanFactory.doGetBean()
     *      ->AbstractBeanFactory.doGetBean(169)->AbstractAutowireCapableBeanFactory.doCreateBean(306)->AbstractAutowireCapableBeanFactory.createBeanInstance(321)
     *      ->AbstractAutowireCapableBeanFactory.instantiateBean(734)
     *      ->AbstractAutowireCapableBeanFactory.getInstantiationStrategy().instantiate(768)
     *      ->SimpleInstantiationStrategy.instantiate(31)->BeanUtils.instantiateClass(SimpleInstantiationStrategy(61))
     *      进入到最后方法可知,实例是通过反射创建的.大功告成
     *
     *
     */
    @Test
    public void testSpringBean(){
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        AsyncService asyncService = (AsyncService) SpringContextUtil.getBean("asyncServiceImpl");
        AsyncService asyncService1 = (AsyncService) SpringContextUtil.getBean("asyncServiceImpl");
        System.out.println(asyncService==asyncService1);
    }

}
