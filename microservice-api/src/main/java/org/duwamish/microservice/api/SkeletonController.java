package org.duwamish.microservice.api;

import java.util.List;
import org.duwamish.microservice.schema.AdCampaign;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkeletonController implements SkeletonHttpClient {

    public List<AdCampaign> getAds() {
        return
            List.of(new AdCampaign(List.of("1", "2")));
    }
}
