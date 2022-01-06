package com.oowanghan.ractor.rxjava.myrxjava.test;

import com.oowanghan.ractor.rxjava.myrxjava.event.Emitter;
import com.oowanghan.ractor.rxjava.myrxjava.observable.Observable;
import com.oowanghan.ractor.rxjava.myrxjava.observable.ObservableOnSubscribe;
import com.oowanghan.ractor.rxjava.myrxjava.observable.operater.schedule.thread.SchedulerFactory;
import com.oowanghan.ractor.rxjava.myrxjava.observer.Observer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author WangHan
 * @Create 2021/6/6 6:41 下午
 */
public class ObserveTest {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 1,
                TimeUnit.MINUTES, new ArrayBlockingQueue<>(1));

        Observable.create(
                new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(Emitter<? super String> emitter) {
                        System.out.println(Thread.currentThread().getName() + ": send event");
                        emitter.onNext("1");
                        emitter.onNext("2");
                        emitter.onNext("3");
                        emitter.onComplete();
                    }
                }
        )
                .subscribeOn(SchedulerFactory.newThread())
                .observeOn(SchedulerFactory.newThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {
                        System.out.println(Thread.currentThread().getName() + ": subscribe");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(Thread.currentThread().getName() + ": message:" + s);
                    }

                    @Override
                    public void onComplete() {
                        System.out.println(Thread.currentThread().getName() + ": complete");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println(Thread.currentThread().getName() + ": error=" + throwable.getMessage());
                    }
                });

        for (int i = 0; i < 100; i++) {
            System.out.println("main run...");
        }
    }
}
