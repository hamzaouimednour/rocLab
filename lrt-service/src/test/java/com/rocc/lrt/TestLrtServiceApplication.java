package com.rocc.lrt;

import org.springframework.boot.SpringApplication;

public class TestLrtServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(LrtServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
