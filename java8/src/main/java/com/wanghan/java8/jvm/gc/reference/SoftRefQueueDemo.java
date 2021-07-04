package com.wanghan.java8.jvm.gc.reference;

import lombok.Data;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @Author WangHan
 * @Create 2020/6/1 12:08 上午
 */
public class SoftRefQueueDemo {
    static ReferenceQueue<User> softQueue = null;

    public static void main(String[] args) {
        CheckRefQueue checkRefQueueThread = new CheckRefQueue();
        checkRefQueueThread.setDaemon(true);
        checkRefQueueThread.start();

        User user = new User(1, "wanghan");
        ReferenceQueue<User> referenceQueue = new ReferenceQueue<>();

        UserSoftReference userSoftReference = new UserSoftReference(user, referenceQueue);
        //释放强引用
        user = null;
        System.out.println(userSoftReference.get());
        System.gc();
        System.out.println("After gc : " + userSoftReference.get());
        //-Xmx10m
        Byte[] bytes = new Byte[1024 * 925 * 7];
        System.gc();
        System.out.println(userSoftReference.get());
    }

    public static class CheckRefQueue extends Thread{
        @Override
        public void run() {
            while (true){
                if (softQueue != null){
                    try {
                        UserSoftReference remove = (UserSoftReference) softQueue.remove();
                        if (remove != null){
                            System.out.println(remove.uid + "is delete");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static class UserSoftReference extends SoftReference<User>{

        public int uid;

        public UserSoftReference(User referent, ReferenceQueue<? super User> q) {
            super(referent, q);
            this.uid = referent.id;
        }
    }

}

@Data
class User{
    public int id;
    public String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }
}