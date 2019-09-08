package com.graph;

import com.graph.Graph.GraphVector;

import java.util.ArrayList;
import java.util.List;

class ListVector extends Graph.GraphVector<Integer, Integer>{
    
    private ArrayList<ListVector> edges;
    
    public ListVector(Integer nodeID, Integer value) {
        super(nodeID, value);
    }
    
    public ListVector() {
        super();
    }
    
    @Override
    public void addEdge(GraphVector vector) {
    
    }
    
    @Override
    public boolean removeEdge(GraphVector vector) {
        //If
        
        return false;
    }
    
    @Override
    public List<ListVector> getEdges() {
        return edges;
    }
    
}
