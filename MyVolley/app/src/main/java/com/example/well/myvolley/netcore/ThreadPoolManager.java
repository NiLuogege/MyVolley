package com.example.well.myvolley.netcore;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Well on 2016/12/14.
 * <p>
 * 线程池 用来管理所有的httpTack
 */

public class ThreadPoolManager {
    private static ThreadPoolManager mPoolManager = new ThreadPoolManager();

    private ThreadPoolExecutor mThreadPoolExecutor;//线程池

    private LinkedBlockingDeque<Future<?>> service = new LinkedBlockingDeque<>();//阻塞队列


    private RejectedExecutionHandler mExecutionHandler = new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                service.put(new FutureTask<Object>(r, null));//将被拒绝的任务重新扔回队列中
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    public static ThreadPoolManager getInstance() {
        return mPoolManager;
    }

    private ThreadPoolManager() {
        int cpu_count = Runtime.getRuntime().availableProcessors();
        int core_pool_size = cpu_count + 1;
        int maximum_pool_size = cpu_count * 2 + 1;
        mThreadPoolExecutor = new ThreadPoolExecutor(core_pool_size, maximum_pool_size, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), mExecutionHandler);
        mThreadPoolExecutor.execute(mRunnable);
    }

    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            while (true) {
                FutureTask futureTask = null;

                try {
                    futureTask = (FutureTask) service.take();//阻塞函数,有就取没有就阻塞
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (futureTask != null) {
                    mThreadPoolExecutor.execute(futureTask);
                }
            }
        }
    };

    public <T> void execute(FutureTask<T> futureTask) {
        if (futureTask != null) {
            try {
                service.put(futureTask);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
