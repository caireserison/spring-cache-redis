package com.example.spring.cache.redis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ExampleService {
	
	@Cacheable(value = "${cache.key}")
	public String testCache(String name) throws InterruptedException {

		// Note que no console vai aparecer essa mensagem somente na primeira chamada com o mesmo parâmetro
		System.out.printf("without cache!");

		// Note que somente a primeira chamada demora, com parâmetros iguais
		Thread.currentThread().sleep(3000l);
		
		return name;
	}
}
