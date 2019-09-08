package com.graph;

import java.util.List;

public interface Graph<T,V> {
	
	/**
	 * Performs a Depth-First search of this graph for the specified
	 * vector and returns it's value.
	 * @param vector the current vector to visit
	 * @param targetVectorID the vector to search for
	 * @return the {@link GraphVector} being searched for, null if it is not found
	 */
    GraphVector<T,V> depthSearch(GraphVector vector, T targetVectorID);
    GraphVector<T,V> breadthSearch(T nodeID);
    
    //By adding this, we're saying the implementing class MUST override toString()
    String toString();
	
	/**
	 * Adds a vector with no edges to this Graph.
	 * @param nodeID a unique identifier for the node
	 * @param value the node's stored value
	 */
	void addVector(T nodeID, V value);
	
	/**
	 * Returns an array of all {@link GraphVector} objects contained
	 * in this {@link Graph}
	 * @return an array of this graph's vectors
	 */
	List<? extends GraphVector<T, V>> getVectors();
    
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
	
		/**
		 * Adds a node connection from this {@link GraphVector} to another
		 * {@code GraphVector}
		 *
		 * @param vector the node to be connected
		 * @see Graph.GraphVector#removeEdge(GraphVector)
		 */
		public abstract void addEdge(GraphVector vector);
	
		/**
		 * Removes a node connection from this {@link GraphVector} to
		 * another {@code GraphVector}
		 *
		 * @param vector the vector to disconnect this vector from
		 * @return true if the target edge is removed, false otherwise
		 */
        public abstract boolean removeEdge(GraphVector vector);
        
        public abstract List<? extends GraphVector> getEdges();
        
        public T getNodeID() {
            return nodeID;
        }
        
        public V getValue() {
            return value;
        }
        
        public void setValue(V value) {
            this.value = value;
        }
        
    }
}
