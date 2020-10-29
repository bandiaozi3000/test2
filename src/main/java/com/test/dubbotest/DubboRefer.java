package com.test.dubbotest;

public class DubboRefer {

    public static void main(String[] args) throws Exception {
        //服务调用者只需要设置依赖
        AobingService service = AobingRpcFramework.refer(AobingService.class, "127.0.0.1", 2333);
        service.hello("你好");
    }
}
