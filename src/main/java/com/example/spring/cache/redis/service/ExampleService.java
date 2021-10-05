package com.example.spring.cache.redis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExampleService {
	
	@Cacheable(value = "${cache.key}")
	public String testCache(String name) throws InterruptedException {

		// Note que no console vai aparecer essa mensagem somente na primeira chamada com o mesmo parâmetro
		log.info("Without cache!");

		// Note que somente a primeira chamada demora, com parâmetros iguais
		Thread.currentThread().sleep(3000l);
		
		return name;
	}
}
