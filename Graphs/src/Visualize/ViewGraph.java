package Visualize;

import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;

public class ViewGraph {
    private Graph dG;

    public ViewGraph(Graphs.Graph G){
        dG = new MultiGraph("Trial");
        dG.addAttribute("ui.stylesheet", " graph {fill-color: #34495e;} " +
                "node { size: 20px; fill-color:#16a085; " +
                "text-mode: normal; text-size: 50px; text-style: italic; text-color: #bdc3c7;  }" +
                "edge {size: 4px; fill-color: #7f8c8d;}");
        for (int i = 0; i < G.V(); i++) {
            dG.addNode("N" + i);
        }
        for (int i = 0; i < G.V(); i++) {
            for (int j : G.adj(i)) {
                dG.addEdge(i + "->" + j, i, j);
            }
        }
        for (Node node : dG) {
            node.addAttribute("ui.label", node.getId());
        }
    }

    public void display(){
        dG.display();
    }

    public static void main(String[] args) {
//        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        Graphs.Graph G = new Graphs.Graph("GTnvv{");
        System.out.println(G);
        ViewGraph viewGraph = new ViewGraph(G);
        viewGraph.display();
    }
}
