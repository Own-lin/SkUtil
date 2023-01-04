package com.skutil.redis;


/**
 * 该接口基于分布式锁的特点进行抽象。
 * <b><tr>在实现分布式锁时实现类需要考虑以下几点：</tr></b>
 * <ul>
 *     <li>锁对象应该是谁</li>
 *     <li>如何判断当前线程是否持有锁</li>
 *     <li>线程获取到锁后所在机器/进程/线程 挂了或者kill掉了怎么办</li>
 *     <li>锁的最大获取时间</li>
 *     <li>锁的最大持有时间</li>
 * </ul>
 *
 *
 * @author zhan yan
 * @date 2022/12/21
 */
public interface DistributedLock {

    /**
     * 尝试获取锁
     */
    boolean tryLock(String bizModule, String objName);

    /**
     * 尝试获取锁，如果获取成功则设定自定义的最大持有时间
     */
    boolean tryLock(String bizModule, String objName, Long timeOut);

    /**
     * 检查锁是否被持有
     */
    boolean checkExist(String bizModule, String objName);

    /**
     * 解锁
     */
    boolean unLock(String bizModule, String objName);

}
