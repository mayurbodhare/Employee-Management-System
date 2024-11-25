package org.openjfx.ems;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.openjfx.ems.Entity.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.Parent;


public class UserSignupController implements Initializable{
	
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
    private void goToUserLogin() throws IOException {
        App.setRoot("user_login");
    }
    
	@FXML
    private void onSubmit() throws IOException {
		Session session = null;
		try {
			
			session = HibernateUtil.getSessionFactory().openSession();
			// Start a transaction
            Transaction tx = session.beginTransaction();
            
            // Create a new User object
        	User newUser = new User();

        	// Set values directly into the User object
        	newUser.setName(name.getText());
        	newUser.setPhone(phone.getText());
        	newUser.setEmail(email.getText());
        	newUser.setRole(role.getText());
        	newUser.setSalary(Double.parseDouble(salary.getText())); // Assuming salary is a number
        	newUser.setGender(gender.getValue());
        	newUser.setUsername(username.getText());
        	newUser.setPassword(password.getText());
        	
        	session.persist(newUser); // Persist the entity to the database

            // Commit the transaction
            tx.commit();
            
         // Pass the newUser to the dashboard
            App.setRoot("user_dashboard", newUser);
                        
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