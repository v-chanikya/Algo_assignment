import Graphs.Graph;
import Visualize.ViewGraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> graphs = new ArrayList<String>();
        try {
            File file = new File(args[0]);
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine())
                graphs.add(myReader.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Random rg = new Random();
        System.out.println("size of graphs is: " + graphs.size());
        int gind = rg.nextInt(graphs.size());
        System.out.println("The Graph is at: " + gind + " and graph is: " + graphs.get(gind));
        Graph G = new Graph(graphs.get(gind));
        System.out.println(G);
        ViewGraph vg = new ViewGraph(G);
        vg.display();
    }
}
