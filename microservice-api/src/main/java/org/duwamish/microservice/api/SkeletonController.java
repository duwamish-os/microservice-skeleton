package org.duwamish.microservice.api;

import java.util.List;
import org.duwamish.microservice.schema.AdCampaign;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SkeletonController {

    @GetMapping("ads")
    public ResponseEntity<List<AdCampaign>> getAds() {
        return ResponseEntity.ok(
            List.of(new AdCampaign(List.of("1", "2")))
        );
    }
}
