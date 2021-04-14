package 蓝桥;

import java.util.*;

public class Day14 {

    public static void main1(String[] args) {

        System.out.println(isPowOf2(100));
    }

    //判断一个数是否为2的整数次幂
    //时间复杂度：o(N)
    public static boolean isPowOf2(int num){

        int tem = 1;

        while (tem <= num) {
            if (tem == num) {

                return true;
            }
            tem = tem<<1;

        }
        return false;

    }


    //方法2：时间复杂度o（1）
    //2的整数次幂二进制最高位是1，其余都是0；它减1，二进制每一位都是1，两者相与就是0
    public static boolean isPowOf22(int num){
        return (num & (num - 1))== 0;


    }


    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            String s = sc.nextLine();
            if (s == null || s.length() == 0) {
                System.out.println(0);

                return;
            }


            if (s.length() <= 200) {
                char[] chars = s.toCharArray();
                Set<Character> set = new HashSet<>();
                int max = 0;

                for (int i = 0; i < chars.length; i++) {
                    int k = i;

                    while (k < s.length()) {

                        if (chars[k] >= 'A' && chars[k] <= 'Z') {
                            i = k;
                            break;

                        } else {
                            set.add(chars[k]);

                        }
                        k++;

                    }


                    if (set.size() > max) {
                        max = set.size();
                        set.clear();
                    }
                }
                System.out.println(max);

            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int n = sc.nextInt();
            int m =sc.nextInt();

            int[] a = new int[n];
            for (int i=0;i<n;i++){
                a[i] = sc.nextInt();

            }

            int[][] b = new int[m][3];

            for (int i=0;i<m;i++){
                b[i][0] = sc.nextInt();
                b[i][1] = sc.nextInt();
                b[i][2] = sc.nextInt();

            }

            int count =0;
            while (count <m) {

                for (int i=b[count][0];i<=b[count][1];i++){
                    
                    b[count][2] =b[count][2]*(i-b[count][0]+1);
                    a[i-1] += b[count][2];

                   // b[count][2] =b[count][2]*(i-b[count][0]+1);
                }

                count++;
            }

            System.out.println(Arrays.toString(a));

        }


    }




}
