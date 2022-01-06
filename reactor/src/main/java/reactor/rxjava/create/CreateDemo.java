package com.oowanghan.ractor.rxjava.create;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import org.junit.Test;

/**
 * 响应模式：
 * jdk9开始， java正式开始支持响应式（Reactive）编程
 * 主要讲解rxjava
 * java.util.concurrent.FLow
 * @Author WangHan
 * @Create 2021/6/4 12:27 上午
 */
public class CreateDemo {

    /**
     * Observer观察者
     */
    @Test
    public void test01() {
        //1. 创建观察者Observer
        Observer<String> observer = getCommonObserver();

        // 创建被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                //事件产生的地方
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                //error和complete是互斥的，执行一个
                emitter.onError(new Throwable("测试异常"));
                emitter.onComplete();
            }
        });
        observable.subscribe(observer);
        System.out.println("测试有异常会不会终止程序");
    }

    /**
     * Consumer消费者
     */
    @Test
    public void test02() {
        // 创建被观察者
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                //事件产生的地方
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                //error和complete是互斥的，执行一个
                emitter.onError(new Throwable("测试异常"));
                emitter.onComplete();
            }
        });

        /**
         * 这种方式如果遇到异常 会抛出
         */
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("receive:" + s);
            }
        });

        /**
         * 这种方式就会自行处理异常，被捕获了
         */
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("receive:" + s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.out.println("error:" + throwable.getMessage());
            }
        });
    }

    @Test
    public void test0() {
        //1. 创建观察者Observer
        Observer<String> observer = getCommonObserver();

        //from(T[])/from(Iterable<? extends T>)将传入的数组或者Iterable拆分成Java对象依次发送
        Observable<String> from = Observable.fromArray("1", "2");
        from.subscribe(observer);

        System.out.println("=====================");

        //just(T...)将传入的参数依次发送
        Observable<String> just = Observable.just("1", "2");

        just.subscribe(observer);
    }

    private Observer<String> getCommonObserver() {
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println("subscribe!");
            }

            @Override
            public void onNext(@NonNull String message) {
                System.out.println("message:" + message);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("error:" + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };
        return observer;
    }
}
