package reactor.rxjava.myrxjava.observable;


import reactor.rxjava.myrxjava.event.Emitter;

public interface ObservableOnSubscribe<T> {

    void subscribe(Emitter<? super T> emitter);
}
