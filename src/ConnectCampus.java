import java.util.*;

/**
 * Created by sgscuser9 on 5/7/2015.
 */
//p10397
public class ConnectCampus {
    Vector<IntegerPair> locations = new Vector<IntegerPair>();

//    Vector<Vector< Pair<Double, Integer>>> AdjList = new Vector<Vector<Pair<Double, Integer>>>();

    
    public void solve(int N, InputReader in, OutputWriter out) {
        for (int i = 0; i < N; i++) {
            locations.add(new IntegerPair(in.readInt(), in.readInt()));
        }

        Vector<IntegerTriple> EdgeList = new Vector<IntegerTriple>();

        int M = in.readInt();// number of cables
        double w;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                w = getDistance(i,j);
                EdgeList.add(new IntegerTriple(w, i, j));
            }
        }



        double mst_cost = 0;
        UnionFind UF = new UnionFind(N);

        for (int i = 0; i < M; i++) {
            int m1 = in.readInt();m1--;
            int m2 = in.readInt();m2--;
            w = getDistance(m1,m2);
            UF.unionSet(m1, m2);
        }

        Collections.sort(EdgeList);
        for (int i = 0; i < N; i++) {
            IntegerTriple front = EdgeList.get(i);
            if (!UF.isSameSet(front.second(), front.third())) {          // check
//                System.out.println("add more "+front.first());
                mst_cost += front.first();            // add the weight of e to MST
                UF.unionSet(front.second(), front.third());            // link them
            }
        }
        System.out.printf("%.2f",mst_cost);
    }

    private double getDistance(int m1, int m2) {
        double diff_x = locations.get(m1).first() - locations.get(m2).first();
        double diff_y = locations.get(m1).second() - locations.get(m2).second();
//        System.out.println( locations.get(m1).toString()+"-"+ locations.get(m2).toString()+"->>"+Math.sqrt(diff_x* diff_x + diff_y* diff_y));
        return Math.sqrt(diff_x* diff_x + diff_y* diff_y);
    }

}

class Pair<X,Y>{
    X _first;
    Y _second;
    public Pair(X x, Y y){
        _first = x;
        _second = y;
    }


    public X first(){
        return _first;
    }

    public Y second(){
        return _second;
    }


}
