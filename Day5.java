package 蓝桥;

import java.util.HashSet;

public class Day5 {

    private static int count;

    public static void main(String[] args) {
        String i1 = "";
        String i2 = "";
        String k1 = "";
        int k;

        for (int i=100;i<1000;i++){
            for (int j=100;j<1000;j++){
                k = i+j;
                if (k > 1000) {
                    break;
                }

                i1 = ""+i;
                i2 = ""+j;
                k1 = k+"";

                if (sumTianShu(i1 + i2 + k1)){
                    count++;
                }
            }
        }

        System.out.println(count);

    }


    static HashSet<Character> set = new HashSet<>();
    public static boolean sumTianShu(String s){

        if (s.length() < 9) {
            return false;
        }
        set.clear();

        for (int i=0;i<s.length();i++){
            if (s.charAt(i)!='0'){
                set.add(s.charAt(i));
            }
        }

        return set.size()==9;
    }



}
