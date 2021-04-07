package InterviewImportant.Arithmiy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class Solution {

    public static boolean isSame(int[] array) {
        Arrays.sort(array);
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i + 1]) {
                return true;
            }
        }
        return false;

    }


    public static void main1(String[] args) {
        int[] array = {1, 2, 5, 3, 8, 6};
        System.out.println(isSame(array));
    }


    public static boolean isPailindrome(int x) {
        String s = "";

        if (x < 0) {
            return false;
        }

        if (x < 10) {
            return true;
        }

        while (x > 0) {
            int n = x % 10;
            s += n;
            x /= 10;
        }

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }


    public static void main2(String[] args) {
        int x = 1221;

        System.out.println(isPailindrome(x));
    }


    public static void reverseMatrix(int[][] matrix) {
        int[][] m = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                m[j][i] = matrix[matrix.length - i - 1][j];

            }

        }
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                System.out.print(m[i][j] + ",");
            }
        }

    }


    public static void main4(String[] args) {
        int[][] ma = {{1, 2, 3,0}, {4, 5, 6,22}, {7, 8, 11,9},{33,44,55,66}};

        reverseMatrix(ma);

    }




    public static void main(String[] args) {
//        int[] a = {1,3,6,3,1,6,7};
//        System.out.println(SingleNum(a));

//        String s = "12334567";
//        int x = Integer.parseInt(s);
//        System.out.println(x);

        findMygic();
    }

    public static void findMygic(){
        String s = "";

        for(int i=12345;i<98765;i++){
            int a=i/10000;
            s+=a;
            int b=i/1000%10;
            s+=b;
            int c=i/100%10;
            s+=c;
            int d=i/10%10;
            s+=d;
            int e=i%10;
            s+=e;

            if(a==b||a==c||a==d||a==e||b==c||b==d||b==e||c==d||c==e||d==e){
                s="";
                continue;
            }


            String str = "";
            for (int k=s.length()-1;k>=0;k--){
                str+=s.charAt(k);
            }

            int m = Integer.parseInt(s);
            int n = Integer.parseInt(str);

            for (int j=0;j<10;j++){
                if (m*j==n){
                    System.out.println(m);
                }
            }

            s="";
            str="";

        }


    }

    //找到只出现一次的数字
    public static int SingleNum(int[] array){
        Map<Integer,Integer> map = new HashMap<>();

        for (int i=0;i<array.length;i++){
            Integer x = map.get(array[i]);
            if (!map.containsKey(array[i])){
                map.put(array[i],1);

            }else {
                map.put(array[i],x+1);

            }
        }

        int v =0;
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){

            if (entry.getValue() == 1) {
                 v = entry.getKey();

            }
        }
        return v;
    }



}
