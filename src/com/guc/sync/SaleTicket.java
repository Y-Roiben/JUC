package com.guc.sync;


// 创建资源类
class Ticket {
    // 定义属性
    private int number = 30;

    public synchronized void sale() {  // synchronized 修饰方法，锁的是this
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "卖出了第" + (number--) + "张票，还剩" + number + "张票");
        }
    }
}
public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 创建三个线程，模拟三个售票员
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }

}
