package com.lamatola.microservice.http;

import feign.Headers;
import feign.RequestLine;
import org.lamatola.microservice.schema.AdResponse;
import org.lamatola.microservice.schema.MicroserviceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
 * FeignClient, RequestMapping are Spring annotation.
 * Outside of Spring, use core annotations like @RequestLine
 */
@FeignClient(name = "skeleton-client", url = "http://localhost:8080")
@Headers("Content-Type: application/json")
public interface SkeletonHttpClient {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/supply/ads")
    @RequestLine("GET /v1/supply/ads")
    MicroserviceResponse<AdResponse> getAds();
//    CompletableFuture<MicroserviceResponse<AdResponse>> getAds();

}
