package com.example.spring.cache.redis.controller;

import com.example.spring.cache.redis.entity.ObjectExample;
import com.example.spring.cache.redis.service.ComplexObjectExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;

@RestController
public class ComplexObjectExampleController {

    @Autowired
    ComplexObjectExampleService complexObjectExampleService;

    @PostMapping(path = "/test-cache", produces = MediaType.APPLICATION_JSON_VALUE)
    public String testObject(@RequestBody ObjectExample request) {
        try {
            return complexObjectExampleService.getAge(request);
        } catch (InterruptedException e) {
            return e.getMessage();
        }
    }
}
