package control;

import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Drag {
		//used for making window movable with mouse --> lack of title bar makes this necessary
		private static double xOffset = 0; 
		private static double yOffset = 0;
		
		public static void makeWindowDraggable(Parent parent, Stage stage) {
			parent.setOnMousePressed(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	                xOffset = event.getSceneX();
	                yOffset = event.getSceneY();
	            }
	        });
	        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
	            @Override
	            public void handle(MouseEvent event) {
	            	stage.setX(event.getScreenX() - xOffset);
	            	stage.setY(event.getScreenY() - yOffset);
	            }
	        });
		}
}
