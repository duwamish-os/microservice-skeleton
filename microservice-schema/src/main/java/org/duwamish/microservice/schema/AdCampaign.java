package org.duwamish.microservice.schema;

import java.util.List;

public class AdCampaign {

    List<String> ads;

    public AdCampaign(List<String> ads) {
        this.ads = ads;
    }

    public List<String> getAds() {
        return ads;
    }

    public void setAds(List<String> ads) {
        this.ads = ads;
    }
}
