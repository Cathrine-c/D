package 蓝桥;

import java_0308.Main;

import javax.swing.text.html.parser.Entity;
import java.util.*;

public class Day2 {

    //康威生命游戏
    public static long sumisAliveCell(int[][] dp, long k) {
        int sum = 0;

        //k表示迭代代数
        while (k > 0) {
            //设置一个二维数组，让它等于原始数组dp
            int[][] map = new int[dp.length][dp[0].length];
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    map[i][j] = dp[i][j];
                }
            }

            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[0].length; j++) {
                    int x = 0;
                    int count = 0;
                    //表示存活的细胞
                    if (map[i][j] == 1) {

                        //统计每个细胞周围的存活细胞数
                        for (int m = i - 1; m <= i + 1; m++) {
                            for (int n = j - 1; n <= j + 1; n++) {
                                if (m < 0 || m >= dp.length || n >= dp[0].length || n < 0) {
                                    continue;
                                } else {
                                    x += map[m][n];
                                }
                            }
                        }

                        //由于把它自身也加进去了，所以要-1
                        if (x - 1 > 3 || x - 1 < 2) {

                            //给dp重新赋值，赋值之后它就变了，所以循环应该用原来的数组
                            dp[i][j] = 0;

                        } else {
                            dp[i][j] = 1;
                        }

                    } else {
                        //统计死亡细胞周围存活的细胞数
                        for (int m = i - 1; m <= i + 1; m++) {
                            for (int n = j - 1; n <= j + 1; n++) {
                                if (m < 0 || m >= dp.length || n >= dp[0].length || n < 0) {
                                    continue;
                                } else {
                                    if (map[m][n] == 1) {
                                        count++;
                                    }
                                }
                            }
                        }

                        //如果存活细胞数==3，那么死亡细胞会重新复活
                        if (count == 3) {
                            //把该细胞值置为1
                            dp[i][j] = 1;
                        }
                    }
                }
            }

            k--;
        }


        //根据最后变化的数组，统计存活细胞数
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                sum += dp[i][j];
            }
        }

        return sum;
    }


    public static void main1(String[] args) {

        int[][] map = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};

        long l = (long) Math.pow(10, 9);
        System.out.println(sumisAliveCell(map, 5));

    }


    public static void main2(String[] args) {

        for (long i = 100000; i > 30000; i--) {
            long x = i * i;
            if (maxDoubleNum(x)) {
                System.out.println(x);
                return;
            }
        }

    }

    public static boolean maxDoubleNum(long x) {
        if (x == 0) {
            return false;
        }
        Set<Integer> set = new HashSet<>();

        while (x > 0) {
            int d = (int) (x % 10);
            set.add(d);
            x /= 10;

        }

        return set.size() == 10;

    }



    public static void main3(String[] args) {

        int sum = 0;
        for (int i=1;i<=1000;i++){

            int n = i;
            while (n > 0) {
                int m = n%10;
                sum+=m;

                n/=10;
            }

        }

        System.out.println(sum);
    }



    public static void main5(String[] args) {
        Scanner sc = new Scanner(System.in);

        //while (sc.hasNext()) {

            int N = sc.nextInt();
            sc.nextLine();
            ArrayList<Integer> list = new ArrayList<>();

            for (int i = 0; i < N; i++) {

                String s = sc.nextLine();


                String[] strings = s.split(" ");

                for (int k = 0; k < strings.length; k++) {
                    list.add(Integer.parseInt(strings[i]));

                }
            }


            int m = 0;
            int n =0;
            Collections.sort(list);
            boolean flag = false;
            for (int i =0;i<list.size()-1;i++){

                if (list.get(i)+2 == list.get(i+1)){
                    n= list.get(i)+1;

                    if (flag) {
                        break;
                    }
                    flag = true;
                }

                if (list.get(i)==list.get(i+1)){
                     m=list.get(i);
                    if (flag) {
                        break;
                    }
                    flag = true;
                }

                System.out.println(m);
                System.out.println(n);
            }


       // }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        while (sc.hasNext()) {
            Map<Character,Integer> map = new HashMap<>();

            String s = sc.next();
            char[] chars = s.toCharArray();
            for (int i=0;i<chars.length;i++){

                if (map.containsKey(chars[i])){
                    Integer x = map.get(chars[i]);
                    map.put(chars[i],x+1);
                }else {

                    map.put(chars[i],1);
                }


            }

            Set<Character> set = map.keySet();
            int value =0;
            char key =0;

            for (Character c:set){
                if (map.get(c) > value) {
                    key = c;
                    value = map.get(c);
                }
            }

            System.out.println(key);
            System.out.println(value);
        }
    }
}



