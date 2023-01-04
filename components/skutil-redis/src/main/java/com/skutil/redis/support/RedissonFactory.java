package com.skutil.redis.support;

import com.skutil.common.exception.support.OsExecuteException;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author zhan yan
 * @date 2022/12/28
 */
public class RedissonFactory {

    private final RedissonClient client;

    public RedissonFactory(RedissonClient client) {
        this.client = client;
    }

    private static final long WAIT_TIME_DEFAULT = 5L;

    private static final long LEASE_TIME_DEFAULT = 5L;


    public Boolean star(String lockObj, Runnable runnable){
        return getaBoolean(lockObj, WAIT_TIME_DEFAULT, LEASE_TIME_DEFAULT, runnable);
    }

    public Boolean star(String lockObj, long maxLockTime, Runnable runnable){
        return getaBoolean(lockObj, WAIT_TIME_DEFAULT, maxLockTime, runnable);
    }

    public Boolean star(String lockObj, long maxWaitTime, long maxLockTime, Runnable runnable){
        return getaBoolean(lockObj, maxWaitTime, maxLockTime, runnable);
    }


    private Boolean getaBoolean(String lockObj, long maxWaitTime, long maxLockTime, Runnable runnable) {
        RLock lock = client.getFairLock(lockObj);
        try {
            long trueWaitTie = maxWaitTime == 0L ? WAIT_TIME_DEFAULT : maxWaitTime;
            long trueLockTime = maxLockTime == 0L ? LEASE_TIME_DEFAULT : maxLockTime;

            lock.tryLock(trueWaitTie, trueLockTime, TimeUnit.SECONDS);
            runnable.run();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new OsExecuteException(e);
        }finally {
            lock.unlock();
        }
        return true;
    }


}
