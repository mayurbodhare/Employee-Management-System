package org.openjfx.ems;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

public class AdminSignupController implements Initializable{
	
	@FXML
	private ChoiceBox<String> gender;
	
	private String[] genders = {"Male", "Female"};

    @FXML
    private void goBackToHome() throws IOException {
        App.setRoot("home");
    }
    
    @FXML
    private void goToAdminLogin() throws IOException {
        App.setRoot("admin_login");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		gender.getItems().addAll(genders);
	}
}