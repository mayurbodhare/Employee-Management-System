package org.openjfx.ems;

import java.io.IOException;

import org.openjfx.ems.Entity.Admin;
import org.openjfx.ems.Entity.User;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class AdminDashboardController {
	
	Admin currentAdmin = new Admin();
	
	@FXML
	private Text name;
	
	
	@FXML
	private void onSearch() throws IOException{
		
	}
	
	@FXML
	private void addAttendence() throws IOException{
		
	}
	
	@FXML
	private void onAssign() throws IOException{
		
	}
	
//	Method to initialize the user data
    public void setUserDetails(Admin user) {
        if (user != null) {
            currentAdmin = user;
            
            name.setText(user.getName());
        }
    }
	
	@FXML
    private void ShowAdminLoginPage() throws IOException {
		currentAdmin = null;
        App.setRoot("admin_login");
    }
	
	@FXML
    public void initialize() {
    	if (currentAdmin != null) {
            // Data has been set; no need to reinitialize
            return;
        }
        // Placeholder code for testing (remove or replace in production)
        System.out.println("Dashboard initialized");
    }
}
