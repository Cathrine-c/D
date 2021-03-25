package java1103_Thread.java;

/**
 * 创建线程的三种方法：
 * 1.显式继承Thread类，重写它里面的run方法
 * 2.通过匿名内部类的方式继承Thread类
 * 3.创建一个类，实现Runnable包接口,然后把实例关联到Thread实例上
 * 4.
 * 5.
 */
public class ThreadDemo1 {

    static class MyRunnale implements Runnable{

        @Override
        public void run() {
            System.out.println(" ");
        }
    }



    static  class myThread extends Thread{
        @Override
        public void run() {
            System.out.println("hello world！我是一个线程");
        }
    }

    //main方法里是主线程
    public static void main(String[] args) {
        //创建了线程之后，需要调用start方法，线程才被真正创建，
       Thread t2 = new Thread();
       //此时PCB就会让CPU来执行该线程
       t2.start();


       Thread t1=new Thread(new MyRunnale()) ;
          t1.start();
    }


}
