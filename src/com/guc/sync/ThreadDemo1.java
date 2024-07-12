package com.guc.sync;

class share{
    private int number = 0;

    public synchronized void increment() throws InterruptedException {
        // 1.判断
        while (number != 0) {
            this.wait();
        }
        // 2.干活
        number++;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        // 3.通知所有线程
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        // 1.判断
        while (number == 0) {
            this.wait();
        }
        // 2.干活
        number--;
        System.out.println(Thread.currentThread().getName() + "\t" + number);
        // 3.通知
        this.notifyAll();
    }

}

public class ThreadDemo1 {
    public static void main(String[] args) {
        share share = new share();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    share.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();

    }

}
