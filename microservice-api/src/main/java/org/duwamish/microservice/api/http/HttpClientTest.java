package org.duwamish.microservice.api.http;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.okhttp.OkHttpClient;
import org.duwamish.microservice.schema.AdResponse;
import org.duwamish.microservice.schema.MicroserviceResponse;

public class HttpClientTest {

    public static void main(String[] args) {
        SkeletonHttpClient target = Feign.builder()
            .client(new OkHttpClient())
            .decoder(new JacksonDecoder())
            .target(SkeletonHttpClient.class, "http://localhost:8080/ads");

        MicroserviceResponse<AdResponse> ads = target.getAds();
        System.out.println(ads.getPayload().getCampaigns().get(0).getProducts());
    }
}
