package pl.tl.alg.graphs;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GraphLoader {

    public static Graph loadDefaultGraph() {
        List<List<Integer>> adjacencyList = new ArrayList<List<Integer>>();
        adjacencyList.add(0,Arrays.asList(1,4));
        adjacencyList.add(1,Arrays.asList(0,5));
        adjacencyList.add(2,Arrays.asList(3,5,6));
        adjacencyList.add(3,Arrays.asList(2,6,7));
        adjacencyList.add(4,Arrays.asList(0));
        adjacencyList.add(5,Arrays.asList(1,2,6));
        adjacencyList.add(6,Arrays.asList(2,3,5,7));
        adjacencyList.add(7,Arrays.asList(3,6));
        return new Graph(adjacencyList);
    }

    public static Graph loadGraphB() {
        List<List<Integer>> adjacencyList = new ArrayList<List<Integer>>();
        adjacencyList.add(0, Arrays.asList(1, 2));
        adjacencyList.add(1,Arrays.asList(0,2));
        adjacencyList.add(2,Arrays.asList(0,1));
        adjacencyList.add(3,Arrays.asList(4));
        adjacencyList.add(4,Arrays.asList(3));
        return new Graph(adjacencyList);
    }

    public static Graph loadModifiedGraphB() {
        List<List<Integer>> adjacencyList = new ArrayList<List<Integer>>();
        adjacencyList.add(0, Arrays.asList(1, 2));
        adjacencyList.add(1,Arrays.asList(0,2));
        adjacencyList.add(2,Arrays.asList(0,1,3));
        adjacencyList.add(3,Arrays.asList(2,4));
        adjacencyList.add(4,Arrays.asList(3));
        return new Graph(adjacencyList);
    }

}
