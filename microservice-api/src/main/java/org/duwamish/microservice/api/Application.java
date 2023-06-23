package org.duwamish.microservice.api;

import org.duwamish.microservice.schema.MicroserviceResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        MicroserviceResponse response  = new MicroserviceResponse(
                "1",
                "hello microservice"
        );

        System.out.println("===================");
        System.out.println(response.requestId + "-" + response.messsage);
        System.out.println("===================");

        SpringApplication.run(Application.class, args);
    }

}
