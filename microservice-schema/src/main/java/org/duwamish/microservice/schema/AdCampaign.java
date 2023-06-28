package org.duwamish.microservice.schema;

import java.util.List;

public class AdCampaign {

    List<String> products;

    public AdCampaign() {

    }

    public AdCampaign(List<String> products) {
        this.products = products;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }
}
