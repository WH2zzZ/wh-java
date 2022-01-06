package reactor.rxjava.create;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.schedulers.NewThreadWorker;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import org.junit.Test;

/**
 * @Author WangHan
 * @Create 2021/6/4 12:27 上午
 */
public class SubjectDemo {

    /**
     * Subject 即是观察者又是被观察者
     * Rxjava：提供四种 Subject:
     *
     *  Asyncsubject：无论发射多少条数据，无论在订前发射还是在订后发射，都只会收到最后一条发射的数据。
     *  BehaviorSubject：只会接收到订阅前最后一条发射的数据以及订阅之后所有的数据。
     *  ReplaySubject：会接收到全部数据，无论订阅前后。
     *  PublishSubject：只会接收到订阅之后的所有数据。
     */
    @Test
    public void test01() {
        AsyncSubject<String> subject = AsyncSubject.create();
//        BehaviorSubject<String> subject = BehaviorSubject.create();
//        ReplaySubject<String> subject = ReplaySubject.create();
//        PublishSubject<String> subject = PublishSubject.create();
        subject.onNext("1");
        subject.onNext("2");
        subject.onNext("3");
        subject.onNext("4");
        subject.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("receive message : " + s);
            }
        });

        subject.onNext("a");
        subject.onNext("b");

        //必须调用 onComplete才会触发调用
        subject.onComplete();

    }


}
