package application;



import java.io.File;
import java.net.URL;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;


public class Handler {
	
	 
	//this @FXML thing lets java know that. 
	//we want to set this var to the  value of the FXML node that has the 
	//Fx:id value of its name in this case vBox
	// and lets it see private vars
	 
	 
	 WebView webView = new WebView();
	 WebEngine engine = webView.getEngine();
	 public VBox getvBox() {
		return vBox;
	}


	


	public MenuBar getMainMenu() {
		return mainMenu;
	}


	
	public  File[] MOD;
	public  File[] MOD2;

	LoadFiles loadMod = new LoadFiles();
	@FXML
	public  VBox vBox;
	
	@FXML
	public  MenuBar mainMenu;
	LoadFiles l = new LoadFiles();

	
	
	@FXML
	private void handleOpenMod() {
		vBox.getChildren().add(webView);
		
		
		 DirectoryChooser  fileChooser = new DirectoryChooser();
		   fileChooser.setTitle("Open Resource File");
		    File selectedFile = fileChooser.showDialog(Main.STAGE);
		   String path = "C:\\Users\\Jordan\\Documents\\test\\ch1\\q1.html";//selectedFile.getAbsolutePath();
		   String javaPath = path.replace("\\", "/");
		    
		   URL url = this.getClass().getResource(path);
		    MOD = selectedFile.listFiles();
		    //engine.load(selectedFile.toURI().toString());
		    
		
		
		
		for (int i =0 ;i< MOD.length;i++) 
      {
			
			//File f = new File(path);
			
			Menu menu = new Menu(MOD[i].getName());
			mainMenu.getMenus().add(menu);
			MOD2 =MOD[i].listFiles();
			for (int j =0 ;j< MOD2.length;j++) {
				
				MenuItem menui = new MenuItem(MOD2[j].getName());
				mainMenu.getMenus().get(1).getItems().add(menui);
			  ObservableList<MenuItem> menuItem =	mainMenu.getMenus().get(j+1).getItems();
			  
			    File item=MOD2[j];
			  
			  EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() { 
				   @Override 
				   public void handle(ActionEvent e) { 
					  
					    System.out.println("world");
					    String path = item.getAbsolutePath();
					    String javaPath = path.replace("\\", "/");
					    File f = new File(path);
					  
					    
					    //engine.load(selectedFile.toURI().toString());
					    engine.load(f.toURI().toString());
				      
				   } 
				};   
			  
			  
			  
			  menuItem.get(j).setOnAction( eventHandler  );
			  
				
				
				
				
			}
				
				
           ///
          
          
          
          
      }
	    
	    
		  
	 
		  
		
		
	   
	}

	
	@FXML
	private void initialize() {
		 
		 System.out.println("hi");
		
		 
	}
	
	
	
	
	
}
