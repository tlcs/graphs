package pl.tl.alg.graphs;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.fest.assertions.api.Assertions.assertThat;

public class GraphLoaderTest {

    @Test
    public void givenDefaultGraph_whenLoaded_thenAdjacencyListsIsSymmetric() {
        Graph graph = GraphLoader.loadDefaultGraph();

        List<List<Integer>> adjacencyList = graph.getAdjacencyList();

        assertListSymmetric(adjacencyList);
    }

    @Test
    public void givenDefaultGraph_whenLoaded_thenAdjacencyIsSortedAsc() {
        Graph graph = GraphLoader.loadDefaultGraph();

        List<List<Integer>> adjacencyList = graph.getAdjacencyList();

        for (List<Integer> adjacentVertices : adjacencyList) {
            int prevVertextId = -1;
            for (int vertexId : adjacentVertices) {
                assertThat(vertexId).isGreaterThanOrEqualTo(prevVertextId);
                prevVertextId = vertexId;
            }
        }
    }

    @Test
    public void givenDefaultGraph_whenLoaded_thenAllVerticesFromAdjacencyListsAreListed() {
        Graph graph = GraphLoader.loadDefaultGraph();
        List<List<Integer>> adjacencyList = graph.getAdjacencyList();

        Map<Integer, Graph.Vertex> vertices = graph.getVertices();

        for (int i = 0; i < adjacencyList.size(); i++) {
            assertThat(vertices).containsKey(i);
        }
        assertThat(vertices).hasSize(adjacencyList.size());
    }

    private static void assertListSymmetric(List<List<Integer>> adjacencyList) {
        for (int currVertexId = 0; currVertexId < adjacencyList.size(); currVertexId++) {
            List<Integer> adjacentVertices = adjacencyList.get(currVertexId);
            for (int adjacentVertexId : adjacentVertices) {
                assertCorrespondingAdjacencyListContainsVertex(adjacencyList, currVertexId, adjacentVertexId);
            }
        }
    }

    private static void assertCorrespondingAdjacencyListContainsVertex(List<List<Integer>> adjacencyList, int currVertexId, int adjacentVertexId) {
        boolean foundInCorrespondingAdjList = false;
        for (int vertexIdOnCorrespondingAdjList : adjacencyList.get(adjacentVertexId)) {
            if (vertexIdOnCorrespondingAdjList == currVertexId) {
                foundInCorrespondingAdjList = true;
            }
        }
        assertThat(foundInCorrespondingAdjList).overridingErrorMessage("Adjacency lists are not symmetric for vertices: " + currVertexId + " , " + adjacentVertexId).isTrue();
    }

}
