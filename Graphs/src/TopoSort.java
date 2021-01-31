import Graphs.Graph;
import Search.DFS;

public class TopoSort {

    private int[] mydfs_vertices;

    public TopoSort(Graph G, int v) {
        this.mydfs_vertices = new int[G.V()];
        // Check weather graph is cyclic,
        // we cannot find order in cyclic graphs henceforth we cannot sort it topologically
        if (this.isDAG(G, v)) {
            // Topological sort is nothing but depth first search
            DFS dfs = new DFS(G, v);
        } else {
            throw new IllegalArgumentException("The graph provided is cyclic, so cannot sort topologically");
        }
    }

    // Check weather the given graph is Directed Acyclic Graph
    private boolean isDAG(Graph G, int v) {
        mydfs(G, v, v);
        return true;
    }

    private void mydfs(Graph G, int v, int start) {

    }

    public static void main() {
        System.out.println("Hello topo sort");
    }
}
