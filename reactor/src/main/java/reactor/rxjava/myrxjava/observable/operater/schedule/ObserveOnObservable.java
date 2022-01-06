package com.oowanghan.ractor.rxjava.myrxjava.observable.operater.schedule;

import com.oowanghan.ractor.rxjava.myrxjava.observable.ObservableSource;
import com.oowanghan.ractor.rxjava.myrxjava.observable.operater.AbstractObservableWithUpStream;
import com.oowanghan.ractor.rxjava.myrxjava.observable.operater.schedule.thread.Scheduler;
import com.oowanghan.ractor.rxjava.myrxjava.observer.Observer;

/**
 * ObserveOnObservable 和 ScheduleOnObservable 的核心区别在于
 * ScheduleOnObservable 是为了在生产事件的时候是异步的
 * ObserveOnObservable 是为了在处理事件的时候是异步的
 *
 * 所以在生产事件的时候 异步需要在 subscribeActual 使用异步线程来处理订阅事件
 * 而想在处理事件的时候使用异步，则不能在 subscribeActual 中开线程处理，
 * 而应该是通过装饰观察者类的onNext,onComplete... 方法中使用线程处理
 *
 * @Author WangHan
 * @Create 2021/6/7 12:32 上午
 */
public class ObserveOnObservable<T> extends AbstractObservableWithUpStream<T, T> {

    private final Scheduler scheduler;

    public ObserveOnObservable(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        source.subscribe(new ObserveOnObserver<T>(observer, scheduler.createWorker()));
    }

    static class ObserveOnObserver<T> implements Observer<T> {

        final Observer<? super T> observer;

        private Scheduler.Worker worker;

        public ObserveOnObserver(Observer<? super T> observer, Scheduler.Worker worker) {
            this.observer = observer;
            this.worker = worker;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            worker.schedule(() -> observer.onNext(t));
        }

        @Override
        public void onComplete() {
            worker.schedule(() -> observer.onComplete());
        }

        @Override
        public void onError(Throwable throwable) {
            worker.schedule(() -> observer.onError(throwable));
        }
    }

}
