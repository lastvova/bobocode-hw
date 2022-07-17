package com.bobocode.config;

import com.bobocode.bpp.TrimmedAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;

public class StringTrimmingConfiguration {

    @Bean
    public TrimmedAnnotationBeanPostProcessor stringTrimmingBeanPostProcessor() {
        return new TrimmedAnnotationBeanPostProcessor();
    }
}
