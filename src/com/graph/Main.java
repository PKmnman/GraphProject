package com.graph;

import com.sun.javafx.tk.Toolkit;
import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/*************************************************************************************
 *  Program:     Main.java
 *  Date:        September 04, 2019
 *  Author: 	 Gary Reeves
 *  Assignment:  Graph Project
 *************************************************************************************/
public class Main extends Application {

	private MainMenu mainMenu;

	private static Graph graph;
	
	//Gui Menu titles
	static final String MAIN_MENU_TITLE = "Graph Program";
	
	//Main Method
	public static void main(String[] args) throws Exception {
		//TODO: Finish UI
		Application.launch(Main.class);

	}
	
	//Where the GUI code gets executed
	
	public void start(Stage primaryStage) throws Exception {

		mainMenu = new MainMenu();
		Scene menu = new Scene(mainMenu);

		primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/icon.png")));
		mainMenu.initIcon(primaryStage);

		//Set the scene to the main menu
		primaryStage.setScene(menu);
		primaryStage.setResizable(false);
		//Set the title
		primaryStage.setTitle(MAIN_MENU_TITLE);
		//Display
		primaryStage.show();
		
	}

	public static ListGraph loadGraph(String fileName) {
        
        try {
			FileReader inFile = new FileReader(fileName);
	
			Scanner fileScanner = new Scanner(inFile);
			
			//Read first line for graph size
			String initLine = fileScanner.nextLine();
			String[] initValues = initLine.split(" ");
			
			//Check that the size line has a value
			if(initValues.length <= 1){
				Scanner stringScanner = new Scanner(initValues[0]);
				//Init Graph
				graph = new ListGraph<Integer>(stringScanner.nextInt());
			}else{
				//There's a problem with the format of the file if this statement was reached
				return null;
			}
			
			//Loop through file
			int id = 1;
			while (fileScanner.hasNextLine()) {
				//Read and split each line
				String line = fileScanner.nextLine();
				String[] lineArr = line.split(" ");
				//Load each vertex definition
				loadVertex(id,lineArr);
				//Advance to the next line
				id++;
			}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
        //Return the fully loaded graph
        return (ListGraph)graph;
        
    }
    
    private static void loadVertex(int lineNum, String[] line){
		//Get the default vertex from the adjacency list
		ListVertex<Integer> vertex = (ListVertex<Integer>)graph.getVertices().get(lineNum - 1);
		
		//For each edge specified in the file
		for (int i = 0; i < line.length; i++) {
			try{
				//Get edge index/ID
				int w = Integer.parseInt(line[i]);
				
				//Add the edge to the vertex
				vertex.addEdge((ListVertex)graph.getVertices().get(w - 1));
				
			}catch (NumberFormatException e){
				System.err.println("Error parsing Graph file: NumberFormatException");
				return;
			}
		}
	}


	
}
