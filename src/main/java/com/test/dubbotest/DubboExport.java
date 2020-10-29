package com.test.dubbotest;

public class DubboExport {
    public static void main(String[] args) throws Exception {
        //服务提供者只需要暴露出接口
        AobingService service = new AobingServiceImpl ();
        AobingRpcFramework.export(service, 2333);
    }
}
