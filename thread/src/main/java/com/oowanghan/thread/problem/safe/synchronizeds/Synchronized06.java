package com.oowanghan.thread.thread.problem.safe.synchronizeds;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.Lock;

/**
 *
 */
@Slf4j
public class Synchronized06 {


    public static void main(String[] args) {
        Dog dog = new Dog();
        System.out.println(ClassLayout.parseInstance(dog).toPrintable());
    }

}
class Dog{

}