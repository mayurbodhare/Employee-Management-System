package org.openjfx.ems;

import java.io.IOException;

import org.openjfx.ems.Entity.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UserDashboardController {
	
	User currentUser = new User();
	
	@FXML
    private Label name;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label role;
    @FXML
    private Label salary;
    @FXML
    private Label gender;
    @FXML
    private Label username;
    @FXML
    private Label id;
    @FXML
    private Label attendance;
	
 // Method to initialize the user data
    public void setUserDetails(User user) {
        if (user != null) {
            currentUser = user;

            // Update the labels with user data
            id.setText(String.valueOf(user.getId()));
            name.setText(user.getName());
            phone.setText(user.getPhone());
            email.setText(user.getEmail());
            role.setText(user.getRole());
            salary.setText(String.valueOf(user.getSalary()));
            gender.setText(user.getGender());
            username.setText(user.getUsername());
            // Uncomment or add attendance logic if required
            // attendance.setText(String.valueOf(user.getAttendance()));
        }
    }
    
    @FXML
    private void ShowUserLoginPage() throws IOException {
    	currentUser = null;
        App.setRoot("user_login");
    }
	
    @FXML
    public void initialize() {
    	if (currentUser != null) {
            // Data has been set; no need to reinitialize
            return;
        }
        // Placeholder code for testing (remove or replace in production)
        System.out.println("Dashboard initialized");
    }
}
