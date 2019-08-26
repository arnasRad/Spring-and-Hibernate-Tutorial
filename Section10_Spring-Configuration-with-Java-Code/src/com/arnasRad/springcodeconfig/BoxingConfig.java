package com.arnasRad.springcodeconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BoxingConfig {

    @Bean
    public UsualFortuneService usualFortuneService() {
        return new UsualFortuneService();
    }

    @Bean BoxingCoach boxingCoach() {
        return new BoxingCoach(usualFortuneService());
    }
}
