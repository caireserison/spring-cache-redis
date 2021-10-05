package com.example.spring.cache.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableRedisRepositories
public class RedisConfig implements CachingConfigurer {

	@Value("${spring.redis.host}")
	private String hostName;

	@Value("${spring.redis.port}")
	private Integer port;

	@Bean
	public JedisConnectionFactory redisConnectionFactory() {
		var redisStandaloneConfiguration = new RedisStandaloneConfiguration(hostName, port);
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	public RedisTemplate redisTemplate(
			@Qualifier("redisConnectionFactory")
			RedisConnectionFactory redisConnectionFactory
	) {
		RedisTemplate redisTemplate = new StringRedisTemplate();
		redisTemplate.setConnectionFactory(redisConnectionFactory());

		var jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
		var om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		jackson2JsonRedisSerializer.setObjectMapper(om);

		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());

		redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
		redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

		redisTemplate.setEnableTransactionSupport(true);
		redisTemplate.afterPropertiesSet();

		return redisTemplate;
	}

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
