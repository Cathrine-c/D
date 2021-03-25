package java1103_Thread.java;

//复写定时器

import java.util.concurrent.PriorityBlockingQueue;

public class ThreadDemo12 {
     static class Task implements Comparable<Task>{

         private long time;
         private Runnable command;

         public Task(Runnable command,long after) {
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
         private PriorityBlockingQueue<Task> queue =null;
         private Object mailBox;


         public Worker(PriorityBlockingQueue<Task> queue,Object mailBox) {
             this.queue = queue;
             this.mailBox=mailBox;
         }

         @Override
         public void run() {
             while (true) {
                     try {
                         Task task = queue.take();
                         mailBox.wait();

                         long currentTime = System.currentTimeMillis();
                         if (task.time > currentTime) {
                             queue.put(task);
                             synchronized (mailBox) {
                                 mailBox.wait(currentTime-task.time);
                             }
                         } else {
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

         public void schedule(Runnable command,long after) {
             Task task = new Task(command,after);
             queue.put(task);
             synchronized (mailBox) {
                 mailBox.notify();
             }
         }
    }


    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("mama");
                timer.schedule(this,2000);
            }
        },2000);
    }

}
