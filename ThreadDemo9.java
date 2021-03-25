package java1103_Thread.java;

/**实现生产-消费模式
 * 也是在锁里实现
 * 用阻塞队列：基于数组实现
 * 注意：
 * 1.当队列为空的时候，继续出队列就会阻塞，一直阻塞到其他线程入队列成功为止
 * 2.当队列满时，继续入队列也会阻塞，一直阻塞到其他线程出队列成功为止
 */
public class ThreadDemo9 {
    static class BlockingQueue {
        private int[] array = new int[1000];
        //head和tail构成的是一个前闭后开的区间
//当两者重合的时候，可能表示队列为空，也可能表示队列满了
//为了区分空还是满，就可能需要额外引入一个size来标识
        private int head;
        private int tail;
        private int size;

        //队列的基本操作:1.入队列  2.出队列 3.取队首元素   阻塞队列只支持前两个操作
        //为了和之前的进行区分，用了不同的名字
        public void put(int value) throws InterruptedException {
            //这些操作都在锁里进行
            synchronized (this) {
                //如果队列满了，就wait一下
                if (size==array.length){
                    wait();
                }
                //把value放到队尾
                array[tail] = value;
                tail++;
                if (tail == array.length) {
                    tail = 0;
                }
                size++;

                notify();//对应出队列的wait()
            }
        }

        public int take() throws InterruptedException {
            int ret = -1;
            synchronized (this) {
                if (size == 0) {
                    wait();//如果size为空，就wait一下
                    ret = array[head];
                }
                head++;
                if (head == array.length) {
                    head = 0;
                }
                size--;

               notify();//对应入队列的wait()，两个wait不可能同时被调用
            }
            return ret;
        }
    }
}
