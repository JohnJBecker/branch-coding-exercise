package com.exercise.accounts.github;

import feign.Request;
import feign.codec.ErrorDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class GithubClientConfig {

    @Value("${feign.client.config.github.connectTimeoutMillis}")
    private long connectTimeout;  // connect timeout in milliseconds

    @Value("${feign.client.config.github.readTimeoutMillis}")
    private long readTimeout;  // read timeout in milliseconds

    @Bean
    public Request.Options requestOptions() {
        // Convert connectTimeout from milliseconds to Duration
        Duration connectTimeoutMillis = Duration.ofMillis(connectTimeout);

        // Convert readTimeout from milliseconds to Duration
        Duration readTimeoutMillis = Duration.ofMillis(readTimeout);

        // Set connection timeout to 5000ms (5 seconds) and read timeout to 10000ms (10 seconds)
        return new Request.Options(connectTimeoutMillis, readTimeoutMillis, false);
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new GithubClientErrorDecoder();
    }
}
