package org.duwamish.microservice.api;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.duwamish.microservice.api.http.SkeletonHttpClient;
import org.duwamish.microservice.schema.AdCampaign;
import org.duwamish.microservice.schema.AdResponse;
import org.duwamish.microservice.schema.MicroserviceResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController("/supply")
public class SkeletonController implements SkeletonHttpClient {

    //    public CompletableFuture<MicroserviceResponse<AdResponse>> getAds() {
    public MicroserviceResponse<AdResponse> getAds() {
        System.out.println("=============================");
        return
            new MicroserviceResponse<>(
                new AdResponse(List.of(new AdCampaign(List.of("1", "2")))));
    }
}
