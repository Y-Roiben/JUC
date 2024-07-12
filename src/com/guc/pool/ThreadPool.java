package com.guc.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    public static void main(String[] args) {
        /*
            参数一：核心线程数量           不能小于0
            参数二：最大线程数不能小于0，   最大数量>=核心线程数量
            参数三：空闲线程最大存活时间    不能小于e
            参数四：时间单位用            TimeUnit指定
            参数五：任务队列              不能为null
            参数六：创建线程工厂           不能为null
            参数七：任务的拒绝策略         不能为null
        * */
//        自定义线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 6, 60,
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(4), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        // 处理10个任务
        try {
            for (int i = 0; i < 10; i++) {
                pool.execute(() -> System.out.println(Thread.currentThread().getName() + " - " + "正在执行任务"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }
    }
}
