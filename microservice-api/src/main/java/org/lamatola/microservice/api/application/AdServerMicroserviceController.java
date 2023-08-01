package org.lamatola.microservice.api.application;

import com.lamatola.microservice.http.AdServerMicroService;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.lamatola.microservice.api.domain.CampaignDomainService;
import org.lamatola.microservice.schema.AdCampaign;
import org.lamatola.microservice.schema.AdResponse;
import org.lamatola.microservice.schema.MicroserviceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController("/supply")
public class AdServerMicroserviceController implements AdServerMicroService {

    private static final int TIMEOUT_MILLIS = 15000;
    @Autowired
    CampaignDomainService campaignDomainService;

    ExecutorService threadPool1 = Executors.newFixedThreadPool(1);
    ExecutorService threadPool2 = threadPool1;

    HttpClient client1 = HttpClient.newBuilder()
        .executor(threadPool1)
        .build();
    HttpClient client2 = HttpClient.newBuilder()
        .executor(threadPool2)
        .build();

    public CompletableFuture<MicroserviceResponse<AdResponse>> getAds() {
        System.out.println("============================= sync ========");

        HttpRequest selectionRequest1 = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8081/v1/ads/select"))
            .timeout(Duration.ofMillis(TIMEOUT_MILLIS))
            .header("Content-Type", "application/json")
            .build();

        HttpRequest selectionRequest2 = HttpRequest.newBuilder()
            .uri(URI.create("http://localhost:8081/v1/ads/select"))
            .timeout(Duration.ofMillis(TIMEOUT_MILLIS))
            .header("Content-Type", "application/json")
            .build();

        long start = System.currentTimeMillis();

        System.out.println("-------------------------------------- start selection request1 ------------------------");
        var adsCandidatesFuture1 = client1.sendAsync(selectionRequest1, BodyHandlers.ofString());
        var adsCandidatesFuture2 = client2.sendAsync(selectionRequest2, BodyHandlers.ofString());

        CompletableFuture<String> adsCandidates1 = adsCandidatesFuture1.thenComposeAsync(res -> {
                System.out.println("rank request1 started: " + thread() + ": " + (System.currentTimeMillis() - start));
                HttpRequest rankRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8082/v1/ads/rank/sync"))
                    .timeout(Duration.ofMillis(TIMEOUT_MILLIS))
                    .header("Content-Type", "application/json")
                    .build();

                CompletableFuture<String> rankedAds = client1.sendAsync(rankRequest, BodyHandlers.ofString())
                    .thenApply(a -> a.body());
                return rankedAds;
            }, threadPool1).whenCompleteAsync((a, b) -> {
                System.out.println("rank request1 completed: " + thread() + ": " +  (System.currentTimeMillis() - start));
            }, threadPool1);

        System.out.println("-------------------------------------- start selection request2 ------------------------");
        CompletableFuture<String> adsCandidates2 = adsCandidatesFuture2.thenComposeAsync(res -> {
                System.out.println("rank request2 started: " + thread() + ": " + (System.currentTimeMillis() - start));
                HttpRequest rankRequest = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8082/v1/ads/rank/sync"))
                    .timeout(Duration.ofMillis(TIMEOUT_MILLIS))
                    .header("Content-Type", "application/json")
                    .build();

                CompletableFuture<String> rankedAds = client2.sendAsync(rankRequest, BodyHandlers.ofString())
                    .thenApply(a -> a.body());
                return rankedAds;
            }, threadPool2).whenCompleteAsync((a, b) -> {
                System.out.println("rank request2 completed: " + thread() + ": " + (System.currentTimeMillis() - start));
            }, threadPool2);;

        return CompletableFuture.allOf(adsCandidates1, adsCandidates2).thenApplyAsync(___ -> {
            List<String> ranked = List.of(adsCandidates1.join(), adsCandidates2.join());
            var response = new MicroserviceResponse<>(new AdResponse(List.of(new AdCampaign(ranked))));
            System.out.println("Response created in " + thread() + ": " + (System.currentTimeMillis() - start));
            return response;
        }, threadPool1).whenCompleteAsync((____, err) -> {
            System.out.println("Response completed in " + thread() + ": " + (System.currentTimeMillis() - start));
        }, threadPool1);
    }

    private static String thread() {
        return Thread.currentThread().getName();
    }

    @Override
    public CompletableFuture<MicroserviceResponse<AdResponse>> getAdsAsync() {
        System.out.println("============================= async ====== ");
        MicroserviceResponse<AdResponse> data = new MicroserviceResponse<>(
            new AdResponse(campaignDomainService.getAds()));

        return CompletableFuture.completedFuture(data);
    }

    @Override
    public Mono<MicroserviceResponse<AdResponse>> getAdsMono() {
        MicroserviceResponse<AdResponse> data = new MicroserviceResponse<>(
            new AdResponse(campaignDomainService.getAds()));

        return Mono.fromCallable(() -> data);
    }
}
