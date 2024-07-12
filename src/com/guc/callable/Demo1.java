package com.guc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable {
    @Override
    public void run() {
        System.out.println("Runnable");
    }
}

class MyThread2 implements Callable<Integer> {
    public MyThread2() {
    }

    @Override
    public Integer call() {
        return 200;
    }
}

public class Demo1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Thread(new MyThread1(), "runnable").start();

        MyThread2 myThread2 = new MyThread2();

        FutureTask<Integer> futureTask = new FutureTask<>(myThread2);
        new Thread(futureTask, "callable").start();
        Integer i = futureTask.get();

        System.out.println("callable 返回的值" + i);

    }
}
