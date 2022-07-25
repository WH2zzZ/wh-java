package reactor.spring.first;

import org.junit.Test;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

/**
 * @Author WangHan
 * @Create 2022/3/19 6:18 下午
 */
public class Publish {

    @Test
    public void publish() throws InterruptedException {
//        Scheduler scheduler1 = Schedulers.newParallel("publish-scheduler", 4);
        Scheduler scheduler1 = Schedulers.boundedElastic();
        Scheduler scheduler2 = Schedulers.newParallel("subscribe-scheduler", 4);


        Flux<String> flux1 = Flux
                .range(1, 100)
                .map(i -> "first:" + Thread.currentThread().getName() + "  " + i)
                // 让流走到这里时，会切换到publishOn指定的线程当中
                .publishOn(scheduler1)
                .map(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    return "publish: " + Thread.currentThread().getName() + "->" + i;
                })
                // 让流从一开始就会进入这个线程处理当中
                .subscribeOn(scheduler2);


        Flux<String> flux2 = Flux
                .range(101, 200)
                .map(i -> "first:" + Thread.currentThread().getName() + "  " + i)
                // 让流走到这里时，会切换到publishOn指定的线程当中
                .publishOn(scheduler1)
                .map(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    return "publish: " + Thread.currentThread().getName() + "->" + i;
                })
                // 让流从一开始就会进入这个线程处理当中
                .subscribeOn(scheduler2);

        flux1.subscribe(s -> System.out.println("1subscribe: " + Thread.currentThread().getName() + " - " + s));
        flux2.subscribe(s -> System.out.println("2subscribe: " + Thread.currentThread().getName() + " - " + s));
        while (true) {
            System.out.println("wait...");
            Thread.sleep(900);
        }
    }
}
