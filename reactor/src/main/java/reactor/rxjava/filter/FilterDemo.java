package com.oowanghan.ractor.rxjava.filter;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

/**
 * @Author WangHan
 * @Create 2021/6/4 12:27 上午
 */
public class FilterDemo {

    /**
     * 过滤操作符：满足条件的去掉
     * 筛选操作服：满足条件的留下
     * subscribeOn
     */
    @Test
    public void test01() throws InterruptedException {
        Observable.range(1, 10).filter(new Predicate<Integer>() {
            @Override
            public boolean test(@NonNull Integer s) throws Exception {
                return true;
            }
        }).subscribe(getCommonObserver());
    }

    private Observer<Integer> getCommonObserver() {
        Observer<Integer> observer = new Observer<Integer>() {

            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println(Thread.currentThread().getName() + " : subscribe!");
            }

            @Override
            public void onNext(@NonNull Integer message) {
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
