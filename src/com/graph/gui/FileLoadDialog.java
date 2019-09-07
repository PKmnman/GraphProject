package com.graph.gui;

import com.graph.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.IOException;
import java.nio.file.Path;

public final class FileLoadDialog extends Controller {

	private static FileLoadDialog instance = null;

	private static Path currentGraphFile = null;

	@FXML
	private Button BrowseBttn, ConfirmBttn, CancelBttn;
	@FXML
	private TextField filePathField;

	private FileChooser fileBrowser;

	private FileLoadDialog() throws IOException {
		super("/GraphFileDialog.fxml", "Load graph from text file...");

		//Initialize the file browsing dialog
		fileBrowser = new FileChooser();
		FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Text Files", "*.txt");
		fileBrowser.getExtensionFilters().add(filter);
	}

	/**
	 * On the first call, initializes the FileLoadDialog controller singleton
	 * instance before returning it. All subsequent calls return the instance.
	 * <br><br>
	 * <b>This method should only be called on the JavaFX Application Thread.</b>
	 * @return the {@link FileLoadDialog} instance
	 */
	static FileLoadDialog getInstance(){
		//Lazy Initializer
		if(instance == null){
			try{
				instance = new FileLoadDialog();
			}catch (IOException e){
				System.err.println("Error Loading File Dialog");
			}
		}
		return instance;
	}

	@FXML
	private void OnCancel(MouseEvent event) {
		changeScene(event, GraphApplication.menu);
	}

	@FXML
	private void onClickBrowse(MouseEvent event){
		currentGraphFile = fileBrowser.showOpenDialog(BrowseBttn.getScene().getWindow()).toPath();

		if(!currentGraphFile.toString().equals("")){
			filePathField.setText(currentGraphFile.toString());
			ConfirmBttn.setDisable(false);
		}

	}

	@FXML
	private void OnConfirm(MouseEvent event) {

		Main.loadGraph(currentGraphFile);

	}

}
