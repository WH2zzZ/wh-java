package com.oowanghan.thread.future;

import org.junit.jupiter.api.Test;

import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;

/**
 *
 * @Author WangHan
 * @Create 2021/5/16 1:19 上午
 */
public class CompletableFuture_Demo {

    /**
     * Future弊端：
     *
     * Future不能主动设置计算结果值，一旦调用get()进行阻塞等待，要么当计算结果产生，要么超时，才会返回。
     *
     * CompletableFuture能够主动设置计算的结果值（主动终结计算过程，即completable），从而在某些场景下主动结束阻塞等待。
     */
    @Test
    public void whyUseCompletableFuture() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            try{
                printInfo("supplyAsync");
                Thread.sleep(1000L);
                return "test";
            } catch (Exception e){
                return "failed test";
            }
        });
//        future.complete("manual test");
        //这里会发现使用的是complete里面的值：manual test
        System.out.println(future.join());
    }

    /**
     * 简述：
     *  supplyAsync()也可以用来创建CompletableFuture实例。
     *  通过该函数创建的CompletableFuture实例会异步执行当前传入的计算任务。
     *  在调用端，则可以通过get或join获取最终计算结果。
     *
     * 方法详解：
     *  第一种只需传入一个Supplier实例（一般使用lambda表达式），此时框架会默认使用ForkJoin的线程池来执行被提交的任务。
     *  第二种可以指定自定义的线程池，然后将任务提交给该线程池执行。
     *
     * 注意：runAsync适合创建不需要返回值的计算任务，其他同supplyAsync()
     */
    @Test
    public void testSupplyAsync(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            printInfo("1号位结束运行");
            return "1";
        });

        System.out.println("CompletableFuture 运行结束" + future.join());
    }

    /**
     * thenApply（）转换的是泛型中的类型，是同一个CompletableFuture，相当于将CompletableFuture<T> 转换成CompletableFuture<U>
     *
     * thenCompose（）用来连接两个CompletableFuture，是生成一个新的CompletableFuture。
     */
    @Test
    public void testThenApply(){
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            printInfo("1号位运行");
            sleep(2000);
            printInfo("1号位结束");
            return "1";
        });
        CompletableFuture<String> future2 = future1.thenApply(param -> {
            sleep(2000);
            printInfo("2号位结束运行:" + param);
            return "2";
        });

        printMainInfo();

        System.out.println("CompletableFuture 运行结束" + future2.join());
    }

    /**
     * thenCompose 可以用于组合多个CompletableFuture，将前一个任务的返回结果作为下一个任务的参数，它们之间存在着先后顺序。
     *
     * thenCompose方法会在某个任务执行完成后，将该任务的执行结果作为方法入参然后执行指定的方法，
     * 该方法会返回一个新的CompletableFuture实例。
     */
    @Test
    public void testThenCompose(){
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            printInfo("1号位运行");
            sleep(3000);
            printInfo("1号位结束");
            return "1";
        });

        CompletableFuture<String> future2 = future1
//                .thenComposeAsync(param -> CompletableFuture.supplyAsync(() -> {
                .thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            printInfo("2号位开始运行");
            printInfo("2号位获取到的param:" + param);
            printInfo("2号位结束运行");
            return "2";
        }));

        System.out.println("CompletableFuture 运行结束" + future2.join());
    }

    /**
     * thenCombine
     * 允许前后连接的两个任务可以并行执行（后置任务不需要等待前置任务执行完成），
     * 最后当两个任务均完成时，再将其结果同时传递给下游处理任务，从而得到最终结果
     *
     * 由于默认会会使用ForkJoin线程池，由于第一个任务执行过快，所以会出现同一个线程id的情况
     *
     * thenCombineAsync()
     * 区别在于，最后合并的任务会交给线程池去解决
     */
    @Test
    public void testCombineAsync(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            printInfo("1号位结束运行");
            return "1";
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
//        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            printInfo("2号位结束运行");
            return "2";
        }), (result1, result2) -> {
            printInfo("Combine号位结束运行");
            return result1 + result2;
        });

        System.out.println("CompletableFuture 运行结束，结果：" + future.join());
    }

    private void printMainInfo() {
        for (int i = 0; i < 10; i++) {
            sleep(1000);
            System.out.println(Thread.currentThread().getId() + "| i:" + i + "主程序run");
        }
    }

    private void printInfo(String info) {
        info = new StringJoiner("|")
                .add(Thread.currentThread().getId() + "")
                .add(info)
                .toString();
        System.out.println(info);
    }

    private void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
