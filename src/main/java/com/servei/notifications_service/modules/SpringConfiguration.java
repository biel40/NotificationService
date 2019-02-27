package com.servei.notifications_service.modules;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@Configuration
@EnableNeo4jRepositories("com.servei.notifications_service.repositories")
@EntityScan(basePackages = "com.servei.notifications_service.nodes")
public class SpringConfiguration {
}
