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
	private static Scene guiScene;

    public static Stage getStage() {
        return guiStage;
    }
    
    public static Scene getScene() {
        return guiScene;
    }
	
	@Override
	public void start(Stage primaryStage) {
		try {			
			guiStage = primaryStage;
			guiStage.initStyle(StageStyle.UNDECORATED); //removes title bar
			guiStage.getIcons().add(new Image("/images/travel_icon.png")); //adds icon
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("view/Login.fxml"));
			
			//used for making window movable with mouse --> lack of title bar makes this necessary
			Drag.makeWindowDraggable(root, guiStage);
			
			guiScene = new Scene(root);			
			guiScene.getStylesheets().add(getClass().getClassLoader().getResource("view/login.css").toExternalForm());
			guiStage.setScene(guiScene);
			guiStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
}
