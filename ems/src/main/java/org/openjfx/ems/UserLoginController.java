package org.openjfx.ems;

import java.io.IOException;

import org.hibernate.Session;
import org.openjfx.ems.Entity.User;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class UserLoginController {
	
	@FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    private Label errorLabel;

    @FXML
    private void goBackToHome() throws IOException {
        App.setRoot("home");
    }
    
    @FXML
    private void goToUserSignup() throws IOException {
        App.setRoot("user_signup");
    }
    
    @FXML
    private void onSubmit() throws IOException {
    	Session session = null;
    	try {
    		String enteredUsername = username.getText();
    	    String enteredPassword = password.getText().trim();

    	    if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {
    	        errorLabel.setText("Username and Password cannot be empty!");
    	        return;
    	    }
    	    
			session = HibernateUtil.getSessionFactory().openSession();
			
//			User user = session.get(User.class, enteredUsername);
			String hql = "FROM User WHERE username = :username";
	        User user = session.createQuery(hql, User.class)
	                           .setParameter("username", enteredUsername)
	                           .uniqueResult();
			
			if (user == null) {
	            errorLabel.setText("User does not exist!");
	        } else if (!user.getPassword().equals(enteredPassword)) {
	            errorLabel.setText("Incorrect password!");
	        } else if(user.getPassword().equals(enteredPassword)){
	            errorLabel.setText(""); // Clear error label
	            App.setRoot("user_dashboard", user);; // Redirect to the dashboard page
	        }
			
    	}catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
                session.close();
            }
            // Shutdown Hibernate only when the application is terminating
            HibernateUtil.shutdown();
        }
    }
}
