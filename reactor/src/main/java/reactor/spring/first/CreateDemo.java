package reactor.spring.first;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.spring.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author WangHan
 * @Create 2022/2/17 12:55 上午
 */
public class CreateDemo {

    public static void main(String[] args) {
        Book book1 = new Book("1", "math");
        Book book2 = new Book("2", "english");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        Flux<Book> just = Flux.just(book1, book2);
        Flux<Book> fromStream = Flux.fromStream(books.stream());
        Flux<Book> fromIterable = Flux.fromIterable(books);
        Flux<Integer> range = Flux.range(1, 10);

        // 动态创建
        Flux.generate(() -> 1, (state, sink) -> {
            sink.next("message : " + state + " ");
            if (state == 10) {
                sink.complete();
            }
            return state + 1;
        }).doOnNext(System.out::print).subscribe();

        Mono.just(book1)
                .doOnSubscribe(subscription -> System.out.print("mono-just:"))
                .doOnNext(System.out::println)
                // 会阻塞，会等到所有数据都处理到这里
                .block();

        Mono.fromRunnable(() -> System.out.println(Thread.currentThread().getName()))
                .doOnSubscribe(subscription -> System.out.print("fromRunnable:"))
                .publishOn(Schedulers.boundedElastic())
                .subscribe();

        Mono.fromCallable(() -> Thread.currentThread().getName())
                .doOnSubscribe(subscription -> System.out.print("fromCallable:"))
                .doOnSuccess(s -> System.out.println(s))
                .publishOn(Schedulers.elastic())
                .subscribe();
    }
}
