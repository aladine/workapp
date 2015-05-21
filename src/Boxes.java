

/**
 * Created by sgscuser9 on 5/7/2015.
 */
public class Boxes {
    public void solve(int testcase, InputReader in, OutputWriter out){
        int N = in.readInt();
        int[] weight = new int[N+1];
        int[] max_load = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            weight[i] = in.readInt();
            max_load[i] = in.readInt();
        }
        int dp[][] = new int[N+1][10000];
        for (int j = weight[N]; j < 3000; j++) {
            dp[N][j] = 1;
        }
//        Let a two-dimensional array element DP[i][j] be the max. number of boxes whose ID ranges from 1 to i
// that can be stacked on the surface whose load capacity is j. Let the range of i and j be [1,I] and [0,J] respectively.


        for (int i = N-1; i > 1  ; i--) {
            for (int j = 3000; j > 0 ; j--) {
                int m = (max_load[i] >  j - weight[i] && j - weight[i] > 0) ? j - weight[i] : max_load[i];
                dp[i][j]  = Math.max(dp[i+1][j], 1 + dp[i+1][m] );
            }
        }

        int max = 0;
        for (int i = N-1; i > 1  ; i--) {
            for (int j = 3000; j > 0 ; j--) {
                if(max < dp[i][j]) max = dp[i][j];
            }
        }
        System.out.println(max);
    }
}
