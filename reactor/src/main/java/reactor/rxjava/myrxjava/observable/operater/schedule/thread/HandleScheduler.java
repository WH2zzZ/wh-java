package com.oowanghan.ractor.rxjava.myrxjava.observable.operater.schedule.thread;

import java.util.concurrent.ExecutorService;

/**
 * @Author WangHan
 * @Create 2021/6/7 12:34 上午
 */
public class HandleScheduler extends Scheduler {

    private final ExecutorService executorService;

    public HandleScheduler(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public Worker createWorker() {
        return new HandlerWorker(executorService);
    }

    static final class HandlerWorker implements Worker{

        final ExecutorService executorService;

        public HandlerWorker(ExecutorService executorService) {
            this.executorService = executorService;
        }

        @Override
        public void schedule(Runnable runnable) {
            executorService.submit(runnable);
        }
    }
}
