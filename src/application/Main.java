package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

public class Main   extends Application{
	//public Parent ROOT;
	//public Stage STAGE;
	public static void main(String[] args) {
		
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//STAGE=primaryStage;
		Parent ROOT = FXMLLoader.load(getClass().getResource("q1.fxml"));
		    
		    
		    
		    
	        primaryStage.setTitle("Study app");
	        primaryStage.setScene(new Scene(ROOT));
	        primaryStage.show();
	}
}
