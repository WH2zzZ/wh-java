package reactor.rxjava.myrxjava.observable.operater;


/**
 * 统一装饰类
 * @Author WangHan
 * @Create 2021/6/6 7:15 下午
 */

import reactor.rxjava.myrxjava.observable.Observable;
import reactor.rxjava.myrxjava.observable.ObservableSource;

/**
 * 这里继承的Observable其实是 转换之后的Observable
 *
 * 下面的属性 ObservableSource 其实是被观察者的源，也就是上游传下来的，也就是说
 *
 * 写代码其实是 从上游写到下游，
 * 但是程序执行是从下游开始一层一层往上游执行
 */
public abstract class AbstractObservableWithUpStream<T, U> extends Observable<U> {

    protected final ObservableSource<T> source;

    public AbstractObservableWithUpStream(ObservableSource<T> source) {
        this.source = source;
    }
}
