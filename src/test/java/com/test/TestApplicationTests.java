package com.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.aspect.MyAspect;
import com.test.async.AsyncDemo;
import com.test.bean.DateTest;
import com.test.bean.People;
import com.test.filter.MyAfterFilter;
import com.test.filter.MyBeforeFilter;
import com.test.filter.MyValueFilter;
import com.test.filter.ProperFilter;
import com.test.method.MyTestMethod;
import com.test.service.HystrixService;
import com.test.util.AddressUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplicationTests {

    @Autowired
    MyAspect myAspect;

    @Autowired
    MyTestMethod myTestMethod;

    @Autowired
    HystrixService hystrixService;

    @Autowired
    AsyncDemo asyncDemo;
	/**
	 * AfterFilter和BeforeFilter区别:
	 *    AfterFilter:在序列化之后操作
	 *    BeforeFilter:在序列化之前操作
	 */
	@Test
	public void contextLoads() {
		List<People> list = new ArrayList<>();
		People p1 = new People(1,"张三");
		People p2 = new People(50,"张三");
		People p3 = new People(100,"张三");
		People p4 = new People(200,"张三");
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		String str = JSON.toJSONString(list, new ProperFilter());
		String jsonStr = JSON.toJSONString(list, new PascalNameFilter());
		String jsonStr1 = JSON.toJSONString(list, new MyValueFilter());
		String jsonStr2 = JSON.toJSONString(list, new MyBeforeFilter());
		String jsonStr3 = JSON.toJSONString(list, new MyAfterFilter());
//		System.out.println(str);
//		System.out.println(jsonStr);
//		System.out.println(jsonStr1);
		System.out.println(JSON.toJSONString(list));
		System.out.println(jsonStr2);
		System.out.println(JSON.toJSONString(list));
		System.out.println(jsonStr3);
		System.out.println(JSON.toJSONString(list));
	}

	@Test
	public void testDate(){
		Date date = new Date();
		DateTest dateTest1 = new DateTest(date,date);
		System.out.println(dateTest1);
		String jsonStr1 = JSON.toJSONString(dateTest1, SerializerFeature.WriteDateUseDateFormat);
		DateTest dateTest3 = JSON.parseObject(jsonStr1,DateTest.class);
		System.out.println(jsonStr1);
		System.out.println(dateTest3);
	}

	@Test
	public void testValid() throws NoSuchMethodException {
        MyTestMethod myTestMethod = new MyTestMethod();
		myTestMethod.test();
	}

	@Test
	public void test121() throws JsonProcessingException {
		double num = 110;
		System.out.println(String.format("%.2f", num));
		People people = new People();
		people.setId(21);
		people.setName("张三三as大打算打算");
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(people));

	}

	@Test
    public void test(){
	    byte[] a = "aaa".getBytes();
        System.out.println(a);
		System.out.println(new String(a));
    }

    @Test
	public void testHystrix() throws Exception {
		for(int i =0;i<10;i++){
			System.out.println(hystrixService.print(1));
		}
		Thread.sleep(15000);
        for(int i =0;i<10;i++){
            System.out.println(hystrixService.print(2));
        }
	}

	@Test
    public void testIp() throws UnsupportedEncodingException {
        System.out.println(AddressUtils.getAddresses("119.75.217.109"));
    }

    @Test
    public void testAsync() throws InterruptedException {
	    asyncDemo.print();
    }

}
