package com.guc.Future;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;

public class CompletableTest {
    public static void main(String[] args) {
//        AnyOf();  // 获取执行最快的结果
//        AllOf();  // 执行所有任务
//        thenRun(); // 所有任务执行完毕后执行, 不关心任务的返回值, 不接受任务的返回值
//        thenAccept();  // 所有任务执行完毕后执行, 接受任务的返回值, 但不返回新的结果。
//        thenApply(); // 用来处理正常结果, 并返回新的结果
//        thenCombine(); // 组合两个 CompletableFuture 的结果, 两个任务并行顺序执行
        thenCompose(); // 组合两个 CompletableFuture 的结果, 两个任务串行执行, 第一个任务执行完毕后执行第二个任务
    }

    /**
     * 组合两个future的结果,
     * 两个future串行执行,
     * */
    private static void thenCompose() {

        // 创建第一个 CompletableFuture
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future1 " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 5;
        });

        // 创建第二个 CompletableFuture
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("future2 " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return 6;
        });

        CompletableFuture<Integer> re = future1.thenCompose((res) -> future2.thenApply((res2) -> res * 10 + res2));
        System.out.println("res = " + re.join());
    }

    /**
     * 组合两个 CompletableFuture 的结果, 两个任务并行顺序执行*/
    private static void thenCombine() {
        // 创建第一个 CompletableFuture
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("future1 " + Thread.currentThread().getName());
            return 5;
        });

        // 创建第二个 CompletableFuture
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("future2 " + Thread.currentThread().getName());
            return 5;
        });

        // 组合两个 CompletableFuture 的结果
        CompletableFuture<Integer> combinedFuture = future1.thenCombine(future2, (result1, result2) -> result1 * 10 + result2);

        // 打印组合后的结果
        System.out.println("Combined result: " + combinedFuture.join());
    }

    /**
     * 接受处理正常结果, 并返回新的结果*/
    private static void thenApply() {
        // 异步回调
        // thenApply 用来处理正常结果
        CompletableFuture<Integer> future = CompletableFuture
                .supplyAsync(() ->  5)
                .thenApply(v -> v + 5)
                .thenApply(v -> v * 10)
                .exceptionally((e) -> {
                    System.out.println(e.getMessage());
                    return 0;
                });

        System.out.println("thenApply res = " + future.join());
    }

    /**
     * 异步回调, 不关心任务的返回值, 不接受任务的返回值
     * */
    private static void thenRun() {
        // 异步回调, 所有任务执行完毕后执行, 不关心任务的返回值, 不接受任务的返回值
        CompletableFuture future = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("runAsync " + Thread.currentThread().getName());
                    return 0;
                })
                .thenRun(() -> System.out.println("thenRun1 " + Thread.currentThread().getName()))
                .thenRun(() -> System.out.println("thenRun2 " + Thread.currentThread().getName()));
        System.out.println("主线程");
        future.join();

    }

    /**
     *所有任务执行完毕后执行, 接受任务的返回值, 但不返回新的结果
     * */
    private static void thenAccept() {
        // 异步回调, 所有任务执行完毕后执行, 接受任务的返回值
        CompletableFuture future = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("runAsync " + Thread.currentThread().getName());
                    return 0;
                })
                .thenAccept(res -> System.out.println("thenAccept res = " + res + " " + Thread.currentThread().getName()));
        System.out.println("主线程");
        future.join();
    }

    private static void AllOf() {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("future1");
        });
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("future2");
        });
        // 获取所有结果
        CompletableFuture<Void> future = CompletableFuture.allOf(future1, future2);
        future.join();


    }

    private static void AnyOf() {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("future1");
        });
        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("future2");
        });

        // 获取执行最快的结果
        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2);
//        anyOfFuture.thenAccept(res -> System.out.println("一个方法执行成功"));

        // get() 需要处理异常
//        try {
//            anyOfFuture.get();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        anyOfFuture.join();


    }

}
