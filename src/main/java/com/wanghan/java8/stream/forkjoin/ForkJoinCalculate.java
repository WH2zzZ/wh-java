package com.wanghan.stream.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinCalculate extends RecursiveTask<Long> {


    private long start;
    private long end;

    private static final long THRESHOLD = 10000;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i ++){
                sum += i;
            }
            return sum;
        }else {
            long mid = (start + end) / 2;
            ForkJoinCalculate left = new ForkJoinCalculate(start, mid);
            left.fork();//拆分子任务同时压入线程队列

            ForkJoinCalculate right = new ForkJoinCalculate(mid + 1, end);
            right.fork();

            return left.join() + right.join();

        }
    }
}
