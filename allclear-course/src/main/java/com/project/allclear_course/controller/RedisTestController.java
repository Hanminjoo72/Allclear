package com.project.allclear_course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisTestController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @GetMapping("/test")
    public String testRedis() {
        // 테스트 키와 값
        String key = "testKey";
        String value = "testValue";

        // Redis에 값 저장
        redisTemplate.opsForValue().set(key, value);

        // Redis에서 값 읽기
        String redisValue = (String) redisTemplate.opsForValue().get(key);

        return "Redis value for '" + key + "' is: " + redisValue;
    }
}