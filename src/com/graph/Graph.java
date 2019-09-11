package com.graph;

import java.util.List;

public interface Graph<T,V> {
	
	/**
	 * Performs a Depth-First search of this graph for the specified
	 * vector and returns it's value.
	 * @param vectorID the vector to search for
	 * @return the {@link GraphVertex} being searched for, null if it is not found
	 */
    List<? extends GraphVertex> depthSearch(T vectorID);
    List<? extends GraphVertex> breadthSearch(T nodeID);
    
    //By adding this, we're saying the implementing class MUST override toString()
    String toString();
	
	/**
	 * Adds a vector with no edges to this Graph.
	 * @param nodeID a unique identifier for the node
	 * @param value the node's stored value
	 */
	void addVertex(T nodeID, V value);
	
	void addVertex(GraphVertex v);
	
	/**
	 * Returns an array of all {@link GraphVertex} objects contained
	 * in this {@link Graph}
	 * @return an array of this graph's vectors
	 */
	List<? extends GraphVertex<T, V>> getVertices();

	boolean contains(T nodeID);

	/**
	 * This abstract class represents key-value pair within a {@link Graph}
	 * @param <T> the type of the vector identifier
	 * @param <V> the type of the value stored in the vector
	 */
    abstract class GraphVertex<T, V>{
    
		protected T vectorID;
        protected V value;
        
        GraphVertex(T vectorID, V value){
            this.vectorID = vectorID;
            this.value = value;
        }
        
        GraphVertex(){
            this(null, null);
        }
	
		/**
		 * Adds a node connection from this {@link GraphVertex} to another
		 * {@code GraphVertex}
		 *
		 * @param vector the node to be connected
		 * @see GraphVertex#removeEdge(GraphVertex)
		 */
		public abstract void addEdge(GraphVertex vector);
	
		/**
		 * Removes a node connection from this {@link GraphVertex} to
		 * another {@code GraphVertex}
		 *
		 * @param vector the vector to disconnect this vector from
		 * @return true if the target edge is removed, false otherwise
		 */
        public abstract boolean removeEdge(GraphVertex vector);
		
		/**
		 * Returns all of this vector's edges as a {@link List} object.
		 * @return a {@link List} containing all of this vector's edges
		 */
		public abstract List<? extends GraphVertex> getEdges();
        
        public T getVectorID() {
            return vectorID;
        }
        
        public V getValue() {
            return value;
        }
        
        public void setValue(V value) {
            this.value = value;
        }
		
	}
}
