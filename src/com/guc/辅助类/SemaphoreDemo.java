package com.guc.辅助类;

import java.util.concurrent.Semaphore;

/**
 * 信号灯
 * */

public class SemaphoreDemo {
    public static void main(String[] args) {
        // 6辆汽车抢3个车位
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "抢到车位");
                    // 停车3秒
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}
