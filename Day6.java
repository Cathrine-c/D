package 蓝桥;

import java.util.*;

public class Day6 {

    public static void main1(String[] args) {

        Scanner sc = new Scanner(System.in);

            double H = sc.nextDouble();
            double S1 = sc.nextDouble();
            double V = sc.nextDouble();
            double L = sc.nextDouble();
            double K = sc.nextDouble();
            int n = sc.nextInt();

           double maxt0 = Math.sqrt(H/5);
           double mint0 = Math.sqrt((H-K)/5);

                double minS0 = S1-mint0*V+L;
                double maxS0 = S1-maxt0*V;
                int x = (int) Math.min(minS0,n);
                int y = (int) Math.max(maxS0,0);

            System.out.println(x-y);
        }



        public static void bfs(String A,String B,int n,String[][] ch){
            Map<String,Integer> map1 = new HashMap<>();
            Map<String,Integer> map2 = new HashMap<>();

            Queue<String> q1 = new LinkedList<>();
            Queue<String> q2 = new LinkedList<>();

            map1.put("A",1);
            map2.put("B",1);
            q1.offer(A);
            q2.offer(B);

            while (true){
                if (q1.isEmpty() || q2.isEmpty()) {
                    System.out.println("NO ANSWER!");
                    return;
                }

                String ta = q1.peek();
                String tb = q2.peek();
                if (map1.get(ta)>11||map2.get(tb)>11){
                    System.out.println("NO ANSWER!");
                    return;
                }

                for (int i=1;i<=n;i++){
                    int k=0;
                    while (ta.indexOf(ch[i][0],k)!=-1){
                        k = ta.indexOf(ch[i][0],k);
                        int l = ch[i][0].length();
                        ta.replaceAll(ch[i][0],ch[i][1]);
                        if (map1.get(ta) == 0) {
                            map1.put(ta,map1.get(q1.peek())+1);
                            q1.offer(ta);

                        }
                        if (map2.get(ta)!=0&&map1.get(ta)+map2.get(ta)-2<=10){
                            System.out.println(map1.get(ta)+map2.get(ta)-2);
                            return;
                        }
                        k++;
                        ta = q1.peek();

                    }
                }
                for (int i=1;i<=n;i++){
                    int k=0;
                    while (tb.indexOf(ch[i][1],k)!=-1){
                        k = tb.indexOf(ch[i][1],k);
                        int l = ch[i][1].length();
                        tb.replace(ch[i][1],ch[i][0]);
                        if (map2.get(tb) == 0) {
                            map2.put(tb,map2.get(q2.peek()+1));
                            q2.offer(tb);
                        }

                        if (map1.get(tb) != 0 && map1.get(ta) + map2.get(ta) - 2 <= 10) {
                            System.out.println(map1.get(tb)+map2.get(tb)-2);
                            return;
                        }
                        
                        k++;
                        tb = q2.peek();
                    }
                }
                q1.poll();
                q2.poll();

            }

        }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String A = sc.next();
        String B =sc.next();
        int n = sc.nextInt();

        String[][] ch = new String[n][2];
        for (int i=0;i<n;i++){
            ch[i][0] = sc.next();
            ch[i][1] = sc.next();

        }

        bfs(A,B,n,ch);

    }


}
