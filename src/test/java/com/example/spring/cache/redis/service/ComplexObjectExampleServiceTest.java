package com.example.spring.cache.redis.service;

import com.example.spring.cache.redis.entity.ObjectExample;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ComplexObjectExampleServiceTest {
    private static final String SUCCESS_MESSAGE = "Erison is 30 years old.";

    @Autowired
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
    public void testGetAge() throws InterruptedException {
        String response = complexObjectExampleService.getAge(objectExample);

        Assertions.assertThat(response).isEqualTo(SUCCESS_MESSAGE);
    }

    @Test
    public void testGetAgeTwoTimes() throws InterruptedException {
        String response = complexObjectExampleService.getAge(objectExample);
        String responseTwo = complexObjectExampleService.getAge(objectExample);

        Assertions.assertThat(response).isEqualTo(responseTwo);
    }
}
