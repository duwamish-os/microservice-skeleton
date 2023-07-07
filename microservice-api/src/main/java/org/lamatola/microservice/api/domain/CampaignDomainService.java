package org.lamatola.microservice.api.domain;

import java.util.List;
import org.lamatola.microservice.schema.AdCampaign;
import org.springframework.stereotype.Service;

@Service
public class CampaignDomainService {
    public List<AdCampaign> getAds() {
        return List.of(new AdCampaign(List.of("1", "2")));
    }
}
