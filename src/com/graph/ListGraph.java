package com.graph;

import java.util.ArrayList;

public class ListGraph implements Graph<Integer, Integer> {
    
    private  ArrayList<GraphVector> vectors;

    public ListGraph(){
        this.vectors = null;
    }

    public ListGraph(int size){
        this.vectors = new ArrayList<>(size);
    }
    
    @Override
    public GraphVector<Integer, Integer> depthSearch(Integer nodeID) {
        return null;
    }
    
    @Override
    public GraphVector<Integer, Integer> breadthSearch(Integer nodeID) {
        return null;
    }
    
    @Override
    public void addVector(GraphVector<Integer, Integer> vector) {
    
    }
    
    @Override
    public GraphVector<Integer, Integer>[] getVectorArray() {
        return new Graph.GraphVector[0];
    }
    
    private class ListVector extends Graph.GraphVector<Integer, Integer>
}
