package application;

import java.io.File;
import java.net.URL;

import javafx.scene.control.Menu;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;



public class LoadFiles {

	public static File[] MOD;
	

	public void loadMod(){
		
		
		
		
		    DirectoryChooser  fileChooser = new DirectoryChooser();
		   fileChooser.setTitle("Open Resource File");
		    File selectedFile = fileChooser.showDialog(null);
		   String path = "C:\\Users\\Jordan\\Documents\\test\\ch1\\q1.html";//selectedFile.getAbsolutePath();
		   String javaPath = path.replace("\\", "/");
		    
		   URL url = this.getClass().getResource(path);
		    MOD = selectedFile.listFiles();
		    //engine.load(selectedFile.toURI().toString());
		    
		
		
		
		for (File file : MOD) 
        {
			
			File f = new File(path);
			
			Menu menu = new Menu(file.getName());
			//getMenus().add(menu);
          System.out.println(file.getAbsolutePath());
            
            
            
            
        }
	 
	
	
	}
	
	
	
	
}
