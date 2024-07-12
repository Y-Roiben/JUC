package com.guc.readWrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class 锁降级 {
    public static void main(String[] args) {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

//        readLock.lock();
//        System.out.println("获取读锁");
//        writeLock.lock();
//        System.out.println("获取写锁");
//        writeLock.unlock();
//        System.out.println("释放写锁");
//        readLock.unlock();
//        System.out.println("释放读锁");


        // 锁降级
        writeLock.lock();
        System.out.println("获取写锁");
        readLock.lock();
        System.out.println("获取读锁");
        writeLock.unlock();
        System.out.println("释放写锁");
        readLock.unlock();
        System.out.println("释放读锁");

    }
}
