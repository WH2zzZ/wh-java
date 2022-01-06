package com.oowanghan.thread.problem.safe.AQS;


import com.oowanghan.thread.util.QueueObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 手动实现锁
 *
 * @Author WangHan
 * @Create 2019/12/7 4:30 下午
 */
public class MyFairLock03 {

    private boolean isLock = false;

    Thread lockBy = null;

    private int lockCount = 0;

    private List<QueueObject> waitingThreads = new ArrayList<>();

    public synchronized void lock() throws InterruptedException {
        QueueObject queueObject = new QueueObject();
        synchronized (this){
            waitingThreads.add(queueObject);
        }

        try {
            queueObject.doWait();
        } catch (InterruptedException e) {
            synchronized (this){
                waitingThreads.remove(queueObject);
            }
            throw e;
        }
    }

    public synchronized void unlock() {
        if (this.lockBy != Thread.currentThread()){
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLock = false;
        lockBy = null;
        if (waitingThreads.size() > 0){
            waitingThreads.get(0).doNotify();
        }
    }

}
