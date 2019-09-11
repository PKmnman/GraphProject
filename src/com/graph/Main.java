package com.graph;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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
public class Main {
	
	public Button BrowseBttn, ConfirmBttn, CancelBttn;
	@FXML
	private TextField filePathField;
	
	private FileChooser fileChooser;
	private File file;
	
	private static Graph graph;
	
	//Gui Menu titles
	static final String MAIN_MENU_TITLE = "Graph Program", LOADER_TITLE = "Load a graph from a text file...";
	
	static Scene browseDialog, menu;
	
	static Stage loadFromFile;
	
	//Main Method
	public static void main(String[] args) throws Exception {
		//TODO: Finish UI
		//Application.launch(Main.class);
		
		graph = loadGraph("C:\\Users\\ksrot\\Dropbox\\Java Programs\\Graph Project\\res\\sample_file.txt");
		
		if(graph != null){
			System.out.println(graph);
			System.out.println();
		}
		
		List dfs = graph.depthSearch(1);
		System.out.println("Depth-First Search");
		if(dfs == null){
			System.out.println("Null list");
		}else {
			
			for (int i = 0; i < dfs.size(); i++) {
				System.out.println(((ListVertex)dfs.get(i)).getVectorID());
			}
			System.out.println();
		}
		
		List bfs = graph.breadthSearch(1);
		System.out.println("Breadth-First Search");
		if(bfs == null){
			System.out.println("Null list");
		}else {
			System.out.print(((ListVertex)bfs.get(0)).getVectorID());
			for (int i = 1; i < bfs.size(); i++) {
				System.out.printf(" > %s",((ListVertex)bfs.get(i)).getVectorID());
			}
			System.out.println();
		}
		
		System.exit(0);
	}
	
	//Where the GUI code gets executed
	
	public void start(Stage primaryStage) throws Exception {
		
		Parent p;
		
		loadFromFile = new Stage(StageStyle.DECORATED);
		
		try {
			//Load all of the GUI's
			p = FXMLLoader.load(Main.class.getResource("/GraphFileDialog.fxml"));
			browseDialog = new Scene(p);
			
			loadFromFile.setScene(browseDialog);
			loadFromFile.sizeToScene();
			loadFromFile.resizableProperty().set(false);
			loadFromFile.initOwner(primaryStage);
			
			
			p = FXMLLoader.load(MainMenu.class.getResource("/MainMenu.fxml"));
			menu = new Scene(p);
			
		} catch (IOException e){
			System.err.println("There was a problem loading the FXML scene data");
			System.err.println(e.getMessage());
		}
		
		
		
		//Set the scene to the main menu
		primaryStage.setScene(menu);
		primaryStage.setResizable(false);
		//Set the title
		primaryStage.setTitle(MAIN_MENU_TITLE);
		//Display
		primaryStage.show();
		
	}
	
	//Graph Loading Event Handler Methods
	
	@FXML
	private void OnCancel(MouseEvent event) {
		changeScene(event, menu, MAIN_MENU_TITLE);
	}
	
	@FXML
	private void onClickBrowse(MouseEvent event){
		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
		fileChooser.getExtensionFilters().add(filter);
		
		file = fileChooser.showOpenDialog(BrowseBttn.getScene().getWindow());
		
		if(file != null){
			filePathField.setText(file.getAbsolutePath());
		}else{
			filePathField.clear();
		}
	}
	
	@FXML
	private void OnConfirm(MouseEvent event) {
		if(file == null && !filePathField.getText().equals("")){
			file = new File(filePathField.getText());
			if(!file.toPath().endsWith(".txt")){
				//Should probs display error message here
				System.err.println("Text file not selected");
				//Switch back to the main menu
				Main.changeScene(event, menu, MAIN_MENU_TITLE);
				filePathField.clear();
				//Skip further execution
				return;
			}
			
			
		}
		
		if(file != null && file.exists()){
		    graph = loadGraph(filePathField.getText());
			//Check that graph loaded
			//Change main menu label accordingly
		}
	}
	
	public static void changeScene(Event e, Scene scene, String title){
		Stage s = (Stage)((Control)e.getSource()).getScene().getWindow();
		
		s.hide();
		s.setScene(scene);
		s.sizeToScene();
		s.setTitle(title);
		s.show();
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
