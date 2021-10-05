package com.example.spring.cache.redis.controller;

import com.example.spring.cache.redis.entity.ObjectExample;
import com.example.spring.cache.redis.service.ObjectExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObjectExampleController {

    @Autowired
    ObjectExampleService objectExampleService;

    @PostMapping(path = "/test-cache", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testObject(@RequestBody ObjectExample request) {
        try {
            return objectExampleService.getAge(request);
        } catch (InterruptedException e) {
            return e.getMessage();
        }
    }
}
