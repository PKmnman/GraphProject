package com.graph;

import javafx.application.Application;
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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/*************************************************************************************
 *  Program:     Main.java
 *  Date:        September 04, 2019
 *  Author: 	 Gary Reeves
 *  Assignment:  Graph Project
 *************************************************************************************/
public class Main extends Application {
	
	public Button BrowseBttn, ConfirmBttn, CancelBttn;
	@FXML
	private TextField filePathField;
	
	private FileChooser fileChooser;
	private File file;
	
	//Gui Menu titles
	static final String MAIN_MENU_TITLE = "Graph Program", LOADER_TITLE = "Load a graph from a text file...";
	
	static Scene browseDialog, menu;
	
	//Main Method
	public static void main(String[] args) throws Exception {
		Application.launch(Main.class);
		
	}
	
	//Where the GUI code gets executed
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent p;
		
		try {
			//Load all of the GUI's
			p = FXMLLoader.load(Main.class.getResource("/GraphFileDialog.fxml"));
			browseDialog = new Scene(p);
			
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
	private void openBrowse(MouseEvent event){
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
		    loadGraph(filePathField.getText());
			//Load Graph
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
        FileReader inFile = null;
        try {
            inFile = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner scan = new Scanner(inFile);

        ListGraph graph = new ListGraph();

        while (scan.hasNextLine()) {
            ListVertex vector = new ListVertex();
            String line = scan.nextLine();
            String[] lineArr = line.split(" ");
            for (int j = 0; j < lineArr.length; j++) {
                if (j == 0) {
                    vector = new ListVertex(Integer.parseInt(lineArr[0]), Integer.parseInt(lineArr[0]));
                } else {
                    vector.addEdge(new ListVertex(Integer.parseInt(lineArr[j]), Integer.parseInt(lineArr[j])));
                }
            }
            graph.addVector(vector, vector);
        }
        return graph;
    }


	
}
