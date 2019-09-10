package com.graph;

import java.util.*;

public class ListGraph<V> implements Graph<Integer, V> {


    private ArrayList<ListVertex> vertices;

    public ListGraph() {
        this(5);
    }

    public ListGraph(int vertices) {
        //Initialize the arraylist used to store the vertices
        this.vertices = new ArrayList<>(vertices);
        for (int i = 0; i < vertices; i++) {
            //Add default vertices
            addVertex(i + 1, null);
        }
        
    }

    @Override
    public List<? extends GraphVertex> depthSearch(Integer vertexID) {
        //Check that starting vertex exist
        int startIndex = indexOfID(vertexID);
        
        if(startIndex >= 0){
            //Get start vertex
            ListVertex start = vertices.get(startIndex);
            
            //Create a list to track the visited nodes
            List<ListVertex> visited = new LinkedList<>();
            
            //Enter recursive part
            DFS(start, visited);
            
            return visited;
        }
        
        return null;
    }
    
    private void DFS(ListVertex v, List<ListVertex> visited){
        
        visited.add(v);
    
        //For each vertex w adjacent to v
        for(ListVertex w : (List<ListVertex>)(v.getEdges())){
            //Check if we've visited
            if(!visited.contains(w)){
                //Visit
                //Then dfs(w)
                DFS(w, visited);
            }
        }
    }
    
    /**
     * Finds the index of the vertex corresponding to the specified
     * vertexID within this graph's adjacency list.
     * @param vertexID the id of the vertex to search for, -1 if no matching vertex is found
     * @return an index
     */
    private int indexOfID(int vertexID){
        for (int i = 0; i < vertices.size(); i++) {
            if(vertices.get(i).getVectorID().equals(vertexID)){
                return i;
            }
        }
        
        return -1;
    }

    @Override
    public List<? extends GraphVertex> breadthSearch(Integer nodeID) {
        //Check if node even exist
        if (this.contains(nodeID)) {
            //This is the starting vertex
            GraphVertex vertex = vertices.get(vertices.indexOf(new ListVertex<V>(nodeID, null)));
            
            //The tracking array
            boolean[] visitedVertices = new boolean[this.vertices.size()];
            Arrays.fill(visitedVertices, false);
            LinkedList<ListVertex> result = new LinkedList<>();
            
            //The processing queue
            Queue<GraphVertex> visited = new ArrayDeque<>();
            
            visited.add(vertex);
            
            while (!visited.isEmpty()) {
                //Pop head of queue into v
                ListVertex v = (ListVertex)visited.poll();
                
                //For each vertex w adjacent to v
                for (int i = 0; i <vertex.getEdges().size(); i++) {
                    //Init w to edge i
                    ListVertex w = (ListVertex)vertex.getEdges().get(i);
                    
                    //Check if it has not been visited
                    if(!visitedVertices[vertices.indexOf(w)]){
                        //Visit
                        visitedVertices[vertices.indexOf(w)] = true;
                        result.add(w);
                        //Queue
                        visited.add(w);
                    }
                }
            }
            
            return result;
        }

        return null;
    }

    /**
     * @see com.graph.Graph#addVertex
     **/
    @Override
    public void addVertex(Integer vertexID, V value) {
        ListVertex<V> vertex = new ListVertex<>(vertexID, value);

        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
    }
    
    @Override
    public void addVertex(GraphVertex v) {
        ListVertex vertex = (ListVertex)v;
    
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
        }
        
    }
    
    @Override
    public List<? extends GraphVertex<Integer, V>> getVertices() {
        return (List)this.vertices;
    }

    @Override
    public boolean contains(Integer nodeID) {
        return indexOfID(nodeID) != -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Graph{\n");
        for (int i = 0; i < vertices.size(); i++) {
            sb.append(vertices.get(i).toString()).append('\n');
        }
        sb.append('}');
        return sb.toString();
    }

}
