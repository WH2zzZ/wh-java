package com.oowanghan.thread.problem.threadlocal.referencequeue;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Author WangHan
 * @Create 2020/5/25 1:32 上午
 */
@Slf4j
public class Demo {

    @Test
    public void test() throws InterruptedException {
        ReferenceQueue<MyObject> queue = new ReferenceQueue<>();
        MyObject reference1 = new MyObject();
        WeakReference<MyObject> reference = new WeakReference<>(reference1, queue);

        Thread thread = new Thread(() -> {
            while (true) {
                Object obj;
                if ((obj = queue.poll()) != null) {
                    System.out.println("queue!!! " + obj);
                }
            }
        });

        thread.start();
        System.out.println(reference);
        reference1 = null;

        System.gc();

        thread.join();
    }

    private static class MyObject {

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalize!!!" + this);
        }
    }
}

