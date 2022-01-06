package reactor.rxjava.transfer;

import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import org.junit.Test;

/**
 * @Author WangHan
 * @Create 2021/6/5 8:54 下午
 */
public class ComposeDemo {


    /**
     * 转换操作符号 - compose
     * 可将一些操作封装起来，起到一个复用的操作
     */
    @Test
    public void test01(){

       Observable.create(new ObservableOnSubscribe<String>() {
           @Override
           public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
               emitter.onNext("1");
               emitter.onNext("2");
               emitter.onNext("3");
               emitter.onNext("4");
               emitter.onComplete();
           }
       }).compose(new ComposeTransformer<String>()).subscribe(getCommonObserver());
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

    private static class ComposeTransformer<T> implements ObservableTransformer<T, T> {

        @NonNull
        @Override
        public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
            return upstream.subscribeOn(Schedulers.io()).observeOn(Schedulers.io());
        }
    }
}
