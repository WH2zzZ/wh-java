package com.oowanghan.ractor.rxjava.myrxjava.observable.operater.trasfer;

import cn.hutool.core.lang.func.Func;
import com.oowanghan.ractor.rxjava.myrxjava.observable.Observable;
import com.oowanghan.ractor.rxjava.myrxjava.observable.ObservableSource;
import com.oowanghan.ractor.rxjava.myrxjava.observable.operater.AbstractObservableWithUpStream;
import com.oowanghan.ractor.rxjava.myrxjava.observer.Observer;

import java.util.function.Function;

/**
 * @Author WangHan
 * @Create 2021/6/6 7:16 下午
 */
public class MapObservable<T, U> extends AbstractObservableWithUpStream<T, U> {

    Function<T, U> function;

    public MapObservable(ObservableSource<T> source, Function<T, U> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<? super U> observer) {
        source.subscribe(new MapObserver<T, U>(function, observer));
    }

    static class MapObserver<T, U> implements Observer<T> {

        Function<T, U> function;
        /**
         * 外部传入进来的观察者，需要对这个观察者进行功能扩展
         */
        Observer<? super U> observer;

        public MapObserver(Function<T, U> function, Observer<? super U> observer) {
            this.function = function;
            this.observer = observer;
        }

        @Override
        public void onSubscribe() {
        }

        /**
         * 核心操作
         * @param t
         */
        @Override
        public void onNext(T t) {
            U u = function.apply(t);
            observer.onNext(u);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }

        @Override
        public void onError(Throwable throwable) {
            observer.onError(throwable);
        }
    }
}
