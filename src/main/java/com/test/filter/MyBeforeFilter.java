package com.test.filter;

        import com.alibaba.fastjson.serializer.BeforeFilter;
        import com.test.bean.People;

public class MyBeforeFilter extends BeforeFilter {

    @Override
    public void writeBefore(Object o) {
        People p = (People) o;
        ((People) o).setName("李四");
    }
}
