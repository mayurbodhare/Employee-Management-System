package org.openjfx.ems;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.openjfx.ems.Entity.Admin;
import org.openjfx.ems.Entity.User;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class AdminSignupController implements Initializable{
	
	@FXML
	private TextField name;
	
	@FXML
	private TextField phone;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField role;
	
	@FXML
	private TextField salary;
	
	@FXML
	private ChoiceBox<String> gender;
	
	@FXML
	private TextField username;
	
	@FXML
	private TextField password;
	
	
	private String[] genders = {"Male", "Female"};

    @FXML
    private void goBackToHome() throws IOException {
        App.setRoot("home");
    }
    
    @FXML
    private void goToAdminLogin() throws IOException {
        App.setRoot("admin_login");
    }
    
    @FXML
    private void onSubmit() throws IOException {
    	
    	Session session = null;
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			// Start a transaction
            Transaction tx = session.beginTransaction();
            
            // Create a new User object
        	Admin newAdmin = new Admin();

        	// Set values directly into the User object
        	newAdmin.setName(name.getText());
        	newAdmin.setPhone(phone.getText());
        	newAdmin.setEmail(email.getText());
        	newAdmin.setRole(role.getText());
        	newAdmin.setSalary(Double.parseDouble(salary.getText())); // Assuming salary is a number
        	newAdmin.setGender(gender.getValue());
        	newAdmin.setUsername(username.getText());
        	newAdmin.setPassword(password.getText());
        	
        	session.persist(newAdmin); // Persist the entity to the database

            // Commit the transaction
            tx.commit();
            
          App.setRoot("admin_dashboard", newAdmin);


            System.out.println("User Signed Up Successfully!");
            
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
                session.close();
            }
            // Shutdown Hibernate only when the application is terminating
            HibernateUtil.shutdown();
        }
    	
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		gender.getItems().addAll(genders);
	}
}