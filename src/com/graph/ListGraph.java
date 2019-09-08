package com.graph;

import java.util.ArrayList;

public class ListGraph<T> {
    private  ArrayList<T> vectors;

    public ListGraph(){
        this.vectors = null;
    }

    public ListGraph(int x){
        this.vectors = new ArrayList<>(x);
    }


}
