package reactor.rxjava.myrxjava.observable.operater.schedule.thread;

/**
 * @Author WangHan
 * @Create 2021/6/7 12:33 上午
 */
public abstract class Scheduler {

    public abstract Worker createWorker();

    public interface Worker {
        void schedule(Runnable runnable);
    }
}
