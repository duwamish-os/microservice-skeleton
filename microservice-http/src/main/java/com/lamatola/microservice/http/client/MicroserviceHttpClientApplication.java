package com.lamatola.microservice.http.client;

import com.lamatola.microservice.http.MicroService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients(clients = {MicroService.class})
public class MicroserviceHttpClientApplication {

    @Bean
    public MicroServiceSpringClient microServiceApp() {
        return new MicroServiceSpringClient();
    }

    private static Logger LOG = LoggerFactory
        .getLogger(MicroserviceHttpClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceHttpClientApplication.class, args);
    }

}
