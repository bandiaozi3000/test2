package com.test.controller;


import com.github.rholder.retry.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/test")
public class TestRetryController {

    @RequestMapping(value = "/retry")
    public void testRetry(){
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("********************"+Thread.currentThread().getName()+"********************");
//                Random random = new Random();
//                int sleepTime = random.nextInt(4000);
//                Thread.sleep(sleepTime);
                return "SUCCESS";
            }
        };
        Retryer<String> retryer = RetryerBuilder.<String>newBuilder()
                .retryIfExceptionOfType(IOException.class)
                .retryIfRuntimeException()
                .withWaitStrategy(WaitStrategies.fixedWait(3, TimeUnit.SECONDS))
                .withStopStrategy(StopStrategies.stopAfterAttempt(2))
                .withAttemptTimeLimiter(AttemptTimeLimiters.fixedTimeLimit(3,TimeUnit.SECONDS))
                .withRetryListener(new RetryListener() {
                    @Override
                    public <V> void onRetry(Attempt<V> attempt) {
                        System.out.println("当前任务重试次数:"+attempt.getAttemptNumber());
                        if(attempt.hasException()){
                            System.out.println("任务执行异常,原因:"+attempt.getExceptionCause());
                        }
                    }
                })
                .build();
        try {
            String result = retryer.call(callable);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
