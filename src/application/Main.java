package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;




public class Main   extends Application{
	//public Parent ROOT;
	//public Stage STAGE;
	
	public static Parent ROOT;
	public static Stage STAGE;
	public static void main(String[] args) {
		System.out.println("hi");
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws RuntimeException {
		try {
		
		
		STAGE=primaryStage;
		
		 try {
			ROOT = FXMLLoader.load(getClass().getResource("q1.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    
		
		    
	        primaryStage.setTitle("Study app");
	        primaryStage.setScene(new Scene(ROOT));
	        primaryStage.show();
	        
		}catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	}
}
