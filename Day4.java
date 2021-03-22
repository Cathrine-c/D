package 蓝桥;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Day4 {

    //求[1,2020]有多少个2
    public static void main1(String[] args) {
        Scanner sc =  new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int count  =0;

        for (int i=m;i<=n;i++){
            int e = i;
            while (e > 0) {
                int x = e%10;
                if (x == 2) {
                    count++;
                }
                e/=10;

            }
        }

        System.out.println(count);
    }


    public static void main3(String[] args) {
        int[][] arr = {{2,2,0,0,0,0},{0,0,0,0,0,0},{0,0,2,2,0,2},{0,0,0,0,0,0},{0,0,0,0,2,2},{0,0,2,0,2,0}};
        System.out.println(sum2020(arr));
    }


    //求一个矩阵里构成2020的数有多少个，矩阵由0和2组成，同一行、同一列、左上到右下连续构成2020
    public static int sum2020(int[][] arr){
        if (arr.length < 4 && arr[0].length < 4) {
            return 0;
        }

        int count =0;
        for (int i=0;i<arr.length;i++){
            for (int j=0;j<arr[0].length;j++){
                if (j<arr[0].length-3) {
                    if (arr[i][j] == 2 && arr[i][j + 1] == 0 && arr[i][j + 2] == 2 && arr[i][j + 3] == 0) {

                        count++;
                    }
                }

                if (i<arr.length-3) {
                    if (arr[i][j] == 2 && arr[i + 1][j] == 0 && arr[i + 2][j] == 2 && arr[i + 3][j] == 0) {
                        count++;
                    }
                }

                if (i<arr.length-3&&j<arr[0].length-3) {
                    if (arr[i][j] == 2 && arr[i + 1][j + 1] == 0 && arr[i + 2][j + 2] == 2 && arr[i + 3][j + 3] == 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }


    //蛇形填数
    public static void main4(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n=sc.nextInt();
        System.out.println(nNum(n));

    }

    private static int nNum(int n) {

        if (n == 1) {
            return 1;
        }

        return nNum(n-1)+4*(n-1);
    }


    //求成绩的最高分、最低分、平均分
    public static void main7(String[] args) {
        Scanner sc= new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i=0;i<n;i++){

                a[i] = sc.nextInt();
            }

            Arrays.sort(a);

            System.out.println(a[a.length-1]);
            System.out.println(a[0]);
            int sum =0;
            for (int i=0;i<a.length;i++){
                sum+=a[i];
            }

            double avg = sum/a.length;
            System.out.println(avg);

        }

    }


    public static void main8(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {

            String s = sc.next();
            sunN(s);

        }
    }

    private static void sunN(String s) {
        Map<Character,Integer> map = new HashMap<>();

        for (int i=0;i<s.length();i++){
            Integer x = map.get(s.charAt(i));
            if (!map.containsKey(s.charAt(i))){
                map.put(s.charAt(i),1);
            }else {
                map.put(s.charAt(i),x+1);
            }
        }

        int max =1;
        char c = ' ';
        int count =0;
        for (Map.Entry<Character,Integer> entry: map.entrySet()){

            if (entry.getValue() > max) {
                max = entry.getValue();
                c = entry.getKey();
            } else if (entry.getValue() == max&&max!=1) {
                count = max;

            }

        }

        if (max == count) {
            char c1 ='z';

            for (Map.Entry<Character,Integer> entry: map.entrySet()){

                if (entry.getKey()<c1) {
                    c1 = entry.getKey();
                }
            }
            System.out.println(c1);
            System.out.println(1);
            return;
        }


        if (max==1){
            char c1 ='z';

            for (Map.Entry<Character,Integer> entry: map.entrySet()){

                if (entry.getKey()<c1) {
                    c1 = entry.getKey();

                }
            }
            System.out.println(c1);
            System.out.println(1);
            return;
        }

        System.out.println(c);
        System.out.println(max);


    }


    //数字三角形
    public static void main9(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();

            int[][] dp = new int[n][n];

            for (int i=0;i<dp.length;i++){
                for (int j=0;j<=i;j++){
                    dp[i][j] = sc.nextInt();
                }
            }


            if (n == 1) {
                System.out.println(dp[0][0]);
            } else if (n==2){
                dp[0][0] = Math.max(dp[1][0],dp[1][1]);

            }else {
                for (int i=n-1;i>=0;i--){

                    for (int j=0;j<i;j++){
                        dp[i-1][j] = Math.max(dp[i][j],dp[i][j+1]);
                    }
                }
            }

            System.out.println(dp[0][0]);
        }

    }


    public static void main(String[] args) {



    }

}
