package reactor.rxjava.merge;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

/**
 * @Author WangHan
 * @Create 2021/6/5 8:54 下午
 */
public class ZipDemo {


    /**
     * 合并操作符号 - zip
     * 合并多个被观察者的元素序列， 根据被观察者发送事件的顺序一个个结合
     * 若两个被观察者的元素数量不一致，按最少的被观察者的元素数量为标准
     */
    @Test
    public void test01(){
        Observable.zip(
                Observable.just("a", "b", "c", "d"),
                Observable.just("1", "2", "3", "4", "5"),
                (result1, result2) -> {
                    return result1 + ":" + result2;
                }
        ).subscribe(getCommonObserver());

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
