package org.openjfx.ems;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class UserSignupController implements Initializable{
	
	@FXML
	private ChoiceBox<String> gender;
	
	private String[] genders = {"Male", "Female"};

    @FXML
    private void goBackToHome() throws IOException {
        App.setRoot("home");
    }
    
    @FXML
    private void goToUserLogin() throws IOException {
        App.setRoot("user_login");
    }
    
    @FXML
    private void onSubmit() throws IOException {
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		gender.getItems().addAll(genders);
	}
}