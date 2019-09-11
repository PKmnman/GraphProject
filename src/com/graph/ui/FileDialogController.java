package com.graph.ui;

import com.graph.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class FileDialogController extends BorderPane {

	@FXML private Button BrowseBttn, ConfirmBttn;
	@FXML private TextField filePathField;

	private MainMenu mainMenu;
	private FileChooser fileChooser;
	private File file;

	public FileDialogController(){
		FXMLLoader loader = new FXMLLoader(MainMenu.class.getResource("/GraphFileDialog.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initializes the controller after the root scene has been loaded
	 */
	public void initialize(){
		fileChooser = new FileChooser();
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
		fileChooser.getExtensionFilters().add(filter);
		fileChooser.setInitialDirectory(new File("./"));
	}

	@FXML
	private void onClickBrowse(MouseEvent event){
		file = fileChooser.showOpenDialog(BrowseBttn.getScene().getWindow());
		if(file != null && file.canRead()){
			fileChooser.setInitialDirectory(file.getParentFile());
			filePathField.setText(file.getAbsolutePath());
		}
	}

	@FXML
	private void OnConfirm() {
		if(file != null && file.canRead()){
			mainMenu.setGraph(Main.loadGraph(file.getPath()));
			if(mainMenu.getGraph() != null){
				//Change currentgraphlabel to file name
				mainMenu.currentGraphLabel.setText(file.getName());
				this.getScene().getWindow().hide();
			}
		}
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}
}
