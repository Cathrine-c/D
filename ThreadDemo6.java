package java1103_Thread.java;

/**
 * wait方法：让其他线程等待调度
 * notify方法：告知其他线程当前线程已经执行完毕
 * 两者都需要搭配 锁synchronized使用
 */
public class ThreadDemo6 {
    public static void main  (String[] args) throws InterruptedException {
        Object object = new Object();
        synchronized (object) {
            System.out.println("等待前");
            object.wait();
            System.out.println("等待后");
        }
    }

}
