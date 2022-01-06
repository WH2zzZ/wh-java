package reactor.rxjava.transfer;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import org.junit.Test;

import java.util.List;

/**
 * @Author WangHan
 * @Create 2021/6/5 8:54 下午
 */
public class BufferDemo {


    /**
     * 转换操作符号 - buffer
     * 建立一个指定大小的缓冲区，将事件放进去
     */
    @Test
    public void test01(){
        Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
                .buffer(3).subscribe(getCommonObserver());

        for (int i = 0; i < 10; i++) {
            System.out.println("app is run...");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Observer<List<String>> getCommonObserver() {
        Observer<List<String>> observer = new Observer<List<String>>() {

            @Override
            public void onSubscribe(@NonNull Disposable disposable) {
                System.out.println("subscribe!");
            }

            @Override
            public void onNext(@NonNull List<String> message) {
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
