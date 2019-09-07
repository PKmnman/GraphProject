package com.graph.gui;

import javafx.application.Application;
import javafx.stage.Stage;

public class GraphApplication extends Application {

	public static Controller browseDialog, menu;

	@Override
	public void start(Stage primaryStage) {
		//Load all of the GUI's
		browseDialog = FileLoadDialog.getInstance();
		menu = MainMenu.getInstance();


		//Set the scene to the main menu
		primaryStage.setScene(menu);
		primaryStage.setResizable(false);
		//Set the title
		primaryStage.setTitle(menu.getTitle());
		//Display
		primaryStage.show();
	}
}
