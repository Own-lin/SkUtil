package com.skutil.redis.support;

import com.skutil.redis.DistributedLock;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * 利用Redis实现的分布式锁（公平、可重入、超时）
 *
 *
 * @author zhan yan
 * @date 2022/12/21
 */
public class SimpleRedisLock implements DistributedLock {

    private final Long TIME_OUT = 10L;

    private final String KEY_PREFIX = "DistributedLock:";

    private final RedisTemplate<String, Object> template;

    public SimpleRedisLock(RedisTemplate<String, Object> template) {
        this.template = template;
    }

    @Override
    public boolean tryLock(String bizModule, String objName) {
        String key = KEY_PREFIX + bizModule + ":" + objName;
        Boolean a = template.opsForValue().setIfAbsent(key, 1, TIME_OUT, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(a);
    }

    @Override
    public boolean tryLock(String bizModule, String objName, Long timeOut) {
        String key = KEY_PREFIX + bizModule + ":" + objName;
        Boolean a = template.opsForValue().setIfAbsent(key, 1, timeOut, TimeUnit.SECONDS);
        return Boolean.TRUE.equals(a);
    }

    @Override
    public boolean checkExist(String bizModule, String objName){
        String key = KEY_PREFIX + bizModule + ":" +objName;
        Boolean a = template.hasKey(key);

        return Boolean.TRUE.equals(a);
    }

    @Override
    public boolean unLock(String bizModule, String objName) {
        Boolean a = template.delete(KEY_PREFIX + bizModule + ":" + objName);
        return Boolean.TRUE.equals(a);
    }



}
