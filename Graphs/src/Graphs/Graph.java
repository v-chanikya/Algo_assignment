package Graphs;

import edu.princeton.cs.algs4.Bag;

public class Graph {

    private int edges;
    private int vertices;

    private Bag<Integer>[] vertices_list;

    // The constructor takes string of format g6 and creates a graph
    public Graph(String graph6) {
        byte[] bytes = graph6.getBytes();
        int v = bytes[0] - 63;
        this.vertices = v;
        this.vertices_list = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            this.vertices_list[i] = new Bag<>();
        }
        int x1 = 0, y1 = 1;
        for (int i = 1; i < bytes.length; i++) {
            int b = bytes[i] - 63;
            int[] a = new int[6];
            a[0] = b & 0b00100000;
            a[1] = b & 0b00010000;
            a[2] = b & 0b00001000;
            a[3] = b & 0b00000100;
            a[4] = b & 0b00000010;
            a[5] = b & 0b00000001;
            for (int j = 0; j < 6; j++) {
                if (a[j] >= 1) {
                    this.addEdge(x1, y1);
                    this.edges++;
                }
                if (++x1 == y1) {
                    y1++;
                    x1 = 0;
                }
            }
        }

    }

    // Create a graph of v vertices with no edges
    public Graph(int v) {
        this.vertices = v;
        this.edges = 0;
        this.vertices_list = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            this.vertices_list[i] = new Bag<>();
        }
    }

    // Number of Edges
    public int E() {
        return this.edges;
    }

    // Number of vertices
    public int V() {
        return this.vertices;
    }

    // Add an edge to graph
    public void addEdge(int v, int w) {
        this.vertices_list[v].add(w);
        this.vertices_list[w].add(v);
    }

    // List of all the vertices connected to vertex v
    public Iterable<Integer> adj(int v) {
        return this.vertices_list[v];
    }

    // Prints the Graph as string to stdout
    public void printGraph() {
        for (int i = 0; i < this.vertices; i++) {
            System.out.print(i);
            System.out.print(": ");
            for (int w : this.adj(i)) {
                System.out.print(w);
                System.out.print(" ");
            }
            System.out.print(";\n");
        }
    }

    // Returns the Graph as String
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < this.vertices; i++) {
            stringBuilder.append(i);
            stringBuilder.append(": ");
            for (int w : this.adj(i)) {
                stringBuilder.append(w);
                stringBuilder.append(" ");
            }
            stringBuilder.append(";\n");
        }
        return stringBuilder.toString();
    }

    // Function reads from a given file list of graphs in g6 format and creates them
    public static void main(String[] args) {
        // Construct an empty graph
        Graph G = new Graph(5);
        G.printGraph();
        // Construct a G6 graph
        Graph G6 = new Graph("GTnvv{");
        System.out.println("No of vertices: " + G6.V() + "\nNo of Edges: " + G6.E());
        G6.printGraph();
    }
}
