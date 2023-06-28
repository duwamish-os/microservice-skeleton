package org.duwamish.microservice.schema;

import java.util.List;

public class AdResponse {
    List<AdCampaign> campaigns;

    public AdResponse() {

    }
    public AdResponse(List<AdCampaign> campaigns) {
        this.campaigns = campaigns;
    }


    public List<AdCampaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<AdCampaign> campaigns) {
        this.campaigns = campaigns;
    }
}
