package java1103_Thread.java;


public class ThreadDemo2 {


    private static long count=100_0000_0000L;
    public static void main(String[] args) {
        serial();
        concurrency();

    }

//并发执行
    private static void concurrency() {
        long beg  = System.currentTimeMillis();
        //匿名内部类：创建了一个没有名字的类
        Thread t1=new Thread(){
            @Override
            public void run() {
                int a=0;
                for (long i=0;i<count;i++){
                    a++;
                }
            }
        };

        Thread t2=new Thread(){
            @Override
            public void run() {
                int b=0;
                for (long i=0;i<count;i++){
                    b++;
                }
            }
        };

        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //要保证t1和t2运行完后再计算时间，调用join方法
        long end = System.currentTimeMillis();
        System.out.println("time:"+(end-beg)+"ms");

    }

    //串行执行
    private static void serial() {
        long beg  = System.currentTimeMillis();//获取当前时间,ms级
        int a=0;
        for (long i=0;i<count;i++){
            a++;
        }

        int b=0;
        for (long i=0;i<count;i++){
            b++;
        }
        long end = System.currentTimeMillis();
        System.out.println("time:"+(end-beg)+"ms");

    }
}
