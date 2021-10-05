package com.example.spring.cache.redis.service;

import com.example.spring.cache.redis.entity.ObjectExample;
import com.example.spring.cache.redis.repository.RedisRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
@Slf4j
public class OperationExampleService {
    private final RedisRepository redisRepository;

    public OperationExampleService(RedisRepository redisRepository) {
        this.redisRepository = redisRepository;
    }

    public String saveAge(ObjectExample request) throws InterruptedException {
        log.info("Salvando objeto no Redis");
        redisRepository.saveObject(request.getId().toString(), request);

        return "Successfully concluded!";
    }

    public String getAge(Integer id) throws Exception {
        log.info("Buscando objeto no Redis");
        try {
            var om = new ObjectMapper();
            var obj = om.convertValue(redisRepository.getObject(id.toString()), ObjectExample.class);

            var today = LocalDate.now();
            var range = Period.between(obj.getDateBirth(), today);
            var age = range.getYears();

            return obj.getName() + " is " + String.valueOf(age) + " years old.";
        } catch (Exception ex) {
            throw new Exception("Data not found for this id. Error: " + ex.getMessage());
        } finally {
            redisRepository.deleteObject(id.toString());
        }

    }
}
