package com.bobocode.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
public class ApplicationConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Scheduled(fixedRate = 1, timeUnit = TimeUnit.DAYS)
    @CacheEvict(value = "largestPicture", allEntries = true)
    public void clearCache() {
        log.info("Cache was cleared");
    }
}
