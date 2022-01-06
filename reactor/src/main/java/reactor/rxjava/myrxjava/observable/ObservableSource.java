package com.oowanghan.ractor.rxjava.myrxjava.observable;

import com.oowanghan.ractor.rxjava.myrxjava.observer.Observer;

public interface ObservableSource<T> {

    void subscribe(Observer<? super T> observer);
}
