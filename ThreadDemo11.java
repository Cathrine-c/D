package java1103_Thread.java;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 定时器的实现：
 * 1.使用一个Task类来描述“一段逻辑”，（一个要执行的任务），同时也要记录这个任务在啥时候执行
 * 2.用一个阻塞优先队列来组织若干个Task
 * 3.需要一个扫描线程，扫描线程循环检测：线程是否需要执行，
 * 阻塞优先队列：既支持阻塞的特性，又支持按优先级的“先进先出”，本质上是一个堆
 *
 */
public class ThreadDemo11 {

//优先队列中的元素必须可以比较，让Task实现Comparable接口
    static class Task implements Comparable<Task> {
        //Runnable中有一个run方法来描述执行的具体的任务
        private long time;
        private Runnable command;


         //构造方法的参数表示：多少ms之后开始执行(相对时间)
        public Task(long after, Runnable command) {
            this.time = time;
            this.command = command;
        }

        public void run(){
            command.run();
        }

        @Override
        public int compareTo(Task o) {
            return (int)(this.time-o.time);
        }
    }


    static class Worker extends Thread{
        private PriorityBlockingQueue<Task> queue = null;
        private Object mailBox=null;
        public Worker(PriorityBlockingQueue<Task> queue,Object mailBox) {
            this.queue = queue;
            this.mailBox=mailBox;
        }

        @Override
        public void run() {
            //实现具体的线程执行的内容
            while (true) {
                //1.取出队首元素，检查时间是否到了
                try {
                    Task task = queue.take();
                    //2.检查当前任务时间是否到了
                    long currentTime = System.currentTimeMillis();
                    if (task.time > currentTime) {
                        //时间还没到，就把任务塞回队列中
                        queue.put(task);
                        synchronized (mailBox){
                            mailBox.wait(task.time-currentTime);
                        }
                    }else{
                        task.run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //如果线程遇到意外终止了，就让它结束break
                    break;
                }
            }
        }
    }

    static class Timer{
        private Object mailBox=new Object();
        //定时器的基本构成有三个部分：
        //1.用一个类来描述“任务”
        //2.用一个阻塞队列来组织若干个任务，让队首元素就是时间最早的任务
        //如果队首时间未到，那么其它元素肯定不能执行
        private PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();
        //3.用一个线程来循环扫描当前的阻塞队列的队首元素，如果时间到，就执行指定得任务

        public Timer() {
            //创建线程
            Worker worker = new Worker(queue, mailBox);
            worker.start();
        }
        //让调用者把任务安排进来
        public void schedule(Runnable command,long after){
            Task task = new Task(after, command);
            queue.put(task);
            synchronized (mailBox){
                mailBox.notify();
            }
        }
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("hehe");
                //每隔两秒出现一个hehe
                timer.schedule(this,2000);
            }
        },2000);
    }
}
