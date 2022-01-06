package reactor.rxjava.myrxjava.test;

import reactor.rxjava.myrxjava.observer.Observer;
import reactor.rxjava.myrxjava.event.Emitter;
import reactor.rxjava.myrxjava.observable.Observable;
import reactor.rxjava.myrxjava.observable.ObservableOnSubscribe;
import reactor.rxjava.myrxjava.observable.ObservableSource;

import java.util.function.Function;

/**
 * @Author WangHan
 * @Create 2021/6/6 6:41 下午
 */
public class FlatMapTest {

    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<? super String> emitter) {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
                emitter.onError(new Throwable("error"));
                emitter.onComplete();
            }
        }).flatMap(new Function<String, ObservableSource<Object>>() {
            @Override
            public ObservableSource<Object> apply(String s) {
                return Observable.create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(Emitter<? super Object> emitter) {
                        emitter.onNext("new_" + s);
                    }
                });
            }
        }).subscribe(new Observer<Object>() {
            @Override
            public void onSubscribe() {
                System.out.println("subscribe");
            }

            @Override
            public void onNext(Object s) {
                System.out.println("message:" + s);
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("error:" + throwable.getMessage());
            }
        });
    }
}
