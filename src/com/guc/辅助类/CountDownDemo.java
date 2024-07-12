package com.guc.辅助类;

import java.util.concurrent.CountDownLatch;

public class CountDownDemo {
    public static void main(String[] args) {
        // 6个同学陆续离开教室，最后关门
//        for (int i = 1; i <= 6; i++) {
//            new Thread(() -> {
//                System.out.println(Thread.currentThread().getName() + "号同学离开教室");
//            }, String.valueOf(i)).start();
//        }
//        System.out.println(Thread.currentThread().getName() + "关门离开教室");

        CountDownLatch countDownLatch = new CountDownLatch(6);  // 倒计时计数器
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "号同学离开教室");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "关门离开教室");
    }
}
