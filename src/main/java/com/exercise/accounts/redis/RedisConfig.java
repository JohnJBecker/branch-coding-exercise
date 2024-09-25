package com.exercise.accounts.redis;

import io.lettuce.core.ClientOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig {

    @Value("${spring.cache.redis.timeToLiveMillis}")
    private long ttl;  // TTL in milliseconds

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        // Convert TTL from milliseconds to Duration
        Duration ttlDuration = Duration.ofMillis(ttl);

        // Define default cache configuration with configurable TTL
        RedisCacheConfiguration cacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(ttlDuration)  // Set TTL from properties
                .disableCachingNullValues();  // Avoid caching null values

       return RedisCacheManager.builder(redisConnectionFactory)
               .cacheDefaults(cacheConfiguration)
               .build();
    }

    // Custom LettuceConnectionFactory with limited reconnect attempts
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration("localhost", 6379);

        // Customize ClientOptions to limit reconnection attempts
        ClientOptions clientOptions = ClientOptions.builder()
                .autoReconnect(true) // Enable reconnect
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS) // Do not accept new commands when disconnected
                .build();

        // Create the Lettuce client configuration with client options
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .clientOptions(clientOptions)
                .commandTimeout(Duration.ofSeconds(5))  // Set command timeout
                .build();

        return new LettuceConnectionFactory(redisConfig, clientConfig);
    }
}
