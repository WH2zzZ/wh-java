package reactor.rxjava.myrxjava.test;

import reactor.rxjava.myrxjava.observer.Observer;
import reactor.rxjava.myrxjava.event.Emitter;
import reactor.rxjava.myrxjava.observable.Observable;
import reactor.rxjava.myrxjava.observable.ObservableOnSubscribe;

/**
 * @Author WangHan
 * @Create 2021/6/6 6:41 下午
 */
public class MapTest {

    public static void main(String[] args) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(Emitter<? super String> emitter) {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onNext("3");
            }
        }).map(result -> {
            return "new_" + result;
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe() {
                System.out.println("subscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("message:" + s);
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("errpr:" + throwable.getMessage());
            }
        });
    }
}
