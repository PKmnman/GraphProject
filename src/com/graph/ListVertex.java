package com.graph;

import com.graph.Graph.GraphVertex;

import java.util.ArrayList;
import java.util.List;

class ListVertex<T, V> extends GraphVertex<T, V> {
    
    private ArrayList<ListVertex> edges = new ArrayList<>(3);
    
    public ListVertex(T nodeID, V value) {
        super(nodeID, value);
    }
    
    public ListVertex() {
        super();
    }
    
    @Override
    public void addEdge(GraphVertex vertex) {
        if(vertex instanceof ListVertex){
            edges.add((ListVertex)vertex);
        }
    }
    
    @Override
    public boolean removeEdge(GraphVertex vertex) {
        //If
        
        return false;
    }
    
    @Override
    public List<ListVertex> getEdges() {
        return edges;
    }
    
}
