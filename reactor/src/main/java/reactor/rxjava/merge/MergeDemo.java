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
public class MergeDemo {


    /**
     * 合并操作符号 - merge
     * 和concat一样，只不过merge是并行的，concat是串行的
     * 将两个被观察者合并
     */
    @Test
    public void test01(){
        Observable.merge(
                Observable.just("111"),
                Observable.just("222")
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
