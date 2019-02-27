package com.servei.notifications_service.modules;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class MailgunConfig {
    @Value("${mailgun.api.key}") String mailgunAPIkey;
    @Value("${mailgun.api.url}") String mailgunAPIurl;

    @Bean
    public String mailgunAPIkey() {
        return this.mailgunAPIkey;
    }

    @Bean
    public String mailgunAPIurl() {
        return this.mailgunAPIurl;
    }
}
