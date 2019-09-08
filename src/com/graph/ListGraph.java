package com.graph;

import java.util.ArrayList;
import java.util.List;

public class ListGraph<T, V> implements Graph<T,V> {
    
    private ListVector entryPoint;

    public ListGraph(){
        this.entryPoint = null;
    }

    public ListGraph(ListVector entryPoint){
       this.entryPoint = entryPoint;
    }
    
    @Override
    public GraphVector<T, V> depthSearch(GraphVector vector, T targetVectorID) {
        return null;
    }
    
    @Override
    public GraphVector<T, V> breadthSearch(T nodeID) {
        return null;
    }
    
    /**@see com.graph.Graph#addVector**/
    @Override
    public void addVector(T nodeID, V value) {
    
    }
    
    @Override
    public List<? extends GraphVector<T, V>> getVectors() {
        return new ArrayList<>();
    }
    
   
}
