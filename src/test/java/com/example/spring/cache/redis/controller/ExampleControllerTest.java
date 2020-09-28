package com.example.spring.cache.redis.controller;

import com.example.spring.cache.redis.service.ExampleService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleControllerTest {

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    ExampleService exampleService;

    @Test
    public void testCache() throws InterruptedException {
        Mockito
                .when(exampleService.testCache(Mockito.anyString()))
                .thenReturn("Erison");

        String response = restTemplate.getForObject("/test-cache/name/Erison", String.class);
        Assertions.assertThat(response).isEqualTo("Erison");
    }

    @Test
    public void testCacheException() throws InterruptedException {
        Mockito
                .doThrow(new InterruptedException("TEST"))
                .when(exampleService)
                .testCache(Mockito.anyString());

        String response = restTemplate.getForObject("/test-cache/name/Erison", String.class);
        Assertions.assertThat(response).isEqualTo("TEST");
    }
}
