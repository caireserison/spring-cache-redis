package com.example.spring.cache.redis.controller;

import com.example.spring.cache.redis.entity.ObjectExample;
import com.example.spring.cache.redis.service.ComplexObjectExampleService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComplexObjectExampleTest {
    private static final String SUCCESS_MESSAGE = "Erison is 30 years old.";

    @Autowired
    TestRestTemplate restTemplate;

    @MockBean
    ComplexObjectExampleService complexObjectExampleService;

    ObjectExample objectExample;

    @Before
    public void setup() {
        objectExample = new ObjectExample();
        objectExample.setId(1);
        objectExample.setName("Erison");
        objectExample.setDateBirth(LocalDate.now().minusYears(30));
    }

    @Test
    public void testAge() throws InterruptedException {
        Mockito
                .when(complexObjectExampleService.getAge(Mockito.any(ObjectExample.class)))
                .thenReturn(SUCCESS_MESSAGE);

        String response = restTemplate.postForObject("/test-cache", objectExample, String.class);
        Assertions.assertThat(response).isEqualTo(SUCCESS_MESSAGE);
    }

    @Test
    public void testAgeException() throws InterruptedException {
        Mockito
                .doThrow(new InterruptedException("TEST"))
                .when(complexObjectExampleService)
                .getAge(Mockito.any(ObjectExample.class));

        String response = restTemplate.postForObject("/test-cache", objectExample, String.class);
        Assertions.assertThat(response).isEqualTo("TEST");
    }
}
