import java.util.*;

/**
 * Created by sgscuser9 on 5/14/2015.
 */
//3
//        2 1 0 1
//        0 1 100
//        3 3 2 0
//        0 1 100
//        0 2 200
//        1 2 50
//        2 0 0 1
public class SendingEmail {
    public static final int INF = 1000000000;
    public static Vector<Vector<IntegerPair>> AdjList = new Vector<Vector<IntegerPair>>();
    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.readInt();
        int m = in.readInt();
        int S = in.readInt();// start
        int T = in.readInt();// end

        //init AdjList
        AdjList.clear();
        for (int i = 0; i < n; i++) {
            AdjList.add(new Vector<IntegerPair>());
        }

        for (int i = 0; i < m; i++) {
            int server1 = in.readInt();
            int server2 = in.readInt();
            int weight = in.readInt();
            AdjList.get(server1).add(new IntegerPair(server2, weight));
            AdjList.get(server2).add(new IntegerPair(server1, weight));
        }

        // Dijkstra routine
        Vector<Integer> dist = new Vector<Integer>();
        dist.addAll(Collections.nCopies(n, INF));
        dist.set(S, 0);

        PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>(1,
                new Comparator<IntegerPair>() {
                    @Override
                    public int compare(IntegerPair o1, IntegerPair o2) {
                        return o1.first() - o2.first();
                    }
                }
        );
        pq.offer(new IntegerPair(0, S));
        while(!pq.isEmpty()){
            IntegerPair top = pq.poll();
            int u = top.second();
            if(top.first() > dist.get(u)) continue;// unvisited
            Iterator it = AdjList.get(u).iterator();
            while (it.hasNext()) {
                IntegerPair p = (IntegerPair)it.next();
                int v = p.first();
                int weight_u_v = p.second();
                if(dist.get(u) + weight_u_v < dist.get(v)){
                    dist.set(v,dist.get(u) + weight_u_v );
                    pq.offer(new IntegerPair(dist.get(v), v));
                }
                if(v == T) break;
            }
        }
        System.out.printf("Case #%d: %s\n", testNumber, dist.get(T) != INF ? dist.get(T) : "unreachable");
//        for (int i = 0; i < n; i++) // index + 1 for final answer
//            System.out.printf("SSSP(%d, %d) = %d\n", S , i, dist.get(i));
    }
}
