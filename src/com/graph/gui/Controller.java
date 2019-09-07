package com.graph.gui;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * This abstract class represents a {@link Scene} that is generated
 * using a FXML file.
 */
public abstract class Controller extends Scene {

	private String title;

	Controller(String fxmlPath, String title) throws IOException {
		super(FXMLLoader.load(MainMenu.class.getResource(fxmlPath)));
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	static void changeScene(Event e, Controller scene){
		Stage s = (Stage)((Control)e.getSource()).getScene().getWindow();

		s.hide();
		s.setScene(scene);
		s.sizeToScene();
		s.setTitle(scene.getTitle());
		s.show();
	}
}
