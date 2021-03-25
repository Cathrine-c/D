package java1103_Thread.java;

public class ThreadDemo3 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(" 我是一个新线程");
            }
        };
        Thread t4=new Thread(runnable);
        t4.start();
    }





}
