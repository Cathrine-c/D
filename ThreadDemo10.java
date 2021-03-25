package java1103_Thread.java;


public class ThreadDemo10 {

    int[] array = new int[1000];

    public int head;
    public int tail;
    public int size;


    public void put1(int value) throws InterruptedException {
        synchronized (this) {
            if (size == array.length) {
                wait();
            }
            array[tail] = value;
            tail++;
            if (tail == array.length) {
                tail = 0;
            }
            size++;

            notify();
        }
    }


    public int take1() throws InterruptedException {
        int ret = -1;
        synchronized (this) {
            if (size == 0) {
                wait();
                ret = array[head];
            }
            head++;
            if (head == array.length) {
                head = 0;
            }
            size--;
            notify();
        }
        return ret;
    }



    public void put(int value) throws InterruptedException {
        synchronized (this) {
            if (size == array.length) {
                wait();
            }
            array[tail] = value;
            tail++;
            if (tail == array.length) {
                tail = 0;
            }
            size++;

            notify();
        }
    }


    public int take() throws InterruptedException {
        int ret = -1;
        synchronized (this) {
            if (size == 0) {
                wait();
                ret = array[head];
            }
            head++;
            if (head == array.length) {
                head = 0;
            }
            size--;

            notify();
        }
        return ret;
    }


    public static void main(String[] args) {
        //创建两个线程，分别模拟消费者和生产者
        //第一次，让消费者消费的快一些，生产者生产得慢一些
        //
        ThreadDemo9.BlockingQueue blockingQueue = new ThreadDemo9.BlockingQueue();
        Thread producer =new Thread(){
            @Override
            public void run() {
                for (int i=0;i<10000;i++){
                    try {
                        blockingQueue.put(i);
                        System.out.println("生产元素："+i);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };
        producer.start();

        Thread consumer=new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {

                        int ret = blockingQueue.take();
                        System.out.println("消费元素"+ret);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        consumer.start();
    }

}
