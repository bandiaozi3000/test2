package com.test.bean;

import java.net.InetAddress;
import java.net.UnknownHostException;
public class Ip {
    public static void main(String[]args)
    {
        try {

            String domainname="https://api.92jiangbei.com";//输入要解析的域名
            System.out.println("使用InetAddress类的方法获取网站"+domainname+"的IP地址...");

            System.out.println("总共ip个数："
                    + InetAddress.getAllByName(domainname).length);//获取接续出来的ip的个数
            InetAddress[] inetadd = InetAddress.getAllByName(domainname);
            //遍历所有的ip并输出
            for (int i = 0; i < inetadd.length; i++) {
                System.out.println("第" + (i + 1) + "个ip：" + inetadd[i]);
            }

        } catch (UnknownHostException e) {
            System.out.println("获取网站www.csdn.net的IP地址失败！没有对应的IP！");
        }
    }

}
