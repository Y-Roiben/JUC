package com.guc.异步回调;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.BiConsumer;

/**
 * 异步回调
 * */
public class Demo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 没有返回值的异步回调
        CompletableFuture<Void> completableFuture1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " - " + "completableFuture1 异步回调");
        });
//        completableFuture1.get();

        // 有返回值的异步回调
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " - " + "completableFuture2 异步回调");
            return 1024;
        });
        completableFuture2.whenComplete(new BiConsumer<Integer, Throwable>() {
            @Override
            public void accept(Integer integer, Throwable throwable) {
                System.out.println("异步回调结果：" + integer);
                System.out.println("异常信息：" + throwable);
            }
        });
        completableFuture2.get();
    }
}
