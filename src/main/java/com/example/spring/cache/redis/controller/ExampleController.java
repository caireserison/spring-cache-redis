package com.example.spring.cache.redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring.cache.redis.service.ExampleService;

@RestController
public class ExampleController {

	@Autowired
	ExampleService exampleService;
	
	@GetMapping(path = "/test-cache/name/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String test(@PathVariable(value = "name") String name) {
		
		try {
			return exampleService.testCache(name);
		} catch (InterruptedException e) {
			return e.getMessage();
		}
	}
}
