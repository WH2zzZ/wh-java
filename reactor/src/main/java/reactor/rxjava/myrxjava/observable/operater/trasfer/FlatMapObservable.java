package reactor.rxjava.myrxjava.observable.operater.trasfer;

import reactor.rxjava.myrxjava.observer.Observer;
import reactor.rxjava.myrxjava.observable.ObservableSource;
import reactor.rxjava.myrxjava.observable.operater.AbstractObservableWithUpStream;

import java.util.function.Function;

/**
 * 事件通知是由用户决定
 * 观察者 -> 事件处理也是用户决定的
 * subscribe 方法 作为 触发条件, 只有被观察者不是用户决定的
 * 一旦有观察者订阅后，所有的操作符 会将这个订阅事件层层上报， 并且对这个observer观察者 做层层包装装饰， 最终到了ObservableCreate
 * 这个时候被观察者 会持有 一个用户定义的事件发送器，事件发送器 持有 观察者的引用
 * 事件发送器也有一个onSubscribe方法，这个方法里面会做一些 发送器 自己的事件处理
 * 然后调用观察者的 onSubscribe ， 由于上面说的层层包装， 所以在执行的时候，从最外层开始执行，一直向里传递，
 * 最终传递到用户自己定义的观察者的 onSubscribe方法
 *
 * 不过注意，可能最上层类在执行
 *
 * 注意！！！ 所以是两个装饰模式的使用 此处较难理解 注意思考！！！
 * AbstractObservableWithUpStream在装饰了被观察者的同时
 * FlatMapObservable也装饰了观察者
 * @Author WangHan
 * @Create 2021/6/6 7:16 下午
 */
public class FlatMapObservable<T, U> extends AbstractObservableWithUpStream<T, U> {

    Function<T, ObservableSource<U>> function;

    public FlatMapObservable(ObservableSource<T> source, Function<T, ObservableSource<U>> function) {
        super(source);
        this.function = function;
    }

    @Override
    protected void subscribeActual(Observer<? super U> observer) {
        source.subscribe(new FlatMapObserver<T, U>(function, observer));
    }

    static class FlatMapObserver<T, U> implements Observer<T> {

        Function<T, ObservableSource<U>> function;
        /**
         * 外部传入进来的观察者，需要对这个观察者进行功能扩展
         */
        Observer<? super U> observer;

        public FlatMapObserver(Function<T, ObservableSource<U>> function, Observer<? super U> observer) {
            this.function = function;
            this.observer = observer;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        /**
         * 核心操作
         * @param t
         */
        @Override
        public void onNext(T t) {
            ObservableSource<U> observable= function.apply(t);
            observable.subscribe(new Observer<U>() {
                @Override
                public void onSubscribe() {

                }

                @Override
                public void onNext(U u) {
                    observer.onNext(u);
                }

                @Override
                public void onComplete() {

                }

                @Override
                public void onError(Throwable throwable) {

                }
            });
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
}
