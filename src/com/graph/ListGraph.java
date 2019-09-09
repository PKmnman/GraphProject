package com.graph;

import java.util.ArrayList;
import java.util.List;

public class ListGraph<T, V> implements Graph<T,V> {
    
    private ListVertex entryPoint;

    public ListGraph(){
        this.entryPoint = null;
    }

    public ListGraph(ListVertex entryPoint){
       this.entryPoint = entryPoint;
    }
    
    @Override
    public GraphVertex<T, V> depthSearch(GraphVertex vertex, T targetVectorID) {
        
        List<GraphVertex> vEdges = vertex.getEdges();
        
        for (int i = 0; i < vEdges.size(); i++) {
            if(false){
                depthSearch(vEdges.get(i), targetVectorID);
            }
        }
        
        return null;
    }
    
    @Override
    public GraphVertex<T, V> breadthSearch(T nodeID) {
        return null;
    }
    
    /**@see com.graph.Graph#addVector**/
    @Override
    public void addVector(T nodeID, V value) {
    
    }
    
    @Override
    public List<? extends GraphVertex<T, V>> getVectors() {
        return new ArrayList<>();
    }
    
   
}
