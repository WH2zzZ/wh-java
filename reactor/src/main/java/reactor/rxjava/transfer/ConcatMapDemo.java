package reactor.rxjava.transfer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

/**
 * @Author WangHan
 * @Create 2021/6/5 8:54 下午
 */
public class ConcatMapDemo {


    /**
     * 转换操作符号 - concatMap
     * 有序的对元素序列做处理,flat是无序的
     */
    @Test
    public void test01(){
        //可以适用于网络请求场景中，比如：
        Observable
            .just("1", "2", "3", "4")
            .concatMap(result -> {
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
