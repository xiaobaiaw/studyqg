package com.guanghui.amen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class AmenApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmenApplication.class, args);
    }

}
