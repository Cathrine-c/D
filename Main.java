package java_JVM;

public class Main {


/* -XX:+PrintGCDetails -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M
 *
 * 堆：20m
 * 新生代：10m，Eden=8m，S0=1m，S1=1m
 * 老年代：10m
 */

    private static final int _1M = 1024*1024;

    //打印GC日志 printGCLog
    public static void testAllocation(){
        byte[] allocation1,allocation2,allocation3,allocation4;

        allocation1 = new byte[2*_1M];//Eden区 2M
        allocation2 = new byte[2*_1M];//Eden区 2M
        allocation3 = new byte[2*_1M];//Eden区 2M

        allocation4 = new byte[4*_1M];

    }


    public static void main(String[] args) {

        testAllocation();
        /*

        1.分配allocation4的内存空间
        2.Eden区空间不够，触发Minor Gc
        3.Minor GC之后，S区不够存，分配担保机制，大对象分配到老年代
        4.GC完，空间足够，分配allocation4的空间。

         */
    }


}
