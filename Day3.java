package 蓝桥;

import java.util.Scanner;

public class Day3 {

    public static void main1(String[] args) {
        //System.out.println(sum());
        Scanner sc = new Scanner(System.in);

        while(sc.hasNext()){
            String x = sc.next();
            String ss = "";
            int count=0;
            for (int i=x.length()-1;i>=0;i--){

                ss +=x.charAt(i);
                count++;
                if (count % 3 == 0) {
                    ss+=",";
                }

            }
            String str ="";
            for (int i=ss.length()-1;i>=0;i--){

                str+=ss.charAt(i);

            }


            //long n = Integer.parseInt(str);
            System.out.println(str);

        }

    }


    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()){
            int n = sc.nextInt();
            int p = sc.nextInt();

            int x = n/p;

            if (x * p < n) {
                x+=1;
            }

            System.out.println(x);
        }

    }


    public static void main4(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] w = new int[n][m];

            int weight = 0;
            for (int i=0;i<n;i++){
                for (int j=0;j<m;j++){
                    w[i][j] = sc.nextInt();
                    weight+=w[i][j];
                }
            }


            int preWeight = 0;
            int minW = weight;
            int k=0,l=0, square =0 , minRow = 0;
            for (int i=0;i<n/2;i++){
                for (int j=0;j<m/2;j++){
                    preWeight+=w[i][j];
                    if (minW<Math.abs(2*preWeight-weight)){
                         k = i;
                         l = j;
                        minW = Math.abs(2*preWeight-weight);

                    }else if (minW==Math.abs(2*preWeight-weight)){
                        square = (i+1)*(j+1);
                        if (square < i * j) {
                            k=i;
                            l=j;

                        } else if (square > i * j) {
                            k=i-1;
                            l=j-1;

                        }else {

                           if(minRow<i){
                               minRow = i;
                               k = i;
                               l = j;
                           }

                        }
                    }else {
                        continue;
                    }
                }
            }

            System.out.println(k+" "+l);

        }
    }


    public static void main5(String[] args) {


        Scanner sc = new Scanner(System.in);


        while (sc.hasNext()){
            int N = sc.nextInt();
            int[] arr = new int[N];

            String string[] = new String[N];
            for (int i=0;i<N;i++){

                arr[i] = sc.nextInt();
                string[i] = sc.next();
            }
            System.out.println(minBa(string,arr));


        }

    }

    private static int minBa(String[] string, int[] arr) {

        int tem = 0;
        int num = 0;

        for (int i=0;i<string.length-1;i++){
            for (int j=i+1;j<string.length;j++){
                if (judge(string[i]+string[j])){
                    if (num == 0) {
                        num = arr[i]+arr[j];

                    }else {
                        tem = arr[i]+arr[j];
                        if (tem < num) {
                            num = tem;
                        }
                    }
                }
            }
        }
        return num;

    }

    private static boolean judge(String s) {
        if (s.contains("A")&&s.contains("B")&&s.contains("C")&&s.length()==3){
            return true;
        }else {
            return false;
        }
    }


    public static void main6(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {

            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] a = new int[1000];
            for (int i=0;i<n;i++){
                a[i] = sc.nextInt();
            }

            int[][] dp = new int[1000][1000];

            for (int i=1;i<=n;i++){
                dp[i][1] = 1;
            }

            for (int i=1;i<=n;i++){
                for (int j=1;j<=k;j++){

                    for (int z=1;z<=i;z++){
                        if (a[i]>a[z])dp[i][j]+=dp[z][j-1];


                    }
                }
            }

            int ans = 0;
            for (int i=1;i<=n;i++){
                ans +=dp[i][k];
                ans%=1000007;

            }
            System.out.println(ans);

        }
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String S = sc.next();
            String T = sc.next();
            long s = Integer.parseInt(S);
            long t = Integer.parseInt(T);
            int count = 0,l=0;
            if (t < 666) {
                System.out.println(0);
                return;
            }

            for (long i=s;i<=t;i++){

                long z = i;
                count = 0;
                while (z > 0) {
                    long k = z%10;
                    if (k == 6) {
                        count++;
                    }else{
                        count=0;
                    }

                    z/=10;
                    if (count >= 3) {
                        l++;
                        break;
                    }

                }
            }
            System.out.println(l);
            //System.out.println(t-s+1-l);

        }
    }


    public static int sum(){
        int count =0;

        for (int i=1;i<2021;i+=2){
            int m=2;

            while (m<=i){
                if (2020 % m ==0&& i % m==0) {
                    count++;
                    break;
                }

                m++;

            }

        }

        return count;
    }
}
