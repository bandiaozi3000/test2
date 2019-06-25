//package com.test.filter;
//
//
//import com.alibaba.fastjson.serializer.PropertyFilter;
//
//public class ProperFilter implements PropertyFilter {
//
//
//    @Override
//    public boolean apply(Object o, String s, Object o1) {
//        if ("id".equals(s)) {
//            int id = ((Integer) o1).intValue();
//            return id >= 100;
//        }
//        return false;
//    }
//}
