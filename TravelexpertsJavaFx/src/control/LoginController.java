package control;

import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import com.google.gson.Gson;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class LoginController implements Initializable {
		
	//private String URLCONSTANT = "http://10.163.101.59:8080";
	private String URLCONSTANT ="http://localhost:8080";
	
    @FXML
    private JFXPasswordField tfPass;

    @FXML
    private JFXTextField tfUser;

    @FXML
    private JFXButton btnLogin;
    
    @FXML
    private JFXButton btnLoginClose;
    
    @FXML
    private JFXButton btnLoginMin;    
    

    @FXML
    private void login(Event event) {
        if(tfUser.getText().isEmpty() && tfPass.getText().isEmpty()){
            showEmptyFieldAlert("Please enter your username and password");
            tfUser.requestFocus();
            return;
        }
        if(tfUser.getText().isEmpty()){
            showEmptyFieldAlert("Please enter your username");
            tfUser.requestFocus();
            return;
        }
        if(tfPass.getText().isEmpty()){
            showEmptyFieldAlert("Please enter your password");
            tfPass.requestFocus();
            return;
        }
        else{
        	//runlater to avoid gui hanging
            Platform.runLater(new Runnable() {
                public void run() {
                	//create json to send to service
                    JsonObject json = new JsonObject();
                    json.addProperty("username", tfUser.getText());
                    json.addProperty("password", tfPass.getText());

                    //connect to service
                    String postUrl = URLCONSTANT + "/TravelExperts2/rs/db/agentlogin";// put in your url
                    Gson gson = new Gson();
                    HttpClient httpClient = HttpClientBuilder.create().build();
                    HttpPost post = new HttpPost(postUrl);
                    StringEntity postingString = null;                    
                    try {
                    	//send json to service
						postingString = new StringEntity(gson.toJson(json));
						post.setEntity(postingString);
	                    post.setHeader("Content-type", "application/json");
	                    HttpResponse response = null;
	                    response = httpClient.execute(post);  
	                    //receive response form service
	                    HttpEntity entity = response.getEntity();
	                    String responseString = "false";
	                    responseString = EntityUtils.toString(entity, "UTF-8");
	                    System.out.println(responseString);    
	                    //successful login
	                    if (responseString.equals("true")) {
	                    	//change from login to main page 
	                    	Parent mainPageParent = FXMLLoader.load(getClass().getClassLoader().getResource("view/Packages.fxml"));
							Scene mainPageScene = new Scene(mainPageParent);
							Stage mainStage = Main.getStage();
							//center stage on screen
							double width = 640;
		                    double height = 575;
		                    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		                    mainStage.setX((screenBounds.getWidth() - width) / 2); 
		                    mainStage.setY((screenBounds.getHeight() - height) / 2);		                    
							mainStage.setScene(mainPageScene);
	                    }
	                    //failed login
	                    else {
	                    	Alert alert = new Alert(Alert.AlertType.ERROR);
	                        alert.setTitle("Incorrect username or password");
	                        alert.setHeaderText(null);
	                        alert.setContentText("Incorrect username or password.");
	                        alert.showAndWait();
	                    }	                    	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
                }
            });
        }
    }

    @FXML
    public void checkEnterPressed(KeyEvent event) {
        if(event.getCode().equals(KeyCode.ENTER)) {
            btnLogin.fire();
        }
    }

    private void showEmptyFieldAlert(String warning) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Empty input field");
        alert.setHeaderText(null);
        alert.setContentText(warning);
        alert.showAndWait();
    }
    
    @FXML
    private void closeButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) btnLoginClose.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void minButtonAction(){
        // get a handle to the stage
        Stage stage = (Stage) btnLoginMin.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	Image closeIcon = new Image(getClass().getResourceAsStream("/images/close_icon.png"));
    	btnLoginClose.setGraphic(new ImageView(closeIcon));
    	
    	Image minIcon = new Image(getClass().getResourceAsStream("/images/minimize_icon.png"));
    	btnLoginMin.setGraphic(new ImageView(minIcon));
    }
}