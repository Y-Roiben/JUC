package com.guc.lock;

import sun.security.provider.SHA;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class shareResource{
    private int flag = 1;
    private Lock lock = new ReentrantLock();
    // 创建三个condition对象
    // 1. 用于A线程
    private Condition c1 = lock.newCondition();
    // 2. 用于B线程
    private Condition c2 = lock.newCondition();
    // 3. 用于C线程
    private Condition c3 = lock.newCondition();

    public void print5(int loop){
        lock.lock();
        try {
            while (flag != 1){
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()
                        + "\t" + i + "\t" + "轮数：" +  loop);

            }
            // 通知
            flag = 2;
            c2.signal(); // 通知B线程
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
    public void print10(int loop){
        lock.lock();
        try {
            while (flag != 2){
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()
                        + "\t" + i + "\t" + "轮数：" + loop);

            }
            // 通知
            flag = 3;
            c3.signal(); // 通知C线程
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
    public void print15(int loop){
        lock.lock();
        try {
            while (flag != 3){
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()
                        + "\t" + i + "\t" + "轮数：" + loop);

            }
            // 通知
            flag = 1;
            c1.signal(); // 通知A线程
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}

// 线程间定制化通信
public class ThreadDemo3 {

    public static void main(String[] args) {
        shareResource shareResource = new shareResource();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print5(i);
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print10(i);
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                shareResource.print15(i);
            }
        }, "C").start();
    }
}
