package com.oowanghan.thread.problem.threadlocal.inheritable;

public class ContextHolder {
    private static final InheritableThreadLocal<Context> holder = new InheritableThreadLocal();

    public ContextHolder() {
    }

    public static Context getContext() {
        Context context = (Context)holder.get();
        if (context == null) {
            context = new Context();
            holder.set(context);
        }

        return context;
    }

    public static void setContext(Context context) {
        holder.set(context);
    }

    public void test() {
        Thread thread = new Thread();
    }

}

class Context {

}