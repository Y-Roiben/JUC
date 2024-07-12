package com.guc.辅助类;

import java.util.concurrent.CyclicBarrier;

// 循环栅栏
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        // 集齐7颗龙珠召唤神龙
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("召唤神龙成功");
        });

        for (int i = 1; i <= 7; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "收集了" + temp + "颗龙珠");
                try {
                    cyclicBarrier.await();  // 等待
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
