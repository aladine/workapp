import java.util.*;

/**
 * Created by sgscuser9 on 5/14/2015.
 */
public class LiftHopping {
    public static final int INF = 1000000000;
    public static Vector<Vector<IntegerPair>> AdjList = new Vector<Vector<IntegerPair>>();
    public void solve(int n, int k, InputReader in, OutputWriter out) {
        int[] T = IOUtils.readIntArray(in, n);
        int N = 100;
        AdjList.clear();
        AdjList.addAll(Collections.nCopies(N,new Vector<IntegerPair>()));

        for (int i = 0; i < n; i++) {
            String[] neibours = in.next().split(",");
//            System.out.println(neibours.length);
            int[] arr = new int[neibours.length];
            for (int j = 0; j < neibours.length; j++) {
                arr[j] = Integer.parseInt(neibours[j]);
            }

            for (int j = 0; j < arr.length; j++) {
                for (int l = 0; l < j; l++) {
                    int cost = (j - l + 1)*T[i];
                    System.out.println(cost);
                    AdjList.get(j).add(new IntegerPair(l, cost)); /// should use IntegerTriple
                    AdjList.get(l).add(new IntegerPair(j, cost));
//                    IntegerTriple
                }
            }


            Vector<Integer> dist = new Vector<Integer>();
            dist.addAll(Collections.nCopies(N, INF));
            dist.set(k, 0);
            PriorityQueue<IntegerPair> pq = new PriorityQueue<IntegerPair>(1,
                        new Comparator<IntegerPair>() {
                            @Override
                            public int compare(IntegerPair o1, IntegerPair o2) {
                                return o1.first() - o2.first();
                            }
                        }
                    );
            pq.offer(new IntegerPair(0, k));
            while(!pq.isEmpty()){
                IntegerPair top = pq.poll();
                int u = top.first();// weight
                int v = top.second();
                if(u > dist.get(v)){
                    continue;
                }

                Iterator<IntegerPair> it  = AdjList.get(v).iterator();
                while(it.hasNext()){
                    IntegerPair p = it.next();
                    int vertex = p.first();
                    int weight_u_v = p.second();
                    System.out.println(vertex+"<-"+v);
                    System.out.println(":::"+dist.get(v));
                    System.out.println(dist.get(v) + weight_u_v + 60);
                    if(dist.get(v) + weight_u_v + 60 < dist.get(vertex)){
                        dist.set(vertex, dist.get(v) + weight_u_v + 60);
                        pq.offer(new IntegerPair(dist.get(vertex), vertex));
                    }
                }
            }

            for (int d = 0; d < N; d++) // index + 1 for final answer
            System.out.printf("SSSP(%d, %d) = %d\n", k , d, dist.get(d));

        }
    }
}
