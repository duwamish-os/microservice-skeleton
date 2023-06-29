package com.lamatola.microservice.http.client;

import com.lamatola.microservice.http.MicroserviceHttpClient;
import feign.AsyncFeign;
import feign.Feign;
import feign.Target.HardCodedTarget;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import java.util.concurrent.CompletableFuture;
import org.lamatola.microservice.schema.AdResponse;
import org.lamatola.microservice.schema.MicroserviceResponse;

public class HttpClientTest {

    public static void main(String[] args) {
        MicroserviceHttpClient asyncClient = AsyncFeign.asyncBuilder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(new HardCodedTarget<>(MicroserviceHttpClient.class, "http://localhost:8080/"));

        MicroserviceHttpClient fClient = Feign.builder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(new HardCodedTarget<>(MicroserviceHttpClient.class, "http://localhost:8080/"));

        MicroserviceResponse<AdResponse> ads = asyncClient.getAds();

        System.out.println(ads.getPayload().getCampaigns().get(0).getProducts());

        CompletableFuture<MicroserviceResponse<AdResponse>> adsAsync = asyncClient.getAdsAsync();
        System.out.println(adsAsync.join().getPayload().getCampaigns().get(0).getProducts());

//        Mono<MicroserviceResponse<AdResponse>> adsMono = fClient.getAdsMono();
//        System.out.println("adsAsync: " + adsMono);
    }
}
