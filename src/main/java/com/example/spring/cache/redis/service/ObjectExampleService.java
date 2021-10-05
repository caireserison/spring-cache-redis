package com.example.spring.cache.redis.service;

import com.example.spring.cache.redis.entity.ObjectExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@Slf4j
public class ObjectExampleService {

    @Cacheable(cacheNames = "${cache.key}", key = "#request.id")
    public String getAge(ObjectExample request) throws InterruptedException {
        LocalDate today = LocalDate.now();
        Period range = Period.between(request.getDateBirth(), today);
        Integer age = range.getYears();

        // Note que no console vai aparecer essa mensagem somente na primeira chamada com o mesmo parâmetro
        log.info("Without cache!");

        // Note que somente a primeira chamada demora, com parâmetros iguais
        Thread.currentThread().sleep(3000l);

        return request.getName() + " is " + age.toString() + " years old.";
    }
}
