package org.duwamish.microservice.api;

import java.util.List;
import org.duwamish.microservice.schema.AdCampaign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "skeleton-client", url = "localhost:8080")
public interface SkeletonHttpClient {

    @RequestMapping(method = RequestMethod.GET, value = "/ads")
    List<AdCampaign> getAds();

}
