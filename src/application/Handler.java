package application;



import java.io.File;
import java.net.URL;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
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
import netscape.javascript.JSObject;


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


	
	public  File[] MOD;//loads the chapters in to the program
	public  File[] MOD2;//gets the files in the chapters 

	LoadFiles loadMod = new LoadFiles();
	@FXML
	public  VBox vBox;
	
	@FXML
	public  MenuBar mainMenu;
	LoadFiles l = new LoadFiles();

	
	
	@FXML
	private void handleOpenMod() {
		
		
		
		 DirectoryChooser  fileChooser = new DirectoryChooser();
		   fileChooser.setTitle("Open Resource File");
		    File selectedFile = fileChooser.showDialog(Main.STAGE);
		  // String path = "C:\\Users\\Jordan\\Documents\\test\\ch1\\q1.html";//selectedFile.getAbsolutePath();
		  // String javaPath = path.replace("\\", "/");
		    
		   //URL url = this.getClass().getResource(path);
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
			  ObservableList<MenuItem> menuItem =	mainMenu.getMenus().get(j+1).getItems();//this need to be pluse one because the first item is the open item 
			  
			    File item=MOD2[j];
			  
			  EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() { 
				   @Override 
				   public void handle(ActionEvent e) { 
					  
					    //System.out.println("world");
					    String path = item.getAbsolutePath();
					   
					    File f = new File(path);
					  
					    
					    //engine.load(selectedFile.toURI().toString());
					    engine.load(f.toURI().toString());
					    EventListener listener = new EventListener() {
							@Override
						    public void handleEvent(Event ev) {
								System.out.println("hi");
								
							
								JSObject jdoc = (JSObject) engine.getDocument();//loads java script from html 
								//jdoc.call("submitF",new Object[] {1});//this should call the sumitF method 
								//boolean isRight = (boolean) jdoc.call("getIsRight");
								//engine.executeScript("submit");
								//System.out.println(isRight);
								//double getAnswer = (double) Double.parseDouble((String) jdoc.call("getAnswer"));
								//System.out.println(getAnswer);
								
								System.out.println(engine.executeScript("getUserAnswer();"));
								System.out.println(engine.executeScript("getIsRight();"));
								System.out.println(engine.executeScript("getPercenDiff();"));
								//System.out.println(engine.executeScript("getPercenDiff"));
								
						    }
						};
						//System.out.print(engine.getLoadWorker().getState() == engine.getLoadWorker().getState().SUCCEEDED );
                      
						
						 engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {//make sure the web page is load before adding listener
					           

								@Override
								public void changed(ObservableValue<? extends State> ov, State t, State t1) {
									if (t1 == Worker.State.SUCCEEDED) {
					                	Document doc = engine.getDocument();
										Element el = doc.getElementById("submitto");
										((EventTarget) el).addEventListener("click", listener, false);
										 
					                   
					                }
									
								}
					    });
						
						
						
						
						
				      
				   } 
				};   
			  
			  
			  
			  menuItem.get(j).setOnAction( eventHandler  );
			  
				
				
				
				
			}
				
				
           ///
          
          
          
          
      }
	    
	    
		  
	 
		  
		
		
	   
	}

	
	@FXML
	private void initialize() {
		
		vBox.getChildren().add(webView);
				
		 
	}
	
	
	
	
	
}
