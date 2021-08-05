package com.valdir.poolart.config;

import com.valdir.poolart.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Autowired
    private DBService dbService;

    @Bean
    public void startDB() {
        if(ddl.equals("create")) {
            this.dbService.startDB();
        }
    }
}
