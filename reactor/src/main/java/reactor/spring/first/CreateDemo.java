package reactor.spring.first;

import reactor.core.publisher.Flux;
import reactor.spring.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author WangHan
 * @Create 2022/2/17 12:55 上午
 */
public class Demo01 {

    public static void main(String[] args) {
        Book book1 = new Book("1", "math");
        Book book2 = new Book("2", "english");
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);

        Flux.just(book1, book2).doOnNext(System.out::println).subscribe();
        Flux.just(book1, book2).switchIfEmpty(subscriber -> ).subscribe();

        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run ...");
        }

    }
}
