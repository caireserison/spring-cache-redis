package com.example.spring.cache.redis.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig implements CachingConfigurer {

	@Override
	public CacheManager cacheManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CacheResolver cacheResolver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public KeyGenerator keyGenerator() {
		// TODO Auto-generated method stub
		return null;
	}

	// Configuração criada para que, em caso de erro do Redis, a aplicação apenas grave o log e continue normalmente
	@Override
	public CacheErrorHandler errorHandler() {
		return new CustomCacheErrorHandlerConfig();
	}

}
