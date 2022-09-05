import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
* AUTHOR: Rohan OMalley
* 
* FILE: PA11Main.java
* 
* ASSIGNMENT: Programming Assignment 11 - PA11Main 
* 
* COURSE: CSc 210; Section C; Spring 2022
* 
* PURPOSE: This program takes in an mtx file
* then takes node info from it then puts all the
* nodes into a graph. Then that graph is searched 
* through in 3 different algorithim types. Huerisitc,
* recursive backtracking and a design of my own. Also,
* the times of how long these algorithims took to
* compute can be displayed.
*/
public class PA11Main {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String[]> nodes = fileRead(args[0]);
        int numNodes = getNumNodes(args[0]);
        DGraph graph = new DGraph(numNodes);
        addNodes(graph, nodes);
        String command = args[1];

        if (command.equals("HEURISTIC")) {

            String trip = heuristic(numNodes, graph);
            System.out.println(trip);

        } else if (command.equals("BACKTRACK")) {

            String trip = backtrack(numNodes, graph);
            System.out.println(trip);

        } else if (command.toUpperCase().equals("MINE")) {

            String trip = mine(numNodes, graph);
            System.out.println(trip);

        } else if (command.toUpperCase().equals("TIME")) {

            long startTime = System.nanoTime();
            String trip = heuristic(numNodes, graph);
            long endTime = System.nanoTime();
            long duration = (endTime - startTime) / 1000000;
            System.out.println("heuristic: " + trip
                    + ", " + duration + " milliseconds");

            long startTimeMine = System.nanoTime();
            String tripMine = mine(numNodes, graph);
            long endTimeMine = System.nanoTime();
            long durationMine = (endTimeMine - startTimeMine) / 1000000;
            System.out.println(
                    "mine: " + tripMine + ", "
                    + durationMine + " milliseconds");

            long startTimeBT = System.nanoTime();
            String tripBT = backtrack(numNodes, graph);
            long endTimeBT = System.nanoTime();
            long durationBT = (endTimeBT - startTimeBT) / 1000000;
            System.out.println("backtrack: " + tripBT + ", " + durationBT
                    + " milliseconds");
        }

    }
    public static int getNumNodes(String fileName)
            throws FileNotFoundException {
        /*
         * Method reads through the file given and
         * goes till it finds the number of nodes
         * for the DGraph to have. Once it does
         * int is returned.
         * 
         * @param, filename, String of the file to read
         * through
         * 
         * @return, numNod, the number of nodes read from the
         * first line without % infront of it
         */
        File file = new File(fileName);
        Scanner fileReader = new Scanner(file);

        String sppline = "";
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            if (!line.startsWith("%")) {
                sppline = line;
                break;
            }
        }
        String[] splitLine = sppline.split("\\s+");
        int numNod = Integer.parseInt(splitLine[0]);
        fileReader.close();
        return numNod;
    }

    public static ArrayList<String[]> fileRead(String fileName)
            throws FileNotFoundException {
        /*
         * Method reads in a file then goes
         * through every line that doesn't have
         * a % at the beginning and splits the
         * line into 3 parts, first node mapped to
         * second node then the cost between those 2.
         * Each array is added to a List then the list
         * is returned when there are no more lines left
         * 
         * @param, fileName, String of file to read
         * 
         * @return, ArrayList<String[]>, List of arrays each array
         * having a first node mapped to second node and the cost of
         * the trip
         */
        File file = new File(fileName);
        Scanner fileReader = new Scanner(file);
        
        ArrayList<String[]> nodes = new ArrayList<String[]>();
        
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine(); 
            if (!line.startsWith("%")) {
                break;
            }
        }
        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            if (!line.startsWith("%")) {
                String[] lineSplit = line.split("\\s+");
                nodes.add(lineSplit);
            }
        }
        fileReader.close();
        return nodes;
    }

    public static void addNodes(DGraph graph, ArrayList<String[]> nodes) {
        /*
         * Method takes all the nodes from the List passed in
         * and adds the Edges to the graph. Turns the strings into
         * integers and doubles. Each edge is added for each line.
         * 
         * @param, DGraph graph, current graph to add the nodes to
         * 
         * @param, ArrayList<String[]> ndoes, the List of arrays with
         * all the edge pairs with the cost for each
         */
        for (int i = 0; i < nodes.size(); i++) {
            String[] cur = nodes.get(i);
            int node1 = Integer.parseInt(cur[0]);
            int node2 = Integer.parseInt(cur[1]);
            double weight = Double.parseDouble(cur[2]);
            graph.addEdge(node1, node2, weight);
        }
    }

    public static String heuristic(int numNode, DGraph graph) {
        /*
         * The heuristic algorithim we were assigned to fill
         * in. Creates a Trip object and goes city by city to find
         * the assumed best possible path. Then the path and cost is
         * printed out at the end of the loop.
         * 
         * @param, numNode, number of nodes in the graph
         * 
         * @param, DGraph graph, the current graph with all
         * the nodes to traverse
         * 
         * @return, String, trip cost string to be printed
         */
        Trip myTrip = new Trip(numNode);
        int curCity = 1;
        myTrip.chooseNextCity(curCity);

        for (int k = 2; k <= numNode; k++) {

            List<Integer> neigh = graph.getNeighbors(curCity);

            double closestWeight = Double.MAX_VALUE;
            int closestV = 0;
            

            for (int neighCity : neigh) {
                
                double w = graph.getWeight(curCity, neighCity);
                
                if (myTrip.isCityAvailable(neighCity) && w < closestWeight) {
                    closestWeight = w;
                    closestV = neighCity;
                } 
            }
            if (myTrip.isCityAvailable(closestV)) {
                myTrip.chooseNextCity(closestV);
                curCity = closestV;
            }

        }
        return myTrip.toString(graph);

    }

    public static String backtrack(int numNode, DGraph graph) {
        /*
         * The backtrack algorithim we were assigned to fill
         * in. Creates a Trip object and a minTrip
         * and goes through each possible path to the end and finds
         * the lowest cost and prints out that path
         * and the cost associated. Returns the cost and
         * order visited from the trip
         * 
         * @param, numNode, number of nodes in the graph
         * 
         * @param, DGraph graph, the current graph with all
         * the nodes to traverse
         * 
         * @return, String, trip cost string to be printed
         */
        Trip myTrip = new Trip(numNode);
        Trip minTrip = new Trip(numNode);
        int curCity = 1;
        myTrip.chooseNextCity(curCity);
        backtrackHelper(graph, myTrip, minTrip);
        return minTrip.toString(graph);

    }

    public static void backtrackHelper(DGraph graph, Trip curTrip, Trip minTrip) {
        /*
         * Helper does the recursion for backtracking.
         * Goes through each possible branch and finds the
         * branch with the lowest cost.
         * 
         * @param, DGraph graph, current graoh with all of
         * the edge pairs to search through
         * 
         * @param, Trip curTrip, the current trip we are going on to find
         * the end.
         * 
         * @param, Trip minTrip, the trip with the least costly path
         * to all the cities in the graph.
         */
        List<Integer> cL = curTrip.citiesLeft();
        if (cL.size() == 0 
                && curTrip.tripCost(graph) < minTrip.tripCost(graph)) {
            minTrip.copyOtherIntoSelf(curTrip);
            return;
        }
        else if (curTrip.tripCost(graph) < minTrip.tripCost(graph)) {
            for (int i = 0; i < cL.size(); i++) {
                curTrip.chooseNextCity(cL.get(i));
                backtrackHelper(graph, curTrip, minTrip);
                curTrip.unchooseLastCity();
            }
        }
    }

    public static String mine(int numNode, DGraph graph) {
        /*
         * The heuristic algorithim we were assigned to fill
         * in. Creates a Trip object and goes city by city to find
         * the assumed best possible path. Then the path and cost is
         * printed out at the end of the loop.
         * 
         * @param, numNode, number of nodes in the graph
         * 
         * @param, DGraph graph, the current graph with all
         * the nodes to traverse
         * 
         * @return, String, trip cost string to be printed
         */
        Trip myTrip = new Trip(numNode);
        int curCity = 1;
        myTrip.chooseNextCity(curCity);

        for (int k = 2; k <= numNode; k++) {

            List<Integer> neigh = graph.getNeighbors(curCity);

            double closestWeight = Double.MAX_VALUE;
            int closestV = 0;

            for (int neighCity : neigh) {
                if (myTrip.isCityAvailable(neighCity)) {
                    double w = graph.getWeight(curCity, neighCity);
                    if (w < closestWeight) {
                        closestWeight = w;
                        closestV = neighCity;
                    }
                }
            }
            if (myTrip.isCityAvailable(closestV)) {
                myTrip.chooseNextCity(closestV);
                curCity = closestV;
            }

        }
        return myTrip.toString(graph);

    }

}
