package com.graph.ui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.io.IOException;

public class ValueDialog extends BorderPane {

	@FXML Button confirmBttn;
	@FXML TextField valueField;
	@FXML Label label;

	private MainMenu mainMenu;
	private StringProperty mode = new SimpleStringProperty("");

	private static final String LABEL_DEFAULT = "Enter a starting value";
	private static final String LABEL_ERROR = "Please enter an integer";

	public ValueDialog() {
		//Load scene
		FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("/ValueDialog.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void initialize(){
		//Set confirm action
		valueField.setOnAction(this::onConfirm);
	}
	
	/**
	 * Set the prompt label to it's default valuew
	 */
	void reset(){
		label.setTextFill(Color.WHITE);
		label.setText(LABEL_DEFAULT);
	}

	@FXML
	private void onConfirm(ActionEvent e){
		//Store input
		String input = valueField.getText();
		try {
			//Parse input
			int i = Integer.parseInt(input);
			//Close window
			this.getScene().getWindow().hide();
			
			//Check the search mode and select perform search
			if(mode.get().equals(MainMenu.DFS_LABEL)){
				mainMenu.dfsGraph(i);
			}else if(mode.get().equals(MainMenu.BFS_LABEL)){
				mainMenu.bfsGraph(i);
			}
		} catch (NumberFormatException | NullPointerException e1) {
			//Update label with error label
			label.setText(LABEL_ERROR);
			label.setTextFill(Color.CRIMSON);
		}
	}
	
	/**
	 * Retrieves this {@link ValueDialog}'s search mode property.
	 * @return the search mode property of this controller
	 */
	StringProperty searchModeProperty(){
		return mode;
	}

	void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}
}
