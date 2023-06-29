package org.lamatola.microservice.api.http;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;

public class HttpConfig {
    @Bean
    public OkHttpClient client() {
        return new OkHttpClient();
    }
}
