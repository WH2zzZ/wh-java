package reactor.spring.first;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.spring.Book;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author WangHan
 * @Create 2022/2/17 1:48 上午
 */
public class SubscribeDemo {

    public static void main(String[] args) {
        Book book1 = new Book("1", "math");
        Book book2 = new Book("2", "english");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        Flux.fromIterable(books)
                .doOnSubscribe(subscription -> System.out.println("start subscribe"))
                .doOnNext(book -> System.out.println("book id : " + book.getId()))
                .doOnComplete(() -> System.out.println("complete"))
                .subscribe();
        Mono<List<Integer>> listMono = Flux.just(1, 2, 3).collectList();

    }

    @Test
    public void demo02() {
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        System.out.println("========");
        Flux.interval(Duration.of(0, ChronoUnit.SECONDS), Duration.of(1, ChronoUnit.SECONDS))
                .buffer(Duration.of(5, ChronoUnit.SECONDS))
                .take(2).toStream().forEach(System.out::println);
        System.out.println("=========");
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        System.out.println("=========");
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
    }

    @Test
    public void demo3(){
        Flux.create(sink -> {
                    sink.next(Thread.currentThread().getName());
                    sink.complete();
                })
                .delayElements(Duration.ofSeconds(1))
                .publishOn(Schedulers.single())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .publishOn(Schedulers.elastic())
                .map(x -> String.format("[%s] %s", Thread.currentThread().getName(), x))
                .subscribeOn(Schedulers.parallel())
                .subscribe(System.out::println);

        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
