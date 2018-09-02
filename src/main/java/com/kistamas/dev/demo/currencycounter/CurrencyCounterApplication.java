package com.kistamas.dev.demo.currencycounter;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyCounterApplication extends ResourceConfig {

    CurrencyCounterApplication() {
        packages("com.kistamas.dev.demo.currencycounter.resources");
    }

    public static void main(String[] args) {
        SpringApplication.run(CurrencyCounterApplication.class, args);
    }
}
