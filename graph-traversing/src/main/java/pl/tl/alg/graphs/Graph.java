package pl.tl.alg.graphs;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Graph {

    private final List<List<Integer>> adjacencyList;
    private final Map<Integer, Vertex> vertices;

    public Graph(List<List<Integer>> adjacencyList) {
        this.adjacencyList = ImmutableList.copyOf(adjacencyList);
        this.vertices = Maps.newHashMap();
        for(int i=0;i<adjacencyList.size();i++){
             vertices.put(i, new Vertex(this, i));
        }
    }

    public Vertex getVertex(int id) {
        return vertices.get(id);
    }


    public Map<Integer, Vertex> getVertices() {
        return ImmutableMap.copyOf(vertices);
    }

    public List<List<Integer>> getAdjacencyList() {
        return adjacencyList;
    }

    public static class Vertex{

        private final int id;
        private final Graph graph;

        private Status status;
        private int distanceToSource;
        private int parentId;

        public Vertex(Graph graph, int id) {
            this.graph = graph;
            this.id = id;
            this.status = Status.UNVISITED;
            this.distanceToSource = -1;
        }

        public int getId() {
            return id;
        }

        public List<Integer> getAdjacentVerticesId(){
            return graph.getAdjacencyList().get(id);
        }

        public List<Vertex> getAdjacentVertices(){
            LinkedList<Vertex> adjacentVertices = Lists.newLinkedList();
            for(int adjacentVertexId : graph.getAdjacencyList().get(id)){
                adjacentVertices.add(graph.getVertex(adjacentVertexId));
            }
            return adjacentVertices;
        }

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public int getDistanceToSource() {
            return distanceToSource;
        }

        public void setDistanceToSource(int distanceToSource) {
            this.distanceToSource = distanceToSource;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }
    }
}
