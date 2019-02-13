package application;

import java.io.File;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;


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

import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;
import netscape.javascript.JSObject;

public class Handler {

    // this @FXML thing lets java know that.
    // we want to set this var to the value of the FXML node that has the
    // Fx:id value of its name in this case vBox
    // and lets it see private vars

    public WebView webView = new WebView();// a javafx node 
    public WebEngine engine = webView.getEngine();// the web engine

   
    public File[] MOD;// loads the chapters in to the program
    public File[] MOD2;// gets the files in the chapters

    LoadFiles loadMod = new LoadFiles();
    @FXML
    public VBox vBox;

    @FXML
    public MenuBar mainMenuBar;
    LoadFiles l = new LoadFiles();

    @FXML
    private void handleOpenMod() {

	DirectoryChooser fileChooser = new DirectoryChooser();
	fileChooser.setTitle("Open Resource File");
	File selectedFile = fileChooser.showDialog(Main.STAGE);//opens the file selector

	MOD = selectedFile.listFiles();

	for (int i = 0; i < MOD.length; i++) { // list over all the files in the directory selected 

	    Menu menu = new Menu(MOD[i].getName());//make a new menu with name of file  

	    mainMenuBar.getMenus().add(menu);//adds it to the menu bar so it show up

	    MOD2 = MOD[i].listFiles();//gets all the file in the sub directory

	    for (int j = 0; j < MOD2.length; j++) {

		MenuItem menui = new MenuItem(MOD2[j].getName());// Make a new menu item with the name of the file in
								 

		mainMenuBar.getMenus().get(1).getItems().add(menui);//

		ObservableList<MenuItem> menuItem = mainMenuBar.getMenus().get(j + 1).getItems();// this need to be +1
											      // one because the first
											      // item is the open item

		File item = MOD2[j];//this here because MOD2 can't be dircely accessed in handle

		EventHandler<ActionEvent> eventHandler = new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent e) {

			String path = item.getAbsolutePath();

			File f = new File(path);

			engine.load(f.toURI().toString());// loads the web page 
			
			
			
			//-------------------------------------start of listener --------------------------------//
			/**
			 * gets all the information from the web page by calling it methods 
			 * 
			 * 
			 * 
			 * 
			 *
			*/
			EventListener listener = new EventListener() {
			    @Override
			    public void handleEvent(Event ev) {
				System.out.println("hi");

				JSObject jdoc = (JSObject) engine.getDocument();// loads java script from html

				System.out.println(engine.executeScript("getUserAnswer();"));
				System.out.println(engine.executeScript("getIsRight();"));
				System.out.println(engine.executeScript("getPercenDiff();"));

			    }
			};
			
			//-------------------------------------end of listener --------------------------------//
			
			 //make sure the web page is load before adding listener
			engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
			 

			    @Override
			    public void changed(ObservableValue<? extends State> ov, State t, State t1) {// ativats if the web page loads or is loading
				if (t1 == Worker.State.SUCCEEDED) {

				    Document doc = engine.getDocument();//gets the web site 

				    Element el = doc.getElementById("submitto");//gets the html node with submitto id

				    ((EventTarget) el).addEventListener("click", listener, false);//adds a listener so for when it gets clicked 

				}

			    }
			});

		    }
		};

		menuItem.get(j).setOnAction(eventHandler);//add all the funtions to the menu items and web page so that when you click it it loads 

	    }

	   

	}

    }

    @FXML
    private void initialize() {

	vBox.getChildren().add(webView);

    }
    public VBox getvBox() {
   	return vBox;
       }

       public MenuBar getMainMenu() {
   	return mainMenuBar;
       }


}
