package control;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private static Stage guiStage;

    public static Stage getStage() {
        return guiStage;
    }
	
	@Override
	public void start(Stage primaryStage) {
		try {			
			guiStage = primaryStage;
			primaryStage.initStyle(StageStyle.UNDECORATED); //removes title bar
			primaryStage.getIcons().add(new Image("/images/travel_icon.png")); //adds icon
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml"));
			
			//used for making window movable with mouse --> lack of title bar makes this necessary
			Drag.makeWindowDraggable(root, primaryStage);
			
			Scene scene = new Scene(root);			
			scene.getStylesheets().add(getClass().getClassLoader().getResource("view/login.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
