package com.graph;

import com.graph.gui.GraphApplication;
import javafx.application.Application;

import java.io.File;
import java.nio.file.Path;

/*************************************************************************************
 *  Program:     Main.java
 *  Date:        September 04, 2019
 *  Author: 	 Gary Reeves
 *  Assignment:  Graph Project
 *************************************************************************************/
public class Main {

	//Main Method
	public static void main(String[] args) {
		Application.launch(GraphApplication.class);
	}

	public static void loadGraph(Path path){
		File graphFile = new File(path.toUri());


	}
	

	
}
