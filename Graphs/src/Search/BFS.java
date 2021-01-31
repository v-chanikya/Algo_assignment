package Search;

import Graphs.Graph;
import Visualize.ViewGraph;
import edu.princeton.cs.algs4.Queue;

public class BFS {

    private boolean[] marked;
    private int count = 1;

    // Constructor
    // Takes input the graph on which BFS needs to be performed and from which vertex
    public BFS(Graph G, int v) {
        if (G == null) {
            throw new IllegalArgumentException("The Graph cannot be null");
        }
        if (v >= G.V()) {
            throw new IllegalArgumentException("The start vertex should be in the Graph");
        }
        marked = new boolean[G.V()];
        bfs(G, v);
    }

    // This function performs breadth first search on given vertex
    private void bfs(Graph G, int v) {
        Queue<Integer> next_index = new Queue<>();
        next_index.enqueue(v);
        marked[v] = true;
        while (!next_index.isEmpty()) {
            for (int w : G.adj(next_index.dequeue())) {
                if (!marked[w]) {
                    marked[w] = true;
                    next_index.enqueue(w);
                    this.count++;
                }
            }
        }
    }

    // Returns if an vertex is reachable from initial vertex v
    public boolean reachable(int w) {
        if (w > this.marked.length - 1) {
            throw new IllegalArgumentException("Vertex not in graph");
        }
        return marked[w];
    }

    // Returns no of vertices reachable from initial vertex v including it self
    public int count() {
        return this.count;
    }

    public static void main(String[] args) {
        Graph G = new Graph("H???CBx");
        BFS bfs_0 = new BFS(G, 0);
        ViewGraph vg = new ViewGraph(G);
        vg.display();
        System.out.println(bfs_0.reachable(4));
        System.out.println(bfs_0.reachable(5));
        System.out.println("No of vertices reachable from 0: " + bfs_0.count());
    }
}
