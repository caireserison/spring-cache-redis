package com.example.spring.cache.redis.controller;

import com.example.spring.cache.redis.entity.ObjectExample;
import com.example.spring.cache.redis.service.OperationExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class OperationExampleController {

	@Autowired
	OperationExampleService operationExampleService;

	@PostMapping(path = "/test-operation-cache", produces = MediaType.APPLICATION_JSON_VALUE)
	public String testPostObject(@RequestBody ObjectExample request) {
		try {
			return operationExampleService.saveAge(request);
		} catch (InterruptedException e) {
			return e.getMessage();
		}
	}

	@GetMapping(path = "/test-operation-cache/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String testGetObject(@PathVariable(value = "id") Integer id) throws Exception {
		try {
			return operationExampleService.getAge(id);
		} catch (InterruptedException e) {
			return e.getMessage();
		}
	}
}
