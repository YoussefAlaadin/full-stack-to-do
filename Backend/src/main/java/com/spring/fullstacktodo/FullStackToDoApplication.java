package com.spring.fullstacktodo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class FullStackToDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(FullStackToDoApplication.class, args);
        System.out.println("Hello World");
    }

}
