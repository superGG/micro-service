package com.kellan.demo.schedul;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 测试spring 自带的定时器
 * @author: Kellan_Song
 * @date: 2020-08-22 16:32
 **/
@Configuration
@EnableScheduling
public class TestSpringSchedul {

    private final Logger logger = LoggerFactory.getLogger(TestSpringSchedul.class);

    private int i = 0;


    @Scheduled(cron = "* * * * * *")
    public void testSchedul() {
        logger.info(String.valueOf(i++));

    }

}