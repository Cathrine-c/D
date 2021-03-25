package java1103_Thread.java;

import java.util.Scanner;

/**
 * volatile关键字的使用
 */
public class ThreadDemo4 {
    static class Counter{

        //加volatile关键字保持内存可见性
        public volatile int flag=0;
    }
    public static void main(String[] args) {
        Counter counter = new Counter();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (counter.flag == 0) {

                }
                System.out.println("循环结束！");
            }
        };
        t1.start();

        Thread t2 = new Thread(){
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("请输入一个整数：");
                counter.flag = scanner.nextInt();
            }
        };
        t2.start();
    }



}
