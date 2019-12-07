package com.example.littlekitchen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@ServletComponentScan
public class LittlekitchenApplication {

    public static void main(String[] args) {
        SpringApplication.run(LittlekitchenApplication.class, args);
    }

}
