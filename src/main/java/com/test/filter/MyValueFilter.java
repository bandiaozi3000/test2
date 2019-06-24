package com.test.filter;

import com.alibaba.fastjson.serializer.ValueFilter;

public class MyValueFilter implements ValueFilter {
    @Override
    public Object process(Object o, String s, Object o1) {
        if ("id".equals(s)) {
            int value = ((Integer) o1).intValue();
            if (value > 100) {
                value = 20000;
            }
            return value;
        }
        return o1;
    }
}
