package com.rocc.interlocking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class InterlockingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterlockingServiceApplication.class, args);
    }

}
