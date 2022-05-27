import java.util.*;

public class WeightedGraph<Vertex> {
    private final boolean undirected;
    private Map<Vertex, List<Edge<Vertex>>> map = new HashMap<>();

    public WeightedGraph() {
        this.undirected = true;
    }

    public WeightedGraph(boolean undirected) {
        this.undirected = undirected;
    }

    public void addVertex(Vertex v) {
        map.put(v, new LinkedList<>());
    }

    public void addEdge(Vertex source, Vertex dest, double weight) {
        if (!hasVertex(source))
            addVertex(source);

        if (!hasVertex(dest))
            addVertex(dest);

        if (hasEdge(source, dest)
                || source.equals(dest))
            return;
    }

    public int getVerticesCount() {
        return map.size();
    }

    public int getEdgesCount() {
        int count = 0;
        for (Vertex v : map.keySet()) {
            count += map.get(v).size();
        }

        if (undirected)
            count /= 2;

        return count;
    }


    public boolean hasVertex(Vertex v) {
        return map.containsKey(v);
    }

    public boolean hasEdge(Vertex source, Vertex dest) {
        if (!hasVertex(source)){
            return false;
        }
        return map.get(source).contains(new Edge<>(source, dest));
    }

    public Iterable<Vertex> adjacencyList(Vertex v) {
        if (!hasVertex(v)) return null;
        List<Vertex> vertexes = new LinkedList<>();
        for (Edge<Vertex> e : map.get(v)) {
            vertexes.add(e.getDest());
        }
        return vertexes;
    }

    public Iterable<Edge<Vertex>> getEdges(Vertex v) {
        if (!hasVertex(v)) return null;
        return map.get(v);
    }
}
