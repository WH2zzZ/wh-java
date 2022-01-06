package com.oowanghan.asm.sample;


@Deprecated
public class HelloWorld{

    private void test(boolean flag) throws InterruptedException {
        if (flag){
            Thread.sleep(1000);
        }else {
            System.out.println("false");
        }
        System.out.println("end");
    }

    public static void main(String[] args) throws InterruptedException {
        new HelloWorld().test(true);
        Thread.sleep(1000);
        long id = Thread.currentThread().getId();
        System.out.println("thread id : " + id);
        System.out.println("main running");
    }
}



