package com.test.service.impl;

import com.test.service.AsyncService;
import com.test.service.TestInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestInterfaceImpl implements TestInterface {

    @Autowired
    public AsyncService asyncService;

    @Override
    public String print(String content) {
        asyncService.testAsync();
        return content;
    }

}
