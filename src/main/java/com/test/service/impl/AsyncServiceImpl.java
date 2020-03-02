package com.test.service.impl;

import com.test.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @ClassName:AsyncService
 * @Description:异步测试
 * @Author:lm.sun
 * @Date:2019/10/21 10:08
 * @Version:V3.3
 */
@Service
public class AsyncServiceImpl implements AsyncService{


    @Override
    @Async
    public void testAsync() {
        try {
            System.out.println("同步任务开始");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("同步任务完成");
        }
    }


}
