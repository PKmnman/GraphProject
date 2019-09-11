package com.graph.ui;

import com.graph.ListGraph;
import com.graph.ListVertex;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class MainMenu extends VBox {

	@FXML public Button bFSButton, loadGraphButton, dFSButton;
	@FXML public TextArea textDisplay;
	@FXML public Label currentGraphLabel;

	public static final String BFS_LABEL = "Breadth-First Search";
	public static final String DFS_LABEL = "Depth-First Search";

	private FileDialogController fileDialogController;
	private ValueDialog valueDialogController;

	private Stage fileDialog;
	private Stage valueDialog;

	private ListGraph<Integer> graph;

	public MainMenu(){
		FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("/MainMenu.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize() {
		//Init the "load file" dialog
		fileDialogController = new FileDialogController();
		fileDialogController.setMainMenu(this);

		//Init the "input value" dialog
		valueDialogController = new ValueDialog();
		valueDialogController.setMainMenu(this);

		//Init the "load file" dialog window
		fileDialog = new Stage();
		fileDialog.setScene(new Scene(fileDialogController));
		fileDialog.initModality(Modality.APPLICATION_MODAL);
		fileDialog.setResizable(false);
		fileDialog.setTitle("Load Graph from file...");

		//Init the "enter value" dialog window
		valueDialog = new Stage();
		valueDialog.setScene(new Scene(valueDialogController));
		valueDialog.initModality(Modality.APPLICATION_MODAL);
		valueDialog.setResizable(false);
		valueDialog.setTitle("");
		
		//Add handler to reset error text on dialog open
		valueDialog.setOnShown(l -> valueDialogController.reset());

		//Bind the search mode prop to the dialog's title prop
		valueDialogController.searchModeProperty().bind(valueDialog.titleProperty());
	}
	
	/**
	 * Loads the main application icon's from it's primary stage.
	 * @param primaryStage the primary stage passed to {@link Application#start(Stage)}
	 */
	public void initIcon(Stage primaryStage){
		valueDialog.getIcons().addAll(primaryStage.getIcons());
		fileDialog.getIcons().addAll(primaryStage.getIcons());
	}
	
	//This is the event handler for the "Load..." button
	@FXML private void onLoadGraphClicked(ActionEvent e){
		if(fileDialog.getOwner() == null){
			fileDialog.initOwner(this.getScene().getWindow());
		}
		fileDialog.show();
	}

	//This is the event handler for the depth-first search button
	@FXML private void onDFSClicked(ActionEvent e){
		//Lazily init the owner of the dialog to this MainMenu
		if(valueDialog.getOwner() == null){
			valueDialog.initOwner(this.getScene().getWindow());
		}

		//Show the dialog as long as there is a graph to search
		if(graph != null){
			valueDialog.setTitle(DFS_LABEL);
			valueDialog.show();
		}

	}
	
	/**
	 * Performs a depth-first search on the loaded graph at the
	 * specified starting vertex.
	 * @param start the starting vertex
	 */
	void dfsGraph(int start){
		if(graph != null){
			//Store result of DFS
			List<ListVertex> dfs = (List<ListVertex>) graph.depthSearch(start);
			//Format result
			StringBuilder sb = new StringBuilder().append(dfs.get(0).getVectorID());
			for (int i = 1; i < dfs.size(); i++) {
				sb.append(" ,").append(dfs.get(i).getVectorID());
			}
			//Update result text
			textDisplay.setText(sb.toString());
		}
	}

	@FXML private void onBFSClicked(ActionEvent actionEvent) {
		//Lazily init the dialog owner
		if(valueDialog.getOwner() == null){
			valueDialog.initOwner(this.getScene().getWindow());
		}

		//Show dialog if graph is loaded
		if(graph != null){
			valueDialog.setTitle(BFS_LABEL);
			valueDialog.show();
		}
	}
	
	/**
	 * Performs a breadth-first search on the loaded graph at the specified
	 * starting vertex.
	 * @param start
	 */
	void bfsGraph(int start) {
		if(graph != null){
			List<ListVertex> bfs = (List<ListVertex>) graph.breadthSearch(start);
			StringBuilder sb = new StringBuilder().append(bfs.get(0).getVectorID());
			for (int i = 1; i < bfs.size(); i++) {
				sb.append(" ,").append(bfs.get(i).getVectorID());
			}

			textDisplay.setText(sb.toString());
		}
	}

	//Getters and setters for the graph
	
	public ListGraph<Integer> getGraph(){
		return this.graph;
	}

	public void setGraph(ListGraph<Integer> graph){
		this.graph = graph;
	}
	
}
