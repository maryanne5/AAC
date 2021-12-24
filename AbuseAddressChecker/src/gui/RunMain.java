package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.HyperlinkCell;

public class RunMain extends Application{


	    
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
			loader.load();
			Parent p = loader.getRoot();
			HyperlinkCell.setHostServices(getHostServices());
			primaryStage.setTitle("Abuse Address Checker");
			primaryStage.setScene(new Scene(p));
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
