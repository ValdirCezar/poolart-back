package com.valdir.poolart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class PoolArtApplication {

    public static void main(String[] args) {
        SpringApplication.run(PoolArtApplication.class, args);
        log.info("------------------ SERVER RUNNING ON PORT 8080 -------------------");
    }

}
