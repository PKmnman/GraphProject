package com.graph;

public interface Graph<T,V> {
    GraphVector<T,V> depthSearch(T nodeID);
    GraphVector<T,V> breadthSearch(T nodeID);
    
    //By adding this, we're saying the implementing class MUST override toString()
    String toString();
    
    void addVector(GraphVector<T,V> vector);
    
    GraphVector<T,V>[] getVectorArray();
    
    abstract class GraphVector<T, V>{
        
        private T nodeID;
        private V value;
        
        public GraphVector(T nodeID, V value){
            this.nodeID = nodeID;
            this.value = value;
        }
        
        public GraphVector(){
            this(null, null);
        }
        
        public abstract void addEdge(GraphVector vector);
        public abstract void removeEdge(GraphVector vector);
        
        public abstract GraphVector[] getEdgeArray();
        
        public T getNodeID() {
            return nodeID;
        }
        
        public void setNodeID(T nodeID) {
            this.nodeID = nodeID;
        }
        
        public V getValue() {
            return value;
        }
        
        public void setValue(V value) {
            this.value = value;
        }
        
    }
}
