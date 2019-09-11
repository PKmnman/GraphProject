package com.graph.ui;

import com.graph.MainMenu;
import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.OptionalInt;

public class ValueDialog extends BorderPane {

	@FXML Button confirmBttn;
	@FXML TextField valueField;
	@FXML Label label;

	private MainMenu mainMenu;
	private StringProperty mode = new SimpleStringProperty("");

	private static final String LABEL_DEFAULT = "Enter a starting value";
	private static final String LABEL_ERROR = "Please enter an integer";

	public ValueDialog() {

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
		label.setTextFill(Color.WHITE);
	}

	public void reset(){
		label.setTextFill(Color.WHITE);
		label.setText(LABEL_DEFAULT);

	}

	public void onConfirm(ActionEvent e){
		String input = valueField.getText();
		try {
			int i = Integer.parseInt(input);
			this.getScene().getWindow().hide();
			if(mode.get().equals(MainMenu.DFS_LABEL)){
				mainMenu.dfsGraph(i);
			}else if(mode.get().equals(MainMenu.BFS_LABEL)){
				mainMenu.bfsGraph(i);
			}
		} catch (NumberFormatException | NullPointerException e1) {
			label.setText(LABEL_ERROR);
			label.setTextFill(Color.CRIMSON);
		}
	}

	public StringProperty searchModeProperty(){
		return mode;
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}
}
