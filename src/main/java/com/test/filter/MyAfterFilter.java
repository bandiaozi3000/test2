package com.test.filter;

import com.alibaba.fastjson.serializer.AfterFilter;
import com.test.bean.People;

public class MyAfterFilter extends AfterFilter {
    @Override
    public void writeAfter(Object o) {
        People p = (People) o;
        ((People) o).setName("王五");
    }
}
