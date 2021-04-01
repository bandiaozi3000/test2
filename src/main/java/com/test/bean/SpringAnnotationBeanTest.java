package com.test.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@Configuration
//@ConditionalOnProperty(name = "annotation.enable")
//@Component
@ConfigurationProperties(prefix = "annotation")
public class SpringAnnotationBeanTest {

    private String enable;


    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }
}
