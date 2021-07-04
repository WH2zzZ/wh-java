package com.wanghan.java8.proxy.statics;

/**
 * @Description: 狮子 实现了猫科动物接口Cat， 并实现了具体的行为。作为委托类实现
 */
public class Lion implements Cat {
    private String name;
    private int runningSpeed;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRunningSpeed() {
        return runningSpeed;
    }

    public void setRunningSpeed(int runningSpeed) {
        this.runningSpeed = runningSpeed;
    }

    public Lion() {
    }

    @Override
    public String eatFood(String foodName) {
        String eat = this.name + " Lion eat food. foodName = " + foodName;
        System.out.println(eat);
        return eat;
    }

    @Override
    public boolean running() {
        System.out.println(this.name + " Lion is running . Speed :" + this.runningSpeed);
        return false;
    }
}