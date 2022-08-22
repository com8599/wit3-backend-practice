package com.witbackend.week8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Week8Application {

    public static void main(String[] args) {
        SpringApplication.run(com.witbackend.week8.Week8Application.class, args);
    }
}
