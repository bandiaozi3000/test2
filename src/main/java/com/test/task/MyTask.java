//package com.test.task;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MyTask {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(MyTask.class);
//
//    @Scheduled(cron = "0 */2 * * * ?")
//    public void task2() {
//        LOGGER.info("当前时间：{}\t\t任务：cron task，每5秒执行一次", System.currentTimeMillis());
//    }
//
//}
