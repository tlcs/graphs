package pl.tl.alg.graphs;

import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class ConnectedComponentsTest {

    @Test
    public void givenDefaultGraph_whenFindingConnectedComponents_thenOneComponentFound(){
        Graph graph = GraphLoader.loadDefaultGraph();

        Set<Set<Integer>> connectedComponents = new BFSTraverse(graph).findConnectedComponents();

        assertThat(connectedComponents).hasSize(1);
        System.out.println("Connected components of default graph");
        printConnectedComponents(connectedComponents);
    }

    @Test
    public void givenGraphB_whenFindingConnectedComponents_thenTwoComponentsFound(){
        Graph graph = GraphLoader.loadGraphB();

        Set<Set<Integer>> connectedComponents = new BFSTraverse(graph).findConnectedComponents();

        assertThat(connectedComponents).hasSize(2);
        System.out.println("Connected components of graph B");
        printConnectedComponents(connectedComponents);
    }


    @Test
    public void givenModifiedGraphB_whenFindingConnectedComponents_thenOneComponentFound(){
        Graph graph = GraphLoader.loadModifiedGraphB();

        Set<Set<Integer>> connectedComponents = new BFSTraverse(graph).findConnectedComponents();

        assertThat(connectedComponents).hasSize(1);
        System.out.println("Connected components of modified graph B");
        printConnectedComponents(connectedComponents);
    }

    private void printConnectedComponents(Set<Set<Integer>> connectedComponents) {
        System.out.println(connectedComponents);
    }


}
