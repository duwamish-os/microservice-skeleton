package org.lamatola.microservice.api;

import java.util.List;
import com.lamatola.microservice.http.SkeletonHttpClient;
import org.lamatola.microservice.schema.AdCampaign;
import org.lamatola.microservice.schema.AdResponse;
import org.lamatola.microservice.schema.MicroserviceResponse;
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
