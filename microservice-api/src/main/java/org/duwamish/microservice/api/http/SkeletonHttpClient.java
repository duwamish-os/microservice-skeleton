package org.duwamish.microservice.api.http;

import feign.RequestLine;
import org.duwamish.microservice.schema.AdResponse;
import org.duwamish.microservice.schema.MicroserviceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
 * FeignClient, RequestMapping are Spring annotation.
 * Outside of Spring, use core annotations like @RequestLine
 */
@FeignClient(name = "skeleton-client", url = "http://localhost:8080")
public interface SkeletonHttpClient {

    @RequestMapping(method = RequestMethod.GET, value = "/ads")
    @RequestLine("GET /{ads}")
    MicroserviceResponse<AdResponse> getAds();

}
