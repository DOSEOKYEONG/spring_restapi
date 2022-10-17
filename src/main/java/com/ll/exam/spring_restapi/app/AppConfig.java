package com.ll.exam.spring_restapi.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Getter
    private static ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        AppConfig.context = context;
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}

