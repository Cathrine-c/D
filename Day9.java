package 蓝桥;


import java.math.BigInteger;
import java.util.Calendar;

public class Day9 {

    public static void main1(String[] args) {

        //x 3/  半分钟后吃掉一个Y  之后每隔一分钟吃掉一个Y
        //Y 2/
        // X=10;  Y=89; 60分钟后Y=？

        long x =10;
        long y=90;
       for (int i=1;i<=60;i++){
           y-=x;

           if (i % 2 == 0) {
               y+=y;

           }

           if (i % 3 == 0) {
               x+=x;
           }
       }
        System.out.println(y);

    }



    public static void main2(String[] args) {

        int[] arr = {1949,  1499,  9149, 9419, 9491 , 9941, 4199 ,4919, 4991 };

        int count=0;
        for (int i=0;i<arr.length;i++){
            int j=2;
            while (j<arr[i]/2){
                if (arr[i] % j == 0) {
                    count++;
                    j=2;
                    break;
                }else {
                    j++;
                }

            }

        }

        int sum = arr.length-count;
        System.out.println(sum);

    }



    //打印梅森素数的后100位
    public static void main3(String[] args) {
        BigInteger m=new BigInteger("2");
        m=m.pow(11213).subtract(BigInteger.valueOf(1));
        BigInteger x=m.mod(BigInteger.valueOf(10).pow(100));
        System.out.println(x);

    }

    public static void main5(String[] args) {
        int i=2099;
        while (i > 1999) {
            int n =(i-1999)%7;
            if (n == 2) {
                System.out.println(i);
                return;
            }else {
                i+=100;

            }


        }
    }



}
