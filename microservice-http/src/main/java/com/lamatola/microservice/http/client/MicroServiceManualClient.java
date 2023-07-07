package com.lamatola.microservice.http.client;

import com.lamatola.microservice.http.MicroService;
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
import reactor.core.publisher.Mono;

@Component
@Order(1)
public class MicroServiceManualClient implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {

        MicroService asyncClient = AsyncFeign.asyncBuilder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(new HardCodedTarget<>(MicroService.class, "http://localhost:8080/"));

        MicroService fClient = Feign.builder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(new HardCodedTarget<>(MicroService.class, "http://localhost:8080/"));

        MicroserviceResponse<AdResponse> ads = asyncClient.getAds();

        System.out.println("==============================================");
        System.out.println(ads.getPayload().getCampaigns().get(0).getProducts());

        CompletableFuture<MicroserviceResponse<AdResponse>> adsAsync = asyncClient.getAdsAsync();
        System.out.println(adsAsync.join().getPayload().getCampaigns().get(0).getProducts());

//        Mono<MicroserviceResponse<AdResponse>> adsMono = fClient.getAdsMono();
//        System.out.println("adsAsync: " + adsMono);
    }
}
