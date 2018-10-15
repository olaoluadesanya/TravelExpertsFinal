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
		
    @FXML
    private JFXPasswordField tfPass;

    @FXML
    private JFXTextField tfUser;

    @FXML
    private JFXButton btnLogin;

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
                    JsonObject json = new JsonObject();
                    json.addProperty("username", tfUser.getText());
                    json.addProperty("password", tfPass.getText());

                    String postUrl = "http://localhost:8080/TravelExperts2/rs/db/agentlogin";// put in your url
                    Gson gson = new Gson();
                    HttpClient httpClient = HttpClientBuilder.create().build();
                    HttpPost post = new HttpPost(postUrl);
                    StringEntity postingString = null;
                    try {
                        postingString = new StringEntity(gson.toJson(json));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    post.setEntity(postingString);
                    post.setHeader("Content-type", "application/json");
                    HttpResponse response = null;
                    try {
                        response = httpClient.execute(post);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    HttpEntity entity = response.getEntity();
                    String responseString = null;
                    try {
                        responseString = EntityUtils.toString(entity, "UTF-8");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                   
                    //change from login to main page 
                    try {                    	                   	
						Parent mainPageParent = FXMLLoader.load(getClass().getClassLoader().getResource("view/Packages.fxml"));
						Scene mainPageScene = new Scene(mainPageParent);
						Stage app_stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
						//center stage
						double width = 640;
                        double height = 575;	
                        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
                        app_stage.setX((screenBounds.getWidth() - width) / 2); 
                        app_stage.setY((screenBounds.getHeight() - height) / 2); 
                        
						app_stage.setScene(mainPageScene);
					} catch (IOException e) {
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tfPass.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

            }
        });
    }
}