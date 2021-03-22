package 蓝桥;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            int m = sc.nextInt();
            int n = sc.nextInt();
            int[][] a = new int[n][2];
            int[][] b = new int[m][2];

            for (int i=0;i<n;i++){
                a[i][0] = sc.nextInt();
                a[i][1] = sc.nextInt();

            }

            for (int i=0;i<m;i++){
                b[i][0] = sc.nextInt();
                b[i][1] = sc.nextInt();
            }

            int i=0,j=0;

            int c =0;
            while (i<m&&j<n){
                if (b[i][0]==a[j][0]){
                    c +=a[j][1];
                }

                j++;
                if (b[i][1]<=a[j][0]){
                     c+=a[j][1];
                }

                i++;
                System.out.println(c);
                c=0;
            }

        }
    }





    public static void main10(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {

            int n =sc.nextInt();
            int[][] a = new int[n][0];
            for (int i=0;i<n;i++){
                a[i][0] = sc.nextInt();
                a[i][1] = sc.nextInt();

            }




        }
    }
    public static void main9(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int N=sc.nextInt();
            int[][] a = new int[N][2];
            for (int i=0;i<N;i++){

                a[i][0] = sc.nextInt();
                a[i][1] = sc.nextInt();

            }

            double c =0;
            for (int i=0;i<N;i++){
                for (int j=i+1;j<N;j++){

                    c+=Math.sqrt(Math.pow(a[j][0]-a[i][0],2)+Math.pow(a[j][1]-a[i][1],2));

                }

            }

            if (c == 0) {
                System.out.println(1);
                return;
            }

            long n =Math.round(c);
            System.out.println(n);
        }
    }



    public static void main6(String[] args) {
        Scanner sc = new Scanner(System.in);

       // while (sc.hasNext()) {
            int n = sc.nextInt();
            int[][] a = new int[n][4];

            for (int i=0;i<n;i++){
                a[i][0] = sc.nextInt();
                a[i][1] = sc.nextInt();
                a[i][2] = sc.nextInt();
                a[i][3] = sc.nextInt();

            }

            long sum = 0;

            for (int i=0;i<n;i++){
                sum +=Math.abs(a[i][2]-a[i][0])*Math.abs(a[i][3]-a[i][1]);
            }

            long s =0,minx=0,miny=0,maxx=0,maxy=0;

            for (int i=0;i<n;i++){
                for (int j=i+1;j<n;j++){

                     minx = Math.max(a[i][0],a[j][0]);
                     miny = Math.max(a[i][1],a[j][1]);
                     maxx = Math.min(a[i][2],a[j][2]);
                     maxy = Math.min(a[i][3],a[j][3]);

                     if (minx<maxx&&miny<maxy){

                         s+=Math.abs(maxx-minx)*Math.abs(maxy-miny);


                     }else {
                         continue;
                     }
                }
            }

            long c = sum-s;
            System.out.println(c);

    }


    //采草药,使采到的总总药量最大
    public static void main7(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t=sc.nextInt();
        int m=sc.nextInt();
        int ti[]=new int[m];
        int V[]=new int[m];
        for(int x=0;x<m;x++) {
            ti[x]=sc.nextInt();
            V[x]=sc.nextInt();
        }


        int dp[][]=new int[m+1][t+1];
        for(int x=1;x<=m;x++) {
            for(int y=1;y<=t;y++) {
                if(ti[x-1]<=y)
                    dp[x][y]=Math.max(dp[x-1][y],dp[x-1][y-ti[x-1]]+V[x-1]);
                else
                    dp[x][y]=dp[x-1][y];
            }
        }

        System.out.println(dp[m][t]);
    }


//            //草药的数量
//            int N = sc.nextInt();
//            int[][] a = new int[N][3];
//            for (int i=0;i<N;i++){
//                //i草药需要的采摘时间
//                a[i][0] = sc.nextInt();
//                //i草药含有的总药量
//                a[i][1] = sc.nextInt();
//                //i草药每秒丧失的药量
//                a[i][2] = sc.nextInt();
//
//            }








        public static void diplay(int n){
            for(int i =0;i<n;i++){
                System.out.println("Hello SUST!");
            }
        }

        public static void main1(String[] args) {
            Scanner scanner = new Scanner(System.in);
            int n = scanner.nextInt();
            diplay(n);
        }

    }




