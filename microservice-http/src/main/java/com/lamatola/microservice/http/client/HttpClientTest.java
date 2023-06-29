package com.lamatola.microservice.http.client;

import com.lamatola.microservice.http.SkeletonHttpClient;
import feign.Feign;
import feign.Target.HardCodedTarget;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import org.lamatola.microservice.schema.AdResponse;
import org.lamatola.microservice.schema.MicroserviceResponse;

public class HttpClientTest {

    public static void main(String[] args) {
        SkeletonHttpClient fClient = Feign.builder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(new HardCodedTarget<>(SkeletonHttpClient.class, "http://localhost:8080/"));

        MicroserviceResponse<AdResponse> ads = fClient.getAds();
        System.out.println(ads.getPayload().getCampaigns().get(0).getProducts());
    }
}
