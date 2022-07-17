package com.bobocode.main;

import com.bobocode.annotation.EnableStringTrimming;
import com.bobocode.service.GreetingService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@EnableStringTrimming
@Configuration
public class DemoApp {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext("com.bobocode");
        var bean = context.getBean(GreetingService.class);
        String result = bean.greetings("   My lord   ");
        System.out.println(result);
    }
}
