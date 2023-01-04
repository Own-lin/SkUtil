package com.example.demo.controller;

import com.skutil.redis.support.RedissonFactory;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author zhan yan
 * @date 2022/12/28
 */
@Slf4j
@RestController
public class MainController {

    private final RedissonFactory redissonFactory;

    private final RedissonClient client;

    public MainController(RedissonClient client) {
        this.client = client;
        this.redissonFactory = new RedissonFactory(client);
    }

    @GetMapping("/say")
    public void doSay(String name){
        redissonFactory.star(name, 5L, 3L, () -> log.info("Got the lock, user is : " + name));
    }

    @GetMapping("/lock")
    public void doLock(String name){
        new Thread(() -> {
            RLock lock = client.getFairLock(name);
            lock.lock(5L, TimeUnit.SECONDS);
            log.info("Lock it : " + name);
        }, "ToDo Thread").start();
    }
}
