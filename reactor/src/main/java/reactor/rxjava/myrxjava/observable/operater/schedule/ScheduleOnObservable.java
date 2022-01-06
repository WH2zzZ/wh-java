package reactor.rxjava.myrxjava.observable.operater.schedule;

import reactor.rxjava.myrxjava.observer.Observer;
import reactor.rxjava.myrxjava.observable.ObservableSource;
import reactor.rxjava.myrxjava.observable.operater.AbstractObservableWithUpStream;
import reactor.rxjava.myrxjava.observable.operater.schedule.thread.Scheduler;

/**
 * @Author WangHan
 * @Create 2021/6/7 12:32 上午
 */
public class ScheduleOnObservable<T> extends AbstractObservableWithUpStream<T, T> {

    private final Scheduler scheduler;

    public ScheduleOnObservable(ObservableSource<T> source, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        scheduler.createWorker()
                .schedule(new SubscribeTask(new SubscribeOnObserver<T>(observer)));
    }

    static class SubscribeOnObserver<T> implements Observer<T> {

        final Observer<? super T> observer;

        public SubscribeOnObserver(Observer<? super T> observer) {
            this.observer = observer;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onNext(T t) {
            observer.onNext(t);
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

    class SubscribeTask implements Runnable {

        final SubscribeOnObserver<T> parent;

        public SubscribeTask(SubscribeOnObserver<T> parent) {
            this.parent = parent;
        }

        @Override
        public void run() {
            source.subscribe(parent);
        }
    }

}
