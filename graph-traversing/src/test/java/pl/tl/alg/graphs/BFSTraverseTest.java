package pl.tl.alg.graphs;

import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.fest.assertions.api.Assertions.assertThat;

public class BFSTraverseTest {


    private Graph graph;
    private Graph.Vertex rootVertex;

    @Before
    public void setUp(){
        graph = GraphLoader.loadDefaultGraph();
        rootVertex = graph.getVertex(1);
    }

    @Test
    public void givenDefaultGraphAndChosenRoot_whenBFS_thenVerticesVisitedInCorrectOrder(){
        BFSTraverse.BFSTraverseResult result = new BFSTraverse(graph).traverse(rootVertex);

        System.out.println(result);
        assertThat(result.getVisitingOrder()).containsExactly(1,0,5,4,2,6,3,7);
    }

    @Test
    public void givenDefaultGraphAndChosenRoot_whenBFS_thenVerticesFullyProcessedInCorrectOrder(){
        BFSTraverse.BFSTraverseResult result = new BFSTraverse(graph).traverse(rootVertex);;

        System.out.println(result);
        assertThat(result.getFullProcessingOrder()).containsExactly(1,0,5,4,2,6,3,7);
    }

    @Test
    public void givenDefaultGraphAndChosenRoot_whenBFS_thenDistancesCorrectlyCalculated(){
        BFSTraverse.BFSTraverseResult traverseResult = new BFSTraverse(graph).traverse(rootVertex);

        Map<Integer, Set<Integer>> distancesMap = createDistancesMap(traverseResult.getGraph());
        assertThat(distancesMap.get(0)).containsExactly(1);
        assertThat(distancesMap.get(1)).containsExactly(0,5);
        assertThat(distancesMap.get(2)).containsExactly(2,4,6);
        assertThat(distancesMap.get(3)).containsExactly(3,7);
    }

    @Test
    public void givenDefaultGraphAndChosenRoot_whenBFS_thenCorrectShortestPathCalculatedForAGivenVertex(){
        BFSTraverse.BFSTraverseResult traverseResult = new BFSTraverse(graph).traverse(rootVertex);;

        assertThat(traverseResult.getShortestPath(7)).containsExactly(6,5,1);
    }



    private Map<Integer, Set<Integer>> createDistancesMap(Graph graph) {
        Map<Integer, Set<Integer>> distancesMap = Maps.newHashMap();
        for(Graph.Vertex v: graph.getVertices().values()){
            addToDistancesMap(distancesMap, v, v.getDistanceToSource());
        }
        return distancesMap;
    }

    private void addToDistancesMap(Map<Integer, Set<Integer>> distancesMap, Graph.Vertex v, int newDistance) {
        if(!distancesMap.containsKey(newDistance)){
            distancesMap.put(newDistance, new HashSet<Integer>());
        }
        distancesMap.get(newDistance).add(v.getId());
    }


}
