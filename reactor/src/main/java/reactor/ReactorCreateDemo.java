package com.oowanghan.ractor;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 响应模式：
 * jdk9开始， java正式开始支持响应式（Reactive）编程
 * 主要讲解rxjava
 * java.util.concurrent.FLow
 * @Author WangHan
 * @Create 2021/6/4 12:27 上午
 */
public class ReactorCreateDemo {

    public static void main(String[] args) {
        //1. 创建观察者Observer
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

        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> observableEmitter) throws Exception {
                System.out.println("subscribe!!");
            }
        });

        //from(T[])/from(Iterable<? extends T>)将传入的数组或者Iterable拆分成Java对象依次发送
        Observable<String> fromObservable = Observable.fromArray("1", "2");

        //just(T...)将传入的参数依次发送
        Observable<String> just = Observable.just("1", "2");

        just.subscribe(observer);
    }
}
