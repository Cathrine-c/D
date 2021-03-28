package java_JVM;

/**
 * 大对象进入老年代
 * -XX:+PrintGCDetails -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:PretenureSizeThreshold=3145728
 */
public class Test2 {
    private static final int _1MB = 1024 * 1024;

    public static void testAllocation() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }


    public static void main(String[] args) throws Exception {
        testAllocation();
    }

}