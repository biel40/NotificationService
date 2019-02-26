package com.servei.notifications_service.modules;

import com.servei.notifications_service.MailProvider.MailGun;
import com.servei.notifications_service.MailProvider.MailProvider;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableNeo4jRepositories("com.servei.notifications_service.repositories")
@EntityScan(basePackages = "com.servei.notifications_service.nodes")
public class SpringConfiguration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

}
