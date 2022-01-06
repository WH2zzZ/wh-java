package com.oowanghan.ractor.rxjava.create;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

/**
 * @Author WangHan
 * @Create 2021/6/5 8:54 下午
 */
public class FromDemo {


    /**
     * 创建操作符号 - from
     */
    @Test
    public void test01(){
        //和just区别就在于可以无限创建参数
        Observable.fromArray("1", "2").subscribe(getCommonObserver());
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
