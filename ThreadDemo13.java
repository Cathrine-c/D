package java1103_Thread.java;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 定时器的实现，三步：
 * 1.需要一个Task类，来组织需要执行的任务,这些任务能够比较优先级
 * 2.需要一个阻塞优先队列来组织若干个task，当队列空了，就发生阻塞，当队列满了，也会发生阻塞
 * 3.需要一个扫描线程，扫描线程需要循环扫描，让任务执行
 */

public class ThreadDemo13 {

    static class Task implements Comparable<Task>{
        private long time;
        private Runnable command;

        public Task( Runnable command,long time) {
            this.time = time;
            this.command = command;
        }

        @Override
        public int compareTo(Task o) {
            return (int)(this.time-o.time);
        }

        public void run() {
            command.run();
        }
    }


    static class Worker extends Thread{
        private PriorityBlockingQueue<Task> queue = null;
        private Object mailBox=null;

        public Worker(PriorityBlockingQueue queue, Object mailBox) {
            this.queue = queue;
        }


        @Override
        public void run() {
            while (true){
                try {
                    Task task = queue.take();
                    long currentTime = System.currentTimeMillis();
                    if (task.time>currentTime){
                        queue.put(task);
                        synchronized (mailBox){
                            mailBox.wait(task.time-currentTime);
                        }
                    }else{
                        task.run();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }

        }
    }

    static class Timer{
        private PriorityBlockingQueue queue = new PriorityBlockingQueue();
        private Object mailBox=new Object();

        public Timer() {
            Worker worker = new Worker(queue,mailBox);
            worker.start();
        }

        public void schedule(Runnable command,long after){
            Task task = new Task(command,after);
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
                System.out.println("ahhah");
                timer.schedule(this,2000);
            }
        },3000);
    }

}
