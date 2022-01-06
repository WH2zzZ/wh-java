package reactor.rxjava.myrxjava.observable;

import reactor.rxjava.myrxjava.observer.Observer;

public interface ObservableSource<T> {

    void subscribe(Observer<? super T> observer);
}
