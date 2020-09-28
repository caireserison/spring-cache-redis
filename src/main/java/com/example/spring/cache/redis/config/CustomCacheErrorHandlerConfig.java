package com.example.spring.cache.redis.config;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomCacheErrorHandlerConfig implements CacheErrorHandler {
	@Override
	public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
		System.out.printf(exception.getMessage());
	}

	@Override
	public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
		System.out.printf(exception.getMessage());
		
	}

	@Override
	public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
		System.out.printf(exception.getMessage());
		
	}

	@Override
	public void handleCacheClearError(RuntimeException exception, Cache cache) {
		System.out.printf(exception.getMessage());
		
	}

}
