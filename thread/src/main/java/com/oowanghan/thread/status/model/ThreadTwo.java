package com.oowanghan.thread.status.model;

public class ThreadTwo implements Runnable {

    private final String NAME = "线程2";

    public static int count = 1;

    @Override
    public void run() {
        while (true){
            System.out.println("====" + NAME + "第" + count + "次运行====");
            count++;
        }
    }

    public static void main(String[] args) {

        Thread thread = new Thread(new ThreadTwo());
        thread.start();
    }
}
