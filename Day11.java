package 蓝桥;

import java.util.Scanner;

//动态规划题
public class Day11 {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] a = new int[4];
        for (int i=1;i<=3;i++){
            a[i] = sc.nextInt();
        }

        int sum=0;
        for (int i=1;i<=3;i++){
            sum+=a[i];
        }

        System.out.println(sum);
    }

    //小蓝希望，从第 11 行第 11 列走到第 n 行第 m列后，只能向右或者向下走，总的权值和最大。请问最大是多少？
    //方法1：
    public static void main2(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[][] weight = new int[n+1][m+1];
        for (int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                weight[i][j] = sc.nextInt();
            }
        }

        System.out.println(maxWeight(weight));

    }


    private static int maxWeight(int[][] weight) {

        int row = weight.length;
        int col = weight[0].length;
        int[][] maxD = new int[row+1][col+1];

        int max = -1000000;

        for (int i=1;i<=row;i++){
            for (int j=1;j<=col;j++){

                if (i ==1&&j==1) {
                    maxD[i][j] = weight[i][j];
                    continue;
                }

                if (j-1>= 1) max = Math.max(maxD[i][j-1],max);

                if(j-2>=1) max=Math.max(maxD[i][j-2],max);

                if(j-3>=1) max=Math.max(maxD[i][j-3],max);

                if(i-1>=1) max=Math.max(maxD[i-1][j],max);

                if(i-1>=1 && j-1>=1) max=Math.max(maxD[i-1][j-1],max);

                if(i-1>=1 && j-2>=1) max=Math.max(maxD[i-1][j-2],max);

                if(i-2>=1) max=Math.max(maxD[i-2][j],max);

                if(i-2>=1 && j-1>=1) max=Math.max(maxD[i-2][j-1],max);

                if(i-3>=1) max=Math.max(maxD[i-3][j],max);

                maxD[i][j] = max+weight[i][j];

            }
        }

        return maxD[row][col];

    }


    //方法1的改进
    static int maxD[][]=new int[101][101];  //总数方格 n行m列
    static int maxdp(int r,int c) {

        int max=-1000;
        if(c-1>=1) max=Math.max(maxD[r][c-1],max);
        if(c-2>=1) max=Math.max(maxD[r][c-2],max);
        if(c-3>=1) max=Math.max(maxD[r][c-3],max);

        if(r-1>=1) max=Math.max(maxD[r-1][c],max);
        if(r-1>=1 && c-1>=1) max=Math.max(maxD[r-1][c-1],max);
        if(r-1>=1 && c-2>=1) max=Math.max(maxD[r-1][c-2],max);

        if(r-2>=1) max=Math.max(maxD[r-2][c],max);
        if(r-2>=1 && c-1>=1) max=Math.max(maxD[r-2][c-1],max);

        if(r-3>=1) max=Math.max(maxD[r-3][c],max);
        return max;
    }


    public static void main1(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int map[][]=new int[n+1][m+1];  //存储方格 n行m列

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                map[i][j]=sc.nextInt();
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if(i==1 && j==1) {
                    maxD[i][j]=map[i][j];
                    continue;
                }

                maxD[i][j]=map[i][j]+maxdp(i,j);

            }
        }

        System.out.println(maxD[n][m]);
    }
}


