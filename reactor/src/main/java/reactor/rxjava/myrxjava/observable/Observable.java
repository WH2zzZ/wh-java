package com.oowanghan.ractor.rxjava.myrxjava.observable;

import com.oowanghan.ractor.rxjava.myrxjava.observable.operater.ObservableCreate;
import com.oowanghan.ractor.rxjava.myrxjava.observable.operater.schedule.ObserveOnObservable;
import com.oowanghan.ractor.rxjava.myrxjava.observable.operater.schedule.ScheduleOnObservable;
import com.oowanghan.ractor.rxjava.myrxjava.observable.operater.schedule.thread.Scheduler;
import com.oowanghan.ractor.rxjava.myrxjava.observable.operater.trasfer.FlatMapObservable;
import com.oowanghan.ractor.rxjava.myrxjava.observable.operater.trasfer.MapObservable;
import com.oowanghan.ractor.rxjava.myrxjava.observer.Observer;

import java.util.function.Function;

/**
 * @Author WangHan
 * @Create 2021/6/6 6:14 下午
 */
public abstract class Observable<T> implements ObservableSource<T> {

    @Override
    public void subscribe(Observer<? super T> observer) {
        //和谁建立订阅，怎么建立订阅，为了保证扩展性，交给具体的开发人员实现，提供一个抽象方法
        subscribeActual(observer);
    }

    protected abstract void subscribeActual(Observer<? super T> observer);

    public static <T> Observable<T> create(ObservableOnSubscribe<T> subscribe){
        return new ObservableCreate<>(subscribe);
    }

    public <U> MapObservable<T, U> map(Function<T, U> function){
        return new MapObservable<T, U>(this, function);
    }

    public <U> FlatMapObservable<T, U> flatMap(Function<T, ObservableSource<U>> function){
        return new FlatMapObservable<T, U>(this, function);
    }

    public ScheduleOnObservable<T> subscribeOn(Scheduler scheduler){
        return new ScheduleOnObservable<T>(this, scheduler);
    }

    public ObserveOnObservable<T> observeOn(Scheduler scheduler){
        return new ObserveOnObservable<T>(this, scheduler);
    }
}
