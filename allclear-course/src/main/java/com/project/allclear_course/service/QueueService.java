package com.project.allclear_course.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String QUEUE_KEY = "queue";
    private static final int QUEUE_LIMIT = 100; // 대기열 크기 제한 설정

    public void enqueue(String item) {
        Long size = redisTemplate.opsForList().size(QUEUE_KEY);
        if (size != null && size >= QUEUE_LIMIT) {
            throw new IllegalStateException("Queue is full");
        }
        redisTemplate.opsForList().leftPush(QUEUE_KEY, item);
    }

    public String dequeue() {
        return (String) redisTemplate.opsForList().rightPop(QUEUE_KEY);
    }

    public Long getQueueSize() {
        return redisTemplate.opsForList().size(QUEUE_KEY);
    }
}
