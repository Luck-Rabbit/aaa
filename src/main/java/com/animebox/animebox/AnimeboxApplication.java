package com.animebox.animebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class AnimeboxApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimeboxApplication.class, args);
    }

}
