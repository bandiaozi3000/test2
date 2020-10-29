package com.test.dubbotest;

import lombok.SneakyThrows;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.ServerSocket;
import java.net.Socket;

public class AobingRpcFramework {

    private static int count = 0;
     public static void export(Object service, int port) throws Exception { 
          ServerSocket server = new ServerSocket(port);
         System.out.println("------Dubbo 服务开始暴露--------");
          while(true) {
              Socket socket = server.accept();
              System.out.println("第"+(++count)+"次调用开始");
              new Thread(new Runnable() {
                  @SneakyThrows
                  @Override
                  public void run() {
                      //反序列化
                      ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                      String methodName = (String)input.readObject(); //读取方法名
                      Class<?>[] parameterTypes = (Class<?>[]) input.readObject(); //参数类型
                      Object[] arguments = (Object[]) input.readObject(); //参数
                      Method method = service.getClass().getMethod(methodName, parameterTypes);  //找到方法
                      Object result = method.invoke(service, arguments); //调用方法
                      // 返回结果
                      ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                      output.writeObject(result);
                      System.out.println("第"+count+"次调用结束,返回结果信息:"+result.toString());
                  }
              }).start();
          }
     }
    public static <T> T refer (Class<T> interfaceClass, String host, int port) throws Exception {
       return  (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[] {interfaceClass},
            new InvocationHandler() {
                public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
                    System.out.println("-----------开始调用Duboo服务----------");
                    Socket socket = new Socket(host, port);  //指定 provider 的 ip 和端口
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream()); 
                    output.writeObject(method.getName());  //传方法名
                    output.writeObject(method.getParameterTypes());  //传参数类型
                    output.writeObject(arguments);  //传参数值
                    ObjectInputStream input = new ObjectInputStream(socket.getInputStream());  
                    Object result = input.readObject();  //读取结果
                    System.out.println("Dubbo调用服务结束,读取结果:"+result.toString());
                    return result;  
               }
        });  
    }  
}