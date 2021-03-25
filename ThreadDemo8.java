package java1103_Thread.java;

/**
 * 为了保证线程安全，有三个要点
 * 1.加锁，保证线程安全
 * 2.双重if保证效率
 * 3.volatile避免内存可见性引来的问题
 */

public class ThreadDemo8 {

    static class Singleton{
        private  Singleton() { }
        private volatile static Singleton instance=null;
        public static Singleton getInstance(){
            if (instance == null) {
                synchronized (Singleton.class){
                    if (instance == null) {
                        instance=new Singleton();
                    }
                }
            }
            return instance;
        }
    }
}
