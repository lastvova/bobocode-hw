package com.bobocode;

import com.bobocode.service.PictureService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class DemoApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApp.class, args);
        System.out.println(run.getBean(PictureService.class).getLargestPictureURI(15));
    }
}
