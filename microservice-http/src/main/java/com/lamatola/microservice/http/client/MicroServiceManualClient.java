package com.lamatola.microservice.http.client;

import com.lamatola.microservice.http.AdServerMicroService;
import feign.AsyncFeign;
import feign.Feign;
import feign.Target.HardCodedTarget;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import java.util.concurrent.CompletableFuture;
import org.lamatola.microservice.schema.AdResponse;
import org.lamatola.microservice.schema.MicroserviceResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class MicroServiceManualClient implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        AdServerMicroService asyncClient = AsyncFeign.asyncBuilder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(new HardCodedTarget<>(AdServerMicroService.class, "http://localhost:8080/"));

        AdServerMicroService fClient = Feign.builder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(new HardCodedTarget<>(AdServerMicroService.class, "http://localhost:8080/"));

        CompletableFuture<MicroserviceResponse<AdResponse>> ads = asyncClient.getAds();

        System.out.println("==============================================");
        System.out.println(ads.join().getPayload().getCampaigns().get(0).getProducts());

        CompletableFuture<MicroserviceResponse<AdResponse>> adsAsync = asyncClient.getAdsAsync();
        System.out.println(adsAsync.join().getPayload().getCampaigns().get(0).getProducts());

//        Mono<MicroserviceResponse<AdResponse>> adsMono = fClient.getAdsMono();
//        System.out.println("adsAsync: " + adsMono);
    }
}
