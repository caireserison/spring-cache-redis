package com.example.spring.cache.redis.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExampleServiceTest {

    // Testes utilizando o próprio Redis

    @Autowired
    ExampleService exampleService;

    @Test
    public void testCache() throws InterruptedException {
        String response = exampleService.testCache("Erison");

        Assertions.assertThat(response).isEqualTo("Erison");
    }

    @Test
    public void testCacheTwoTimes() throws InterruptedException {
        // Teste utilizando o próprio Redis

        String response = exampleService.testCache("Erison");
        String responseTwo = exampleService.testCache("Erison");

        Assertions.assertThat(response).isEqualTo(responseTwo);
    }
}
