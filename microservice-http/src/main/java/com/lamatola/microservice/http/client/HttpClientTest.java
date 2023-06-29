package com.lamatola.microservice.http.client;

import com.lamatola.microservice.http.MicroserviceHttpClient;
import feign.Feign;
import feign.Target.HardCodedTarget;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import java.util.concurrent.CompletableFuture;
import org.lamatola.microservice.schema.AdResponse;
import org.lamatola.microservice.schema.MicroserviceResponse;

public class HttpClientTest {

    public static void main(String[] args) {
        MicroserviceHttpClient fClient = Feign.builder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(new HardCodedTarget<>(MicroserviceHttpClient.class, "http://localhost:8080/"));

        MicroserviceResponse<AdResponse> ads = fClient.getAds();

        System.out.println(ads.getPayload().getCampaigns().get(0).getProducts());

        CompletableFuture<MicroserviceResponse<AdResponse>> adsAsync = fClient.getAdsAsync();
        int i = 0;
        while (!adsAsync.isDone() && i < 10000000) {
            i++;
        }

        System.out.println("adsAsync: " + adsAsync.isDone());
    }
}
