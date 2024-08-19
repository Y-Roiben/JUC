package com.guc.Future;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[] args) {
        FutureTask<String> task = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "Hello";
            }
        });
        new Thread(task).start();
        try {
            System.out.println("waiting for result, 阻塞等待");
            System.out.println(task.get()); // blocking
            System.out.println("got result");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
