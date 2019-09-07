package com.graph;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/*************************************************************************************
 *  Program:     MainMenu.java
 *  Date:        September 05, 2019
 *  Author: Gary Reeves; *  Assignment:  Graph Project
 *************************************************************************************/
public class MainMenu {

	@FXML
	private Button loadGraphButton;
	@FXML
	private Button dFSButton;
	@FXML
	private TextField dFSTextField;
	
	@FXML
	private void onKeyReleased(KeyEvent e){
		if(e.getCode() == KeyCode.ENTER){
			dFSButton.fire();
		}

	}
	
	@FXML
	private void onDFSClicked(ActionEvent e){
		//Call searchDFS method
		System.out.println("DFS Button clicked!!!");
	}
	
	@FXML
	private void onLoadGraphClicked(ActionEvent e){
		Main.changeScene(e, Main.browseDialog, Main.LOADER_TITLE);
	}
	
}
