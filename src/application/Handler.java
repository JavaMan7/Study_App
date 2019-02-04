package application;



import java.io.File;
import java.net.URL;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;


public class Handler {
	
	 
	//this @FXML thing lets java know that. 
	//we want to set this var to the  value of the FXML node that has the 
	//Fx:id value of its name in this case vBox
	// and lets it see private vars
	 
	 
	 
	@FXML
	public  VBox vBox;
	
	@FXML
	public  Menu mainMenu;
	 WebView webView = new WebView();
	 WebEngine engine = webView.getEngine();
	 LoadFiles loadMod = new LoadFiles();

	
	
	@FXML
	private void handleOpenMod() {
		  
		vBox.getChildren().add(webView);
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Open Resource File");
	    File selectedFile = fileChooser.showOpenDialog(Main.STAGE);
	    String path = "C:\\Users\\Jordan\\Documents\\test\\ch1\\q1.html";//selectedFile.getAbsolutePath();
	    String javaPath = path.replace("\\", "/");
	    File f = new File(path);
	    URL url = this.getClass().getResource(path);
	    
	    //engine.load(selectedFile.toURI().toString());
	    engine.load(f.toURI().toString());
	    
	    
	  
		  
		  
		
		
	   
	}
	@FXML
	private void reload() {
		  
		engine.reload();
	   
	}
	@FXML
	private void initialize() {
		 
		 System.out.println("hi");
		 
		
	}
	
	
	
	
	
}
