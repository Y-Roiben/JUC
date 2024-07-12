package com.guc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo1 {
    public static void main(String[] args) {
        // 线程池
//        ExecutorService pool = Executors.newFixedThreadPool(5); // 最大线程数 5个窗口

//        ExecutorService pool = Executors.newSingleThreadExecutor(); // 一池一线程

        ExecutorService pool = Executors.newCachedThreadPool(); // int 最大线程数
        // 处理10个任务
        try {
            for (int i = 0; i < 20; i++) {
                pool.execute(() -> System.out.println(Thread.currentThread().getName() + " - " + "正在执行任务"));
//                Future<?> submit = pool.submit(()
//                        ->{
//                    System.out.println(Thread.currentThread().getName() + " - " + "正在执行任务");
//                    return "任务结果";
//                });
//                Object object = submit.get();
//                System.out.println(object);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            pool.shutdown();
        }

    }
}
