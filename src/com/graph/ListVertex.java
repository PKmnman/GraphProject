package com.graph;

import com.graph.Graph.GraphVertex;

import java.util.ArrayList;
import java.util.List;

public class ListVertex<V> extends GraphVertex<Integer, V> {
    
    private ArrayList<ListVertex> edges = new ArrayList<>(3);
    
    public ListVertex(Integer nodeID, V value) {
        super(nodeID, value);
    }
    
    @Override
    public void addEdge(GraphVertex vertex) {
        if(vertex instanceof ListVertex){
            edges.add((ListVertex)vertex);
        }
    }
    
    @Override
    public boolean removeEdge(GraphVertex vertex) {
        //Not implemented
        return false;
    }
    
    @Override
    public List<ListVertex> getEdges() {
        return edges;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ListVertex){
            return ((ListVertex) obj).vectorID.equals(this.vectorID);
        }
        return false;
    }
    
    @Override
    public Integer getVectorID() {
        return super.getVectorID();
    }
    
    @Override
    public V getValue() {
        return super.getValue();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.vectorID);
        
        for(ListVertex v : edges){
            sb.append(">").append(v.getVectorID());
        }
        
        return sb.toString();
    }
    
}
