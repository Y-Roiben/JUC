package com.guc.分支合并;

import java.util.concurrent.*;

/**
 * 服务拆分
 * */
class MyTask extends RecursiveTask<Integer>{
    private static final Integer ADJUST_VALUE = 10;
    private final int begin; // 用于存放任务的开始
    private final int end;  // 用于存放任务的开始
    private int result; // 用于存放结果
    MyTask(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - begin <= ADJUST_VALUE) {
            for (int i = begin; i <= end; i++) {
                result = result + i;
            }
        }else {
            int middle = (begin + end) / 2;
            MyTask task1 = new MyTask(begin, middle);
            MyTask task2 = new MyTask(middle + 1, end);
            task1.fork();
            task2.fork();
            result = task1.join() + task2.join();
        }
        return result;
    }
}
public class Demo1 {
    public static void main(String[] args) {

        MyTask myTask = new MyTask(0, 100);
        ForkJoinPool forkJoinPool = new ForkJoinPool();  // 创建池对象
        ForkJoinTask<Integer> forkJoinTask = forkJoinPool.submit(myTask);
        try {
            System.out.println(forkJoinTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        // 关闭线程池
        forkJoinPool.shutdown();

    }

}
