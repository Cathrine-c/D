package 蓝桥;

import java.math.BigInteger;

public class Day8 {

    //汉诺塔问题
    public static void main1(String[] args) {
        BigInteger bigInteger = new BigInteger("2");
        BigInteger bigInteger1 = bigInteger.pow(64);

        BigInteger bigInteger2 = new BigInteger("-1");
        System.out.println(bigInteger1.add(bigInteger2));
       // System.out.println("18446744073709551615");
    }


    public static void main2(String[] args) {
        int[] num = {1,3};
        while (true) {
            int a = num[0];
            int b = num[1];
            if ((Math.round(a*1.0/b*1000000))==618034){
                break;
            }

            num[0] = b;
            num[1] = a+b;

        }

        System.out.println(num[0]+"/"+num[1]);

    }

    public static void main(String[] args) {

        System.out.println("7"+","+"A"+","+"Q"+","+"2"+","+"8"+","+"3"+","+"J"+","+"4"+","+"9"+","
        +"5"+","+"K"+","+"6"+","+"10");

    }
}
