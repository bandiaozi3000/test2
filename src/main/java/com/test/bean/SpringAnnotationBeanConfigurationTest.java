package com.test.bean;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SpringAnnotationBeanTest.class)
public class SpringAnnotationBeanConfigurationTest {
}
