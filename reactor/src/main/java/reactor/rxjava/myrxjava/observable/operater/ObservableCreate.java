package com.oowanghan.ractor.rxjava.myrxjava.observable.operater;

import com.oowanghan.ractor.rxjava.myrxjava.event.Emitter;
import com.oowanghan.ractor.rxjava.myrxjava.observable.Observable;
import com.oowanghan.ractor.rxjava.myrxjava.observable.ObservableOnSubscribe;
import com.oowanghan.ractor.rxjava.myrxjava.observer.Observer;

/**
 * @Author WangHan
 * @Create 2021/6/6 6:21 下午
 */
public class ObservableCreate<T> extends Observable<T> {

    final ObservableOnSubscribe<T> source;

    public ObservableCreate(ObservableOnSubscribe<T> source) {
        this.source = source;
    }

    @Override
    protected void subscribeActual(Observer<? super T> observer) {
        observer.onSubscribe();

        CreateEmitter<? super T> emitter = new CreateEmitter<>(observer);

        source.subscribe(emitter);
    }

    static class CreateEmitter<T> implements Emitter<T> {

        Observer<T> observer;
        boolean done;

        public CreateEmitter(Observer<T> observer) {
            this.observer = observer;
        }

        @Override
        public void onNext(T t) {
            if (done){
                return;
            }
            observer.onNext(t);
        }

        @Override
        public void onComplete() {
            if (done){
                return;
            }
            observer.onComplete();
            done = true;
        }

        @Override
        public void onError(Throwable throwable) {
            if (done){
                return;
            }
            observer.onError(throwable);
            done = true;
        }
    }

}
