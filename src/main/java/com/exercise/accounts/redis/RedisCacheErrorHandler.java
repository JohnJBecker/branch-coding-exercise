package com.exercise.accounts.redis;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.RedisSystemException;

@Component
public class RedisCacheErrorHandler implements CacheErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(RedisCacheErrorHandler.class);

    @Override
    public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
        log.error("Cache get error for key '{}'. Error: {}", key, exception.getMessage());
        handleRedisException(exception);
    }

    @Override
    public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
        log.error("Cache put error for key '{}'. Error: {}", key, exception.getMessage());
        handleRedisException(exception);
    }

    @Override
    public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
        log.error("Cache evict error for key '{}'. Error: {}", key, exception.getMessage());
        handleRedisException(exception);
    }

    @Override
    public void handleCacheClearError(RuntimeException exception, Cache cache) {
        log.error("Cache clear error. Error: {}", exception.getMessage());
        handleRedisException(exception);
    }

    private void handleRedisException(RuntimeException exception) {
        if (exception instanceof RedisSystemException) {
            log.warn("Redis error occurred, proceeding without caching.");
        } else {
            throw exception;
        }
    }
}