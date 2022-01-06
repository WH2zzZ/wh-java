package com.oowanghan.ractor.rxjava.tool;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

/**
 * @Author WangHan
 * @Create 2021/6/4 12:27 上午
 */
public class DisposableDemo {

    /**
     * 正常来说 线程都是在main线程执行的，可通过如下操作符来进行线程切换
     * subscribeOn
     */
    @Test
    public void test01() throws InterruptedException {
        Observer<String> observer = getCommonObserver();

        // 创建被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {

                System.out.println(Thread.currentThread().getName() + " : start send event");

                emitter.onNext("1");
                Thread.sleep(2000);

                emitter.onNext("2");
                Thread.sleep(1000);
                emitter.onNext("3");
            }
        });
        Disposable disposable = observable
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        System.out.println("receive message : " + s);
                    }
                });

        //observer这种方式 disposable 在onSubscribe中获取
        observable
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .subscribe(getCommonObserver());

        Thread.sleep(1000);
        disposable.dispose();
        System.out.println("测试有异常会不会终止程序");
    }

    private Observer<String> getCommonObserver() {
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println(Thread.currentThread().getName() + " : subscribe!");
            }

            @Override
            public void onNext(@NonNull String message) {
                System.out.println(Thread.currentThread().getName() + " : message = " + message);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println(Thread.currentThread().getName() + " : error = " + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println(Thread.currentThread().getName() + " : complete");
            }
        };
        return observer;
    }
}
