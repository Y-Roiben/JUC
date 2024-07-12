package com.guc.lock;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * list集合线程不安全
 * 并发修改异常
 * */
public class ThreadDemo4 {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // 集合添加内容
                list.add(UUID.randomUUID().toString().substring(0, 5));
                // 获取集合内容
                System.out.println(list);
            }, String.valueOf(i)).start();
        }


    }
}
