package Search;

import Graphs.Graph;
import Visualize.ViewGraph;

public class DFS {

    private boolean[] marked;
    private int count = 0;

    // Takes input the graph on which DFS needs to be performed and from which vertex
    public DFS(Graph G, int v) {
        if (G == null) {
            throw new IllegalArgumentException("The Graph cannot be null");
        }
        if (v >= G.V()) {
            throw new IllegalArgumentException("The start vertix should be in the Graph");
        }
        this.marked = new boolean[G.V()];
        dfs(G, v);
    }

    // Recursive function which performs depth first search
    private void dfs(Graph G, int v) {
        this.marked[v] = true;
        this.count++;
        for (int w : G.adj(v)) {
            if (!this.marked[w]) {
                dfs(G, w);
            }
        }
    }

    // Returns if a vertex is reachable from the initial vertex v
    public boolean reachable(int w) {
        if (w > this.marked.length - 1) {
            throw new IllegalArgumentException("Vertex not in graph");
        }
        return this.marked[w];
    }

    // Returns no of vertices that are reachable from initial vertex v including itself
    public int count() {
        return this.count;
    }

    public static void main(String[] args) {
        Graph G = new Graph("H???CBx");
        System.out.println(G);
        ViewGraph disp = new ViewGraph(G);
        disp.display();
        DFS dfs = new DFS(G, 0);
        System.out.print("Is Graph connected to 5: ");
        System.out.println(dfs.reachable(5));
        System.out.println("No of vertices reachable from 0: " + dfs.count());
    }
}
