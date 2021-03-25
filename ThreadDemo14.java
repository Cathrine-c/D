package java1103_Thread.java;

import java.util.concurrent.PriorityBlockingQueue;

//复写定时器
public class ThreadDemo14 {
    static class Task implements Comparable<Task>{

        private Runnable command;
        private long time;

        public Task( Runnable command,long time) {
            this.command = command;
            this.time = time;
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

        public Worker(PriorityBlockingQueue<Task> queue,Object mailBox) {
            this.queue = queue;
            this.mailBox=mailBox;
        }

        @Override
        public void run() {
            while (true){
                try {
                    Task task = queue.take();
                    long currentTime = System.currentTimeMillis();
                    if (task.time > currentTime) {
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
        private PriorityBlockingQueue<Task> queue = new PriorityBlockingQueue<>();

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
                System.out.println("hehhe");
                timer.schedule(this,2000);
            }
        },3000);
    }

}
