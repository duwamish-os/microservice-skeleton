package org.lamatola.microservice.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdServerApplication.class, args);
    }

}
