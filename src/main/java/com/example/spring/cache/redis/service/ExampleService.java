package com.example.spring.cache.redis.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class ExampleService {
	
	@Cacheable(value = "${cache.key}")
	public String testCache(String name) throws InterruptedException {
		
		System.out.printf("without cache!");
		
		// don't do this at home!!!
		Thread.currentThread().sleep(3000l);
		
		return name;
	}
}
