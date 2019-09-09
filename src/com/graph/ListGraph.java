package com.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListGraph<T, V> implements Graph<T,V> {
    
    
    private ArrayList<ListVertex> vertices;

    public ListGraph(){
        this(5);
    }

    public ListGraph(int vertices){
       this.vertices = new ArrayList<>(vertices);
    }
    
    public ListGraph(ListVertex ... vertices){
        this.vertices = new ArrayList<>(vertices.length);
        Collections.addAll(this.vertices, vertices);
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
    
    /**@see com.graph.Graph#addVertex **/
    @Override
    public void addVertex(T vertexID, V value) {
        ListVertex<T,V> vertex = new ListVertex<>(vertexID, value);
        
        if(!vertices.contains(vertex)){
            vertices.add(vertex);
        }
    }
    
    @Override
    public List<? extends GraphVertex<T, V>> getVectors() {
        return new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return "A List Graph";
    }
    
}
