import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by sgscuser9 on 5/22/2015.
 * Similar to Boxes.java
 */
public class Turtle {
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int N = in.readInt();
        int[] weight = new int[N], capacity = new int[N];
        int[] T =  new int[10005];

        PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>(1, new Comparator<IntegerPair>() {
            @Override
            public int compare(IntegerPair o1, IntegerPair o2) {
                if(o1.second() == o2.second()) return o1.first() - o2.first();
                return o1.second() - o2.second();
            }
        });

        int k = 0;
        int next;
        int MAX_N = 10005;

        for (int i = 0; i < N; i++) {
            weight[i] = in.readInt();
            capacity[i] = in.readInt();
            if(capacity[i]-weight[i]>0) {
                pq.add(new IntegerPair(weight[i], capacity[i]-weight[i]));
            }
        }

        T[0] = 0;
        IntegerPair[] arr = (IntegerPair[]) (pq.toArray());
        int[][] dp = new int[MAX_N][MAX_N];

        for (int i = 0; i < N; i++) {
            int w = arr[i].first();
            int c = arr[i].second();
            for (int j = 0; j < i; j++) {
                if(dp[i-1][j] > c){
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1]+ w);
                }else{
                    dp[i][j] = dp[i-1][j];
                }

            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            if(dp[i][N] != MAX_N) {
                ans = i;
                break;
            }
        }
        System.out.println("Ans"+ ans);


    }
}
