package com.guc.lock;

import java.util.concurrent.locks.ReentrantLock;

class LTicket {
    private int number = 100;
    // 创建可重入锁
    private final ReentrantLock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + (number--) + "张票，还剩" + number + "张票");
            }
        } finally {
            lock.unlock();
        }
    }
}
public class LSaleTicket {
        public static void main(String[] args) {
            LTicket ticket = new LTicket();

            new Thread(() -> {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }, "A").start();

            new Thread(() -> {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }, "B").start();

            new Thread(() -> {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }, "C").start();
        }
}
