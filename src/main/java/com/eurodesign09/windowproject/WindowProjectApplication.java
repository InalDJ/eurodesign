package com.eurodesign09.windowproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;

@SpringBootApplication
public class WindowProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(WindowProjectApplication.class, args);
    }

}
