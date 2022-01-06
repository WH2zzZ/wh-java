package reactor.rxjava.create;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Author WangHan
 * @Create 2021/6/5 8:54 下午
 */
public class OtherDemo {


    /**
     * 创建操作符号 - just
     * 创建操作符号 - defer 懒加载被观察者
     * 创建操作符号 - timer
     * 创建操作符号 - interval
     * 创建操作符号 - intervalRamge
     * 创建操作符号 - range
     * 创建操作符号 - ramgeLong
     * 创建操作符号 - empty 直接发送complete
     * 创建操作符号 - never 直接不发送事件
     * 创建操作符号 - error 直接发送error
     */
    @Test
    public void test01(){
        Observable.interval(1, TimeUnit.MINUTES).subscribe();
        Observable.timer(1, TimeUnit.MINUTES).subscribe();
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
