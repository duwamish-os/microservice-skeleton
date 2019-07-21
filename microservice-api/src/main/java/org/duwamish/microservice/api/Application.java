package org.duwamish.microservice.api;

import org.duwamish.microservice.schema.MicroserviceResponse;

public class Application {

    public static void main(String[] args) {
        MicroserviceResponse response  = new MicroserviceResponse(
                "1",
                "hello microservice"
        );

        System.out.println(response);
    }

}
