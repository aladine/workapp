import java.util.*;

/**
 * Created by sgscuser9 on 5/7/2015.
 */
//public class IntegerPair {
//    int x;
//    int y;
//
//    public IntegerPair(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//
//    public IntegerPair(int[] a) {
//        this.x = a[0];
//        this.y = a[1];
//    }
//}

class IntegerPair implements Comparable {
    private Integer _first, _second;

    public IntegerPair(Integer f, Integer s) {
        _first = f;
        _second = s;
    }

    Integer first() { return _first; }
    Integer second() { return _second; }

    public int compareTo(Object o) {
        IntegerPair ip = (IntegerPair)o ;
        if (!this.first().equals(ip.first()))
            return this.first() - ((IntegerPair)o).first();
        else
            return this.second() - ((IntegerPair)o).second();
    }

    public String toString(){
        return "First: "+_first+" Second: "+ _second;
    }
}

class IntegerTriple implements Comparable {
    private Integer _first, _second, _third;

    public IntegerTriple(Integer f, Integer s, Integer t) {
        _first = f;
        _second = s;
        _third = t;
    }

    Integer first() { return _first; }
    Integer second() { return _second; }
    Integer third() { return _third; }

    public int compareTo(Object o) {
        if (!this.first().equals(((IntegerTriple)o).first()))
            return this.first() - ((IntegerTriple)o).first();
        else if (!this.second().equals(((IntegerTriple)o).second()))
            return this.second() - ((IntegerTriple)o).second();
        else
            return this.third() - ((IntegerTriple)o).third();
    }
}

class Node<T>{
    public T data;
    public boolean isUsed = false;
    public final HashSet<Edge> inEdges;
    public final HashSet<Edge> outEdges;
    public Node(T data) {
        this.data = data;
        this.inEdges = new HashSet<Edge>();
        this.outEdges = new HashSet<Edge>();
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }
}

class Edge<T>{
    public final Node<T> from;
    public final Node<T> to;

    public Edge(Node<T> from, Node<T> to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean equals(Object obj) {
        Edge e = (Edge)obj;
        return e.from == from && e.to == to;
    }
}

class Graph<T> {
    public Map<String, Node<T>> nodes;

    public Graph() {
        this.nodes = new HashMap<String, Node<T>>();
    }

    public List<T> topologicalSort(){
        List<T> list = new ArrayList<T>();
        //S <- Set of all nodes with no incoming edges, it always non-empty at the beginning
        HashSet<Node<T>> S = new HashSet<Node<T>>();
        for(Node<T> n : this.nodes.values()){
            if(n.inEdges.size() == 0){
                S.add(n);
            }
        }

        //while S is non-empty do
        while(!S.isEmpty()){
            //remove a node n from S
            Node<T> n = S.iterator().next();
            S.remove(n);

            //insert n into L
            if(n.isUsed)list.add(n.data);

            //for each node m with an edge e from n to m do
            for(Iterator<Edge> it = n.outEdges.iterator();it.hasNext();){
                //remove edge e from the graph
                Edge e = it.next();
                Node<T> m = e.to;
                it.remove();//Remove edge from n
                m.inEdges.remove(e);//Remove edge from m

                //if m has no other incoming edges then insert m into S
                if(m.inEdges.isEmpty()){
                    S.add(m);
                }
            }
        }
        return list;
    }
    public void addEdge(String fr, String to){
        Node<T> first = nodes.get(fr);
        Node<T> second = nodes.get(to);
        Edge e = new Edge(first,second);
        if(!first.outEdges.contains(e))first.outEdges.add(e);
        if(!second.inEdges.contains(e))second.inEdges.add(e);
    }

    public Node<T> addIfNotExist(String sourceURI, T data) {
        if(!nodes.containsKey(sourceURI)){
            nodes.put(sourceURI,new Node<T>(data));
        }
        return nodes.get(sourceURI);
    }
}
