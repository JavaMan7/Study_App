package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Frame extends Application {
    public static Stage STAGE;
	@Override
	public void start(Stage primaryStage) throws Exception {
		STAGE = primaryStage;
		
        Parent root = FXMLLoader.load(getClass().getResource("q1.fxml"));
        
        primaryStage.setTitle("Study app");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
		
	}

}
