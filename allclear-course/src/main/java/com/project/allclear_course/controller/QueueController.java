package com.project.allclear_course.controller;

import com.project.allclear_course.service.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queue")
public class QueueController {

    @Autowired
    private QueueService queueService;

    @PostMapping("/enqueue")
    public String enqueue(@RequestParam String item) {
        queueService.enqueue(item);
        return "Item added to queue";
    }

    @PostMapping("/dequeue")
    public String dequeue() {
        String item = queueService.dequeue();
        return item != null ? "Dequeued item: " + item : "Queue is empty";
    }

    @GetMapping("/size")
    public Long getQueueSize() {
        return queueService.getQueueSize();
    }
}