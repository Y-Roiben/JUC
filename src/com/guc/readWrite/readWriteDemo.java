package com.guc.readWrite;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class cache{
    private volatile Map<String, String> map = new HashMap<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, String value){
        Lock writeLock = readWriteLock.writeLock();
        writeLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写入");
            TimeUnit.MICROSECONDS.sleep(300);

            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(String key){
        Lock readLock = readWriteLock.readLock();
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始读取");
            TimeUnit.MICROSECONDS.sleep(300);

            String o = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成   " + o);
            return o;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            readLock.unlock();
        }

    }
}
public class readWriteDemo {
    public static void main(String[] args) {
        cache cache = new cache();
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.put(temp + "", temp+"");
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                cache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }

}
