package reactor.spring.first;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @Author WangHan
 * @Create 2022/3/19 5:56 下午
 */
public class ZipWith {

    @Test
    public void zipWith() {
        Flux.just("I", "You")
                .zipWith(Flux.just("Win", "Lose"))
                .zipWith(Flux.just("hh", "gg"))
                .subscribe(System.out::println);
        Flux.just("I", "You")
                .zipWith(Flux.just("Win", "Lose"),
                        (s1, s2) -> String.format("%s!%s!", s1, s2))
                .subscribe(System.out::println);
    }

    @Test
    public void merge() {
        Flux.merge(
                        Flux.interval(
                                Duration.of(0, ChronoUnit.MILLIS),
                                Duration.of(100, ChronoUnit.MILLIS)).take(2),
                        Flux.interval(
                                Duration.of(50, ChronoUnit.MILLIS),
                                Duration.of(100, ChronoUnit.MILLIS)).take(2))
                .toStream()
                .forEach(System.out::println);
        System.out.println("---");
        Flux.mergeSequential(Flux.interval(
                                Duration.of(0, ChronoUnit.MILLIS),
                                Duration.of(100, ChronoUnit.MILLIS)).take(2),
                        Flux.interval(
                                Duration.of(50, ChronoUnit.MILLIS),
                                Duration.of(100, ChronoUnit.MILLIS)).take(2))
                .toStream()
                .forEach(System.out::println);
    }

}
