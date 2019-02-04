package application;



import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;


public class Handler {
	
	 
	//this @FXML thing lets java know that. 
	//we want to set this var to the  value of the FXML node that has the 
	//Fx:id value of its name in this case vBox
	// and lets it see private vars
	 
	 
	 
	@FXML
	public  VBox vBox;
	
	@FXML
	public  Menu mainMenu;
	

	
	
	@FXML
	private void handleOpenMod() {
		 
	    DirectoryChooser directoryChooser = new DirectoryChooser();
	    directoryChooser.setTitle("Open Resource File");
	   // File selectedFile = directoryChooser.showDialog(Main.STAGE);
		//loadMod.loadMod(selectedFile);
		
	    vBox.getChildren().add(webView);
		  
		  
		
		
	   
	}
	 WebView webView = new WebView();
	 WebEngine engine = webView.getEngine();
	 LoadFiles loadMod = new LoadFiles();
	
	@FXML
	private void initialize() {
		 
		 System.out.println("hi");
		 
		
	}
	
	
	
	
	
}
