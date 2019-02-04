package application;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;


public class Handler {
	 WebView webView = new WebView();
	 WebEngine engine = webView.getEngine();
	 LoadFiles loadMod = new LoadFiles();
	 
	//this @FXML thing lets java know that. 
	//we want to set this var to the  value of the FXML node that has the 
	//Fx:id value of its name in this case vBox
	// and lets it see private vars
	 
	 
	 
	@FXML
	public static VBox vBox;
	
	//@FXML
//	public static Menu mainMenu;
	

	
	
	@FXML
	private void handleOpenMod() {
		 
	    DirectoryChooser directoryChooser = new DirectoryChooser();
	    directoryChooser.setTitle("Open Resource File");
	    File selectedFile = directoryChooser.showDialog(Main.STAGE);
		loadMod.loadMod(selectedFile);
		
		  
		  
		  
		
		
	   
	}
	
	
	@FXML
	private void initialize() {
		 vBox.getChildren().add(webView);
		 System.out.println("hi");
	}
	
	
	
	
	
}
