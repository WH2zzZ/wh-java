package com.oowanghan.ractor.rxjava.myrxjava.event;

/**
 * 事件发射器
 */
public interface Emitter<T> {

    void onNext(T t);

    void onComplete();

    void onError(Throwable throwable);
}
