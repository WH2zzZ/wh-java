package com.oowanghan.ractor.rxjava.myrxjava.observable.operater.schedule.thread;

import java.util.concurrent.*;

/**
 * @Author WangHan
 * @Create 2021/6/7 11:33 下午
 */
public class SchedulerFactory {

    private static Scheduler NEW_THREAD = null;

    static {
        NEW_THREAD = new HandleScheduler(Executors.newFixedThreadPool(1));
    }

    public static Scheduler newThread(){
        return new HandleScheduler(Executors.newFixedThreadPool(1));
    }
}
