package application;



import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.TextField;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;

import javafx.util.Callback;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeCell;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.DirectoryChooser;

public class Handler {

    // this @FXML thing lets java know that.
    // we want to set this var to the value of the FXML node that has the
    // Fx:id value of its name in this case vBox
    // and lets it see private vars
	public static  EventHandler<ActionEvent> eventHandler = Handler::handle;
	public static String path ;
    public static WebView webView = new WebView();// a javafx node
    public static WebEngine engine;// the web engine
           static XMLReadWright XML  = new XMLReadWright();
            

    public List <String> backList;
    LoadFiles loadMod = new LoadFiles();
    @FXML
    public VBox vBox;

    @FXML
    public MenuBar mainMenuBar;
    LoadFiles l = new LoadFiles();

    @FXML
    public TreeView<String> tree;

    TreeItem<String> rootItem = new TreeItem<String>("root");

    
    
    public static  void handle(ActionEvent e) {

		//path = item.getAbsolutePath();

		File f = new File(path);
		 engine = webView.getEngine();
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

			//JSObject jdoc = (JSObject) engine.getDocument();// loads java script from html

			System.out.println(engine.executeScript("getUserAnswer();").toString());
			System.out.println(engine.executeScript("getIsRight();"));
			System.out.println(engine.executeScript("getPercenDiff();"));
			XML.logAnswer(
					"jordan"
					, engine.executeScript("getUserAnswer();").toString()

					, engine.executeScript("getPercenDiff();").toString()

					,engine.executeScript("getIsRight();").toString()

					, f.getAbsolutePath()+"\\data.xml");

		    }
		};

		//-------------------------------------end of listener --------------------------------//

		 //make sure the web page is load before adding listener
		engine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {


		    @Override
		    public void changed(ObservableValue<? extends State> ov, State t, State t1) {// Activates if the web page loads or is loading
			if (t1 == Worker.State.SUCCEEDED) {

			    Document doc = engine.getDocument();//gets the web site

			    Element el = doc.getElementById("submitto");//gets the html node with submitto id

			    ((EventTarget) el).addEventListener("click", listener, false);//adds a listener so for when it gets clicked

			}

		    }
		});

	    }
    @FXML
    private void handleOpenMod() {
    	  
    	
        List <File> MOD;// loads the chapters in to the program
        List <File> MOD2;// gets the files in the chapters
        
    	
    	
	DirectoryChooser fileChooser = new DirectoryChooser();
	fileChooser.setTitle("Open Resource File");
	File selectedFile = new File("C:\\Users\\Jordan\\Documents\\mod");//fileChooser.showDialog(Main.STAGE);//opens the file selector

	//for (int i = 0; i < mainMenuBar.getMenus().size(); i++) mainMenuBar.getMenus().remove(i);//remove old tabs
	//mainMenuBar.getMenus().clear();
	//if(MOD!=null)for (int i = 0; i <MOD.size(); i++) MOD.remove(i);
	//if(MOD2!=null)for (int i = 0; i <MOD2.size(); i++) MOD2.remove(i);


	MOD= new LinkedList<File>(Arrays.asList(selectedFile.listFiles()));
	 
     rootItem.setExpanded(true);
	for(int i=0;i<MOD.size();i++) {

		if(!MOD.get(i).isDirectory())MOD.remove(i);



	}
       System.out.print(MOD);


	for (int i = 0; i < MOD.size(); i++) { // list over all the files in the directory selected
		TreeItem<String> ch = new TreeItem<String> (MOD.get(i).getName());
		//make a new menu with name of file



	    MOD2 = new LinkedList<File>(Arrays.asList(MOD.get(i).listFiles()));//gets all the file in the sub directory

	    for (int j = 0; j < MOD2.size(); j++)if(!MOD2.get(j).getName().endsWith(".html"))MOD2.remove(j);


	    for (int j = 0; j < MOD2.size(); j++) {

	    	TreeItem<String> q = new TreeItem<String>(MOD2.get(j).getName()+"||"+MOD2.get(j).getAbsolutePath());// Make a new menu item with the name of the file in


		  ch.getChildren().add(q);//this need to be +1
	      // one because the first
	      // item is the open item

		List<TreeItem<String>> menuItem = new ArrayList<TreeItem<String>>();
		menuItem.add(q);
		File item = MOD2.get(j);//this here because MOD2 can't be dircely accessed in handle

		
		 

		

		//menuItem.get(j).addEventHandler(MouseEvent.MOUSE_CLICKED,eventHandler);//add all the funtions to the menu items and web page so that when you click it it loads

	    }
	    rootItem.getChildren().add(ch);


	}
	tree.setRoot(rootItem);
   tree.setEditable(true);
    tree.setCellFactory(new Callback<TreeView<String>,TreeCell<String>>(){
        @Override
        public TreeCell<String> call(TreeView<String> p) {
            return new TreeCellText();
        }
    });

	 

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
       private  final class TreeCellText extends TreeCell<String>{
    	   private ContextMenu addMenu = new ContextMenu();
    		private TextField textField;
    	    // private ContextMenu addMenu = new ContextMenu();
    		 public TreeCellText() {
    			// createTextField();
    	            MenuItem addMenuItem = new MenuItem("Add Employee");
    	            addMenu.getItems().add(addMenuItem);
    	            addMenuItem.setOnAction(new EventHandler() {
    	                public void handle(javafx.event.Event event ) {
    	                    TreeItem newEmployee = 
    	                        new TreeItem<String>("New Employee");
    	                            getTreeItem().getChildren().add(newEmployee);
    	                }

						
    	            });
    	        }
    		 
    	      // @Override
    	      //  public void startEdit() {
    	         //   super.startEdit();
    	 
    	         //   if (textField == null) {
    	               // createTextField();
    	           // }
    	           // setText(null);
    	       //     setGraphic(textField);
    	          //  textField.selectAll();
    	       // }
    	       @Override
    	        public void updateItem(String item, boolean empty) {
    	            super.updateItem(item, empty);
    	 
    	            if (empty) {
    	                setText(null);
    	                setGraphic(null);
    	            } else {
    	                if (isEditing()) {
    	                    if (textField != null) {
    	                        textField.setText(getString());
    	                    }
    	                    setText(null);
    	                    setGraphic(textField);
    	                } else {
    	                	
    	                    setText(getString());
    	                    setGraphic(getTreeItem().getGraphic());
    	                    if (
    	                        getTreeItem().isLeaf()&&getTreeItem().getParent()!= null
    	                    ){
    	                        
    	                        
    	                        System.out.println(getString());
    	                        if(getString().contains("||")) {
    	                        Handler.path=getString().substring(getString().indexOf("||"));
    	                       
    	                       // super.updateItem( getString().substring(0, getString().indexOf("||")), false);
    	                        addMenu.setOnAction(Handler.eventHandler);
    	                        }
    	                        setContextMenu(addMenu);
    	                    }
    	                }
    	            }
    	        }

    		
    		
    		 private void createTextField() {
    	       //  textField = new TextField(getString());
    	         
    	         //textField.setOnAction(Handler.eventHandler);//Handler.eventHandler);
    	         
    	     }

    	     private String getString() {
    	         return getItem() == null ? "" : getItem().toString();
    	     }
    	    
    		
    		
    	}

}




