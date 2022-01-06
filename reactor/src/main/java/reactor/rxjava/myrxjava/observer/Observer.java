package com.oowanghan.ractor.rxjava.myrxjava.observer;

public interface Observer<T> {

    void onSubscribe();

    void onNext(T t);

    void onComplete();

    void onError(Throwable throwable);
}
