package com.graph.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

/*************************************************************************************
 *  Program:     MainMenu.java
 *  Date:        September 05, 2019
 *  Author: Gary Reeves; *  Assignment:  Graph Project
 *************************************************************************************/
public class MainMenu extends Controller {

	//Singleton Instance
	private static MainMenu instance = null;

	@FXML
	private Button loadGraphButton, dFSButton;
	@FXML
	private TextField dFSTextField;

	/**
	 * Constructs a {@link MainMenu} instance. Should only be called
	 * in the {@link #getInstance()} method.
	 * @throws IOException most likely a {@link java.io.FileNotFoundException}
	 */
	private MainMenu() throws IOException {
		super("/MainMenu.fxml", "Graph Program");
	}

	/**
	 * On the first call, initializes the MainMenu controller singleton
	 * before returning it. Subsequent calls return the same instance.
	 * @return the {@link MainMenu} singleton instance
	 */
	static MainMenu getInstance(){
		//Lazy Initializer
		if(instance == null){
			try{
				instance = new MainMenu();
			}catch (IOException e){
				System.err.println("Error Loading Main Menu");
			}
		}
		return instance;
	}
	
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
		changeScene(e, GraphApplication.browseDialog);
	}
	
}
