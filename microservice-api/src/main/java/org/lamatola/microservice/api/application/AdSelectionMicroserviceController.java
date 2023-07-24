package org.lamatola.microservice.api.application;

import java.util.List;
import com.lamatola.microservice.http.AdSelectionMicroService;
import java.util.concurrent.CompletableFuture;
import org.lamatola.microservice.api.domain.CampaignDomainService;
import org.lamatola.microservice.schema.AdCampaign;
import org.lamatola.microservice.schema.AdResponse;
import org.lamatola.microservice.schema.MicroserviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/supply")
public class AdSelectionMicroserviceController implements AdSelectionMicroService {

    @Autowired
    CampaignDomainService campaignDomainService;

    public MicroserviceResponse<AdResponse> getAds() {
        System.out.println("============================= sync ========");
        List<AdCampaign> ads = campaignDomainService.getAds();
        return new MicroserviceResponse<>(new AdResponse(ads));
    }

    @Override
    public CompletableFuture<MicroserviceResponse<AdResponse>> getAdsAsync() {
        System.out.println("============================= async ====== ");
        MicroserviceResponse<AdResponse> data = new MicroserviceResponse<>(
            new AdResponse(campaignDomainService.getAds()));

        return CompletableFuture.completedFuture(data);
    }

    @Override
    public Mono<MicroserviceResponse<AdResponse>> getAdsMono() {
        MicroserviceResponse<AdResponse> data = new MicroserviceResponse<>(
            new AdResponse(campaignDomainService.getAds()));

        return Mono.fromCallable(() -> data);
    }
}
