package com.wanghan.java8.redisson.distribute;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

/**
 * @Author WangHan
 * @Create 2021/4/16 4:05 下午
 */
public class RedissonTest {

    public static void main(String[] args) {

        Config config = new Config();
        config.useSingleServer()
                // use "rediss://" for SSL connection
                .setAddress("redis://127.0.0.1:6379")
                .setClientName("test1");

        RedissonClient redisson = Redisson.create(config);

//        makeThread();
        System.out.println(Thread.currentThread().getName() + " ready lock");
        RLock lock = redisson.getLock("myLock");
        lock.lock(3, TimeUnit.MINUTES);
        System.out.println(Thread.currentThread().getName() + " get lock");
        lock.lock(3, TimeUnit.MINUTES);
        try {
            Thread.sleep(3 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void makeThread() {
        Config config = new Config();
        config.useSingleServer()
                // use "rediss://" for SSL connection
                .setAddress("redis://127.0.0.1:6379")
                .setClientName("test2");

        RedissonClient redisson = Redisson.create(config);
        Thread thread = new Thread(() -> {
            RLock myLock = redisson.getLock("myLock");
            System.out.println(Thread.currentThread().getName() + " ready lock");
            myLock.lock(3, TimeUnit.MINUTES);
            System.out.println(Thread.currentThread().getName() + " get lock");
        });

        thread.start();
    }
}
