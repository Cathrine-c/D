package java1103_Thread.java;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 线程池实现:
 * 1.有一个类来描述具体要执行的任务（Runnable）
 * 2.有一个阻塞队列来组织所有的任务（BlockingQueue）
 * 3.有一个类来描述工作线程（Worker）
 * 4.有一个数据结构来组织若干个线程（List）
 */

public class ThreadDemo15 {
//使用这个类来描述当前的工作线程是啥样的
    static class Worker extends Thread{
        private int id = 0;
        //每个Worker线程都需要从任务队列中取任务
        private BlockingQueue<Runnable> queue=null;

    public Worker(BlockingQueue<Runnable> queue,int id) {
        this.queue = queue;
        this.id = id;
    }

    @Override
    public void run() {
        //只要线程收到异常，就立刻结束run方法
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Runnable command = queue.take();
                System.out.println("Thread "+id+" is running.....");
                command.run();
            }
                } catch (InterruptedException e) {
                    //线程结束
            System.out.println("线程被终止");
                }
            }
        }


//本质上就是一个生产者消费者模型
//调用execute的代码就是生产者，生产了任务（Runnable对象）
//worker线程就是消费者，消费了队列中的任务
//交易场所就是BlockingQueue

    static class MyThreadPool{
        //这个阻塞队列用于组织若干个任务
        private BlockingQueue<Runnable>  queue = new LinkedBlockingDeque<>();
        //这个List用来组织若干个工作线程
        private List<Worker> workers = new ArrayList<>();

        private static final int maxWorkerCount=10;

        //实现execute方法和shutdown方法
        public void  execute(Runnable command) throws InterruptedException {
           //也是使用延时加载的方式来创建线程
            //如果当前线程数目比较少，新创建线程来作为工作线程
            //如果线程数目过多(达到设定阈值），就不用新建线程了

            if (workers.size() < maxWorkerCount) {
                Worker worker = new Worker(queue,workers.size());
                worker.start();
                workers.add(worker);
            }
            queue.put(command);
        }

        //销毁线程， 保证所有线程结束
        public void shutdown() throws InterruptedException {
            //终止掉所有线程
            for (Worker worker : workers) {
                worker.interrupt();
            }
            //还需要等待每个线程执行结束
            for (Worker worker:workers){
                worker.join();
            }
        }
    }

    static class Command implements Runnable{
        private int num;

        public Command(int num) {
            this.num = num;

        }

        @Override
        public void run() {
            System.out.println("正在执行任务"+num);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        MyThreadPool pool = new MyThreadPool();
        for (int i=0;i<1000;i++){
            pool.execute(new Command(i)) ;
        }
        Thread.sleep(2000);
        pool.shutdown();
        System.out.println("线程池已经被销毁");
    }
}
