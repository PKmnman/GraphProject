package com.graph.ui;

import com.graph.Main;
import com.graph.MainMenu;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class FileDialogController extends BorderPane {

	public Button BrowseBttn, ConfirmBttn;
	@FXML
	private TextField filePathField;
	@FXML
	private MainMenu mainMenu;
	private FileChooser fileChooser;
	private File file;

	private Stage dialog;

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
				mainMenu.notifLabel.setText(file.getName());
				this.getScene().getWindow().hide();
			}
			//Change main menu label accordingly
		}
	}

	public void setMainMenu(MainMenu mainMenu) {
		this.mainMenu = mainMenu;
	}
}
