package com.oowanghan.ractor.rxjava.transfer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

/**
 * @Author WangHan
 * @Create 2021/6/5 8:54 下午
 */
public class FlatMapDemo {


    /**
     * 转换操作符号 - flatMap
     *
     * flatMap是Rxjava中一个强大的操作符，在实际项目中，应用的场景很多，比如开始列举的化解循环嵌套，
     * 还有一种场景在我们实际项目中是非常多的，那就是连续请求两个接口，第一个接口的返回值是第二个接口的请求参数，
     * 在这种情况下，以前我们会在一个请求完成后，在onResponse中获取结果再请求另一个接口。这种接口嵌套，代码看起来是非常丑陋的，
     * 运用flatMap就能很好的解决这个问题。代码看起来非常优雅而且逻辑清晰。
     *
     * concatMap和flatMap的功能是一样的， 将一个发射数据的Observable变换为多个Observables，然后将它们发射的数据放进一个单独的Observable。
     * 只不过最后合并ObservablesflatMap采用的merge，而concatMap采用的是连接(concat)。
     * 总之一句一话,他们的区别在于：
     *      concatMap是有序的，
     *      flatMap是无序的，
     *      concatMap最终输出的顺序与原序列保持一致，而flatMap则不一定，有可能出现交错。
     */
    @Test
    public void test01(){
        //可以适用于网络请求场景中，比如：
        Observable
                .just("1", "2", "3", "4", "5", "6")
                .flatMap(result -> {
                    //这一步还有可能是异步操作，所以产生一个新的被观察者
                    return Observable.just("new_" + result);
                })
                .subscribe(getCommonObserver());

        for (int i = 0; i < 10; i++) {
            System.out.println("app is run...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Observer<String> getCommonObserver() {
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println("subscribe!");
            }

            @Override
            public void onNext(@NonNull String message) {
                System.out.println("message:" + message);
            }

            @Override
            public void onError(@NonNull Throwable throwable) {
                System.out.println("error:" + throwable.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("complete");
            }
        };
        return observer;
    }
}
