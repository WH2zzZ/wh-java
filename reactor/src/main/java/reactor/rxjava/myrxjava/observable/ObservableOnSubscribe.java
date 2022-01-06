package com.oowanghan.ractor.rxjava.myrxjava.observable;

import com.oowanghan.ractor.rxjava.myrxjava.event.Emitter;

public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<? super T> emitter);
}
