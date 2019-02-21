package com.servei.notifications_service;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;

public class RestTemplate {

    @Bean
    public org.springframework.web.client.RestTemplate restTemplate(RestTemplate restTemplate) {
        return new org.springframework.web.client.RestTemplate();
    }
}
