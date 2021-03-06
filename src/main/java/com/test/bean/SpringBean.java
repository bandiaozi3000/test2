//package com.test.bean;
//
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SpringBean implements BeanPostProcessor,InitializingBean,BeanFactoryPostProcessor{
//
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("***************afterPropertiesSet************执行");
//    }
//
//    @Override
//    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
//        System.out.println("***************postProcessBeforeInitialization************执行--"+s);
//        return o;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
//        System.out.println("***************postProcessAfterInitialization************执行--"+s);
//        return o;
//    }
//
//    @Override
//    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        System.out.println("MyBeanFactoryPostProcessor...postProcessBeanFactory...");
//    }
//}
