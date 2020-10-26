package com.test.bean;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Data
public class SpringBeanAll implements BeanNameAware,BeanFactoryAware,ApplicationContextAware,BeanPostProcessor,InitializingBean,DisposableBean,ApplicationListener {
    private String name;
    private Integer age;

    public void setName(String name) {
        System.out.println("2、填充属性");
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SpringBeanAll() {
        System.out.println("1、实例化");
    }


    @Override
    public void setBeanName(String s) {
        System.out.println("3、BeanNameAware的setBeanName：name="+s);
    }


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("4、BeanFactoryAware的setBeanFactory。。");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("5、ApplicationContextAware的setApplication...");
    }

    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println("6、bean初始化之前执行,beanname="+s);
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println("8、bean初始化之后执行,beanname="+s);
        return o;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("7、属性设置成功后执行的初始化方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("9、销毁bean方法");
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof ContextRefreshedEvent){
            System.out.println("ContextRefreshedEvent");
        }
        if(applicationEvent instanceof ContextStartedEvent){
            System.out.println("ContextStartedEvent");
        }
        if(applicationEvent instanceof ContextStoppedEvent){
            System.out.println("ContextStoppedEvent");
        }
        if(applicationEvent instanceof ContextClosedEvent){
            System.out.println("ContextClosedEvent");
        }
        if(applicationEvent instanceof RequestHandledEvent){
            System.out.println("RequestHandledEvent");
        }
    }

    @PostConstruct
    public void init(){
        System.out.println("**************** SpringBeanALL init ****************");
    }


    @PreDestroy
    public void destroy2(){
        System.out.println("**************** SpringBeanALL destroy ****************");
    }

}