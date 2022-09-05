import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

public class dGraphTester {

    @Test
    public void test() throws FileNotFoundException {

        ArrayList<String[]> nodes = PA11Main
                .fileRead("PublicTestCases/az.mtx");
        DGraph graph = new DGraph(nodes.size());
        PA11Main.addNodes(graph, nodes);

        System.out.println(graph.toDotString());

        assertEquals(graph.toDotString(), "digraph {\n"
                + "1 -> 2 [label=\"113.0\"];\n"
                + "1 -> 3 [label=\"212.45\"];\n"
                + "1 -> 4 [label=\"187.95\"];\n"
                + "1 -> 5 [label=\"209.48\"];\n"
                + "2 -> 1 [label=\"113.0\"];\n"
                + "2 -> 3 [label=\"99.48\"];\n"
                + "2 -> 4 [label=\"129.98\"];\n"
                + "2 -> 5 [label=\"144.38\"];\n"
                + "3 -> 1 [label=\"212.45\"];\n"
                + "3 -> 2 [label=\"99.48\"];\n"
                + "3 -> 4 [label=\"186.41\"];\n"
                + "3 -> 5 [label=\"64.95\"];\n"
                + "4 -> 1 [label=\"187.95\"];\n"
                + "4 -> 2 [label=\"129.98\"];\n"
                + "4 -> 3 [label=\"186.41\"];\n"
                + "4 -> 5 [label=\"138.92\"];\n"
                + "5 -> 1 [label=\"209.48\"];\n"
                + "5 -> 2 [label=\"144.38\"];\n"
                + "5 -> 3 [label=\"64.95\"];\n"
                + "5 -> 4 [label=\"138.92\"];\n"
                + "}\n" + "");

    }

    @Test
    public void test2() throws FileNotFoundException {

        ArrayList<String[]> nodes = PA11Main.fileRead("PublicTestCases/az.mtx");
        DGraph graph = new DGraph(nodes.size());
        PA11Main.addNodes(graph, nodes);

        System.out.println(graph.getNeighbors(1));

    }

    @Test
    public void test3() throws FileNotFoundException {

        ArrayList<String[]> nodes = PA11Main.fileRead("PublicTestCases/az.mtx");
        DGraph graph = new DGraph(nodes.size());
        PA11Main.addNodes(graph, nodes);

        System.out.println(graph.getWeight(5, 2));

    }

    @Test
    public void test4() throws FileNotFoundException {

        ArrayList<String[]> nodes = PA11Main.fileRead("PublicTestCases/az.mtx");
        DGraph graph = new DGraph(nodes.size());
        PA11Main.addNodes(graph, nodes);

        System.out.println(graph.getWeight(8, 24));

    }

    @Test
    public void test5() throws FileNotFoundException {

        ArrayList<String[]> nodes = PA11Main
                .fileRead("PublicTestCases/example.mtx");
        int numNodes = PA11Main.getNumNodes("PublicTestCases/example.mtx");
        DGraph graph = new DGraph(numNodes);
        PA11Main.addNodes(graph, nodes);

        PA11Main.heuristic(numNodes, graph);

    }

    @Test
    public void test6() throws FileNotFoundException {

        ArrayList<String[]> nodes = PA11Main
                .fileRead("PublicTestCases/big11.mtx");
        int numNodes = PA11Main.getNumNodes("PublicTestCases/big11.mtx");
        DGraph graph = new DGraph(numNodes);
        PA11Main.addNodes(graph, nodes);

        PA11Main.heuristic(numNodes, graph);

    }

}
