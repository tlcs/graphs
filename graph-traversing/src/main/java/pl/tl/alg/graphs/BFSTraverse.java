package pl.tl.alg.graphs;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

public class BFSTraverse {

    private final Queue<Graph.Vertex> queue = new LinkedList<Graph.Vertex>();
    private final Graph graph;
    private BFSTraverseResult result;

    public BFSTraverse(Graph graph){
        this.graph = graph;
    }

    public BFSTraverseResult traverse(int vertexId) {
        return traverse(graph.getVertex(vertexId));
    }

    public BFSTraverseResult traverse(Graph.Vertex source) {
        this.result=  new BFSTraverseResult(graph);
        for(Graph.Vertex vertex : graph.getVertices().values()){
            vertex.setDistanceToSource(-1);
            vertex.setStatus(Status.UNVISITED);
            vertex.setParentId(-1);
        }

        source.setDistanceToSource(0);
        source.setParentId(-1);
        markAsVisited(source);

        queue.add(source);

        while(!queue.isEmpty()){
            Graph.Vertex currentVertex = queue.remove();
            for(Graph.Vertex v:currentVertex.getAdjacentVertices()){
                if(v.getStatus().equals(Status.UNVISITED)){
                    markAsVisited(v);
                    v.setParentId(currentVertex.getId());
                    int newDistance = currentVertex.getDistanceToSource() + 1;
                    v.setDistanceToSource(newDistance);
                    queue.add(v);
                }
            }
            markAsProcessed(currentVertex);
        }
        return result;
    }

    public Set<Set<Integer>> findConnectedComponents(){
        Set<Set<Integer>> connectedComponents = Sets.newHashSet();
        Set<Integer> unvisitedVertices = new HashSet<Integer>(graph.getVertices().keySet());
        while(!unvisitedVertices.isEmpty()){
            int unvisitedVertex = unvisitedVertices.iterator().next();
            BFSTraverseResult traverseResult = traverse(unvisitedVertex);
            HashSet<Integer> newlyVisitedVertices = new HashSet<Integer>(traverseResult.getFullProcessingOrder());
            connectedComponents.add(newlyVisitedVertices);
            unvisitedVertices.removeAll(newlyVisitedVertices);
        }
        return connectedComponents;
    }


    private void markAsProcessed(Graph.Vertex vertex) {
        result.getFullProcessingOrder().add(vertex.getId());
        vertex.setStatus(Status.PROCESSED);
    }

    private void markAsVisited(Graph.Vertex vertex) {
        result.getVisitingOrder().add(vertex.getId());
        vertex.setStatus(Status.VISITED);
    }

    public static class BFSTraverseResult {

        private final List<Integer> visitingOrder = Lists.newLinkedList();
        private final List<Integer> fullProcessingOrder = Lists.newLinkedList();
        private final Graph graph;

        public BFSTraverseResult(Graph graph) {
            this.graph = graph;
        }

        public List<Integer> getVisitingOrder() {
            return visitingOrder;
        }

        public List<Integer> getFullProcessingOrder() {
            return fullProcessingOrder;
        }


        public Graph getGraph() {
            return graph;
        }

        public List<Integer> getShortestPath(int vertexId){
            List<Integer> shortestPath = Lists.newLinkedList();
            Graph.Vertex currVertex = graph.getVertex(vertexId);;
            while(currVertex.getParentId()!=-1){
                int parentId = currVertex.getParentId();
                shortestPath.add(parentId);
                currVertex = graph.getVertex(parentId);
            }
            return shortestPath;
        }
    }

}
