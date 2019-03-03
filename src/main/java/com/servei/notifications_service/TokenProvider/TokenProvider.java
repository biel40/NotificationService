package com.servei.notifications_service.TokenProvider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class TokenProvider {

    @Value("${token.provider.url}")
    String tokenProviderUrl;

    @Value("${token.verificator.url}")
    String tokenVerificationUrl;

    @Bean
    public String getTokenProviderUrl() {
        return this.tokenProviderUrl;
    }

    @Bean
    public String getTokenVerificatorUrl() {
        return this.tokenVerificationUrl;
    }

}
