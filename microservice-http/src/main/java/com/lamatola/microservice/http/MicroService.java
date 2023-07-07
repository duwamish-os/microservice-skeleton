package com.lamatola.microservice.http;

import feign.Headers;
import feign.RequestLine;
import java.util.concurrent.CompletableFuture;
import org.lamatola.microservice.schema.AdResponse;
import org.lamatola.microservice.schema.MicroserviceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import reactor.core.publisher.Mono;

/**
 * https://cloud.spring.io/spring-cloud-netflix/multi/multi_spring-cloud-feign.html
 * FeignClient, RequestMapping are Spring annotation.
 * Outside of Spring, use core annotations like @RequestLine
 */
@FeignClient(name = "microService", url = "http://localhost:8080", configuration = MicroServiceConfig.class)
@Headers("Content-Type: application/json")
public interface MicroService {

    @RequestMapping(method = RequestMethod.GET, value = "/v1/supply/ads")
    @RequestLine("GET /v1/supply/ads")
    MicroserviceResponse<AdResponse> getAds();

    @RequestMapping(method = RequestMethod.GET, value = "/v1/supply/ads/async")
    @RequestLine("GET /v1/supply/ads/async")
    CompletableFuture<MicroserviceResponse<AdResponse>> getAdsAsync();

    @RequestMapping(method = RequestMethod.GET, value = "/v1/supply/ads/mono")
    @RequestLine("GET /v1/supply/ads/mono")
    Mono<MicroserviceResponse<AdResponse>> getAdsMono();
}
