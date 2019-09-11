package com.graph;

import com.graph.ui.FileDialogController;
import com.graph.ui.ValueDialog;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
		fileDialogController = new FileDialogController();
		fileDialogController.setMainMenu(this);

		valueDialogController = new ValueDialog();
		valueDialogController.setMainMenu(this);


		fileDialog = new Stage();
		fileDialog.setScene(new Scene(fileDialogController));

		fileDialog.initModality(Modality.APPLICATION_MODAL);
		fileDialog.setResizable(false);
		fileDialog.setTitle("Load Graph from file...");

		valueDialog = new Stage();
		valueDialog.setScene(new Scene(valueDialogController));
		valueDialog.initModality(Modality.APPLICATION_MODAL);
		valueDialog.setResizable(false);
		valueDialog.setTitle("");

		valueDialog.setOnShown(l -> valueDialogController.reset());

		valueDialogController.searchModeProperty().bind(valueDialog.titleProperty());
	}

	public void initIcon(Stage primaryStage){
		valueDialog.getIcons().addAll(primaryStage.getIcons());
		fileDialog.getIcons().addAll(primaryStage.getIcons());
	}

	@FXML private void onLoadGraphClicked(ActionEvent e){
		if(fileDialog.getOwner() == null){
			fileDialog.initOwner(this.getScene().getWindow());
		}
		fileDialog.show();
	}

	@FXML private void onDFSClicked(ActionEvent e){
		if(valueDialog.getOwner() == null){
			valueDialog.initOwner(this.getScene().getWindow());
		}

		//Call searchDFS method
		if(graph != null){
			valueDialog.setTitle(DFS_LABEL);
			valueDialog.show();
		}

	}

	public void dfsGraph(int start){
		if(graph != null){
			List<ListVertex> dfs = (List<ListVertex>) graph.depthSearch(start);
			StringBuilder sb = new StringBuilder().append(dfs.get(0).getVectorID());
			for (int i = 1; i < dfs.size(); i++) {
				sb.append(" ,").append(dfs.get(i).getVectorID());
			}

			textDisplay.setText(sb.toString());
		}
	}

	@FXML private void onBFSClicked(ActionEvent actionEvent) {
		if(valueDialog.getOwner() == null){
			valueDialog.initOwner(this.getScene().getWindow());
		}

		if(graph != null){
			valueDialog.setTitle(BFS_LABEL);
			valueDialog.show();
		}
	}

	public void bfsGraph(int start) {
		if(graph != null){
			List<ListVertex> bfs = (List<ListVertex>) graph.breadthSearch(start);
			StringBuilder sb = new StringBuilder().append(bfs.get(0).getVectorID());
			for (int i = 1; i < bfs.size(); i++) {
				sb.append(" ,").append(bfs.get(i).getVectorID());
			}

			textDisplay.setText(sb.toString());
		}
	}

	public ListGraph<Integer> getGraph(){
		return this.graph;
	}

	public void setGraph(ListGraph<Integer> graph){
		this.graph = graph;
	}

	public FileDialogController getFileDialogController() {
		return fileDialogController;
	}


}
