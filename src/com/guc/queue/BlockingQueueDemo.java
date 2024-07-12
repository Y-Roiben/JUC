package com.guc.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        // 第一组
        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));
        System.out.println(queue.element());
//        System.out.println(queue.add("c")); // 队列满了抛出异常，
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
//        System.out.println(queue.remove());  // 队列空了抛出异常

        System.out.println("-----第二组----------");

        // 第二组
        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        System.out.println(queue.offer("www"));

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());

        // 第三组
        System.out.println("-----第三组----------");

        queue.put("a");
        queue.put("b");
        queue.put("c");
//        queue.put("www");

        System.out.println(queue.take());
        System.out.println(queue.take());
        System.out.println(queue.take());
//        System.out.println(queue.take());


        System.out.println("-----第四组----------");
        // 设置超时时间
        System.out.println(queue.offer("a", 2, TimeUnit.SECONDS));
        System.out.println(queue.offer("b", 2, TimeUnit.SECONDS));
        System.out.println(queue.offer("c", 2, TimeUnit.SECONDS));
        System.out.println(queue.offer("d", 2, TimeUnit.SECONDS));
        System.out.println(queue.poll(2, TimeUnit.SECONDS));
        System.out.println(queue.poll(2, TimeUnit.SECONDS));
        System.out.println(queue.poll(2, TimeUnit.SECONDS));
        System.out.println(queue.poll(2, TimeUnit.SECONDS));



    }
}
