package com.lamatola.microservice.http.client;

import com.lamatola.microservice.http.AdServerMicroService;
import org.lamatola.microservice.schema.AdResponse;
import org.lamatola.microservice.schema.MicroserviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class MicroServiceSpringClient implements CommandLineRunner {
    @Autowired
    AdServerMicroService adServerMicroService;

    @Override
    public void run(String... args) throws Exception {
        MicroserviceResponse<AdResponse> adsAsync = adServerMicroService.getAds().join();
        System.out.println("------------------------");
        System.out.println("Microservice Response: " + adsAsync.getPayload().getCampaigns().get(0).getProducts());
        System.out.println();
        System.out.println("------------------------");
    }

}
