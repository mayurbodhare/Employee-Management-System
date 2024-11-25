package org.openjfx.ems;

import java.io.IOException;
import java.time.LocalDate;

import org.hibernate.Session;
import org.openjfx.ems.Entity.Admin;
import org.openjfx.ems.Entity.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AdminDashboardController {
	
	Admin currentAdmin = new Admin();
	User currentUser = new User();
	
	@FXML
	private Text name;
	
	@FXML
	private TextField id;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private Label count;
	
	@FXML
	private Button attendence_btn;
	
	
	
	public boolean isAttendanceButtonEnabled(User user) {
	    LocalDate today = LocalDate.now();
	    return !user.getAttendanceDates().contains(today);
	}
	
	
	
	@FXML
	private void onSearch() throws IOException{
		Session session = null;
		try {
			// Assuming `id` TextField is used to input the User ID
		    String userId = id.getText().trim(); // Get the user ID from the TextField

		    // Input validation
		    if (userId.isEmpty()) {
		        errorLabel.setText("Please enter a user ID.");
		        return;
		    }
		    
		    session = HibernateUtil.getSessionFactory().openSession();
		    
		 // Try to get the User by ID using Hibernate
	        User user = session.get(User.class, Long.parseLong(userId)); // Assuming the ID is a Long

	        // Check if user is found
	        if (user != null) {
	            currentUser = user; // Set the currentUser to the found user
	            count.setText(String.valueOf(user.getAttendanceDates().size()));
	            
	            // enable or disable the attendance button on the bases of isAttendanceButtonEnabled function
	            attendence_btn.setDisable(!isAttendanceButtonEnabled(user));
	            
	            errorLabel.setText(""); // Clear any previous errors
	            System.out.println("User found: " + user.getName());
	        } else {
	            // User not found
	            currentUser = null; // Ensure currentUser is reset
	            errorLabel.setText("User not found with ID: " + userId);
	            System.out.println("User not found.");
	        }
	        
			System.out.println("\n\n" + currentUser);
			System.out.println("User Found Bhai.");
		} catch (NumberFormatException e) {
	        // Handle invalid input (non-numeric ID)
	        errorLabel.setText("Invalid ID format. Please enter a numeric ID.");
	        System.out.println("Invalid ID format.");
	    } catch (Exception e) {
	        // Handle general errors (e.g., database connection issues)
	        errorLabel.setText("An error occurred while searching for the user.");
	        e.printStackTrace();
	    } finally {
	    	if (session != null && session.isOpen()) {
                session.close();
            }
            // Shutdown Hibernate only when the application is terminating
            HibernateUtil.shutdown();
		}
	}
	
	@FXML
	private void addAttendence() throws IOException{
		if (currentUser == null) {
	        errorLabel.setText("No user selected. Please search for a user first.");
	        return;
	    }

	    Session session = null;
	    
	    try {
	    	LocalDate today = LocalDate.now();

	        if (currentUser.getAttendanceDates().contains(today)) {
	            errorLabel.setText("Attendance for today is already marked.");
	            System.out.println("Attendance already marked for today.");
	            return;
	        }
	        
	     // Add today's date to the attendanceDates set
	        currentUser.getAttendanceDates().add(today);

	        // Save the updated user in the database
	        session = HibernateUtil.getSessionFactory().openSession();
	        session.beginTransaction();
	        session.merge(currentUser); // Update the user in the database
	        session.getTransaction().commit();
	        
	     // Update the UI
	        count.setText(String.valueOf(currentUser.getAttendanceDates().size()));
	        attendence_btn.setDisable(true); // Disable the attendance button after marking attendance
	        errorLabel.setText(""); // Clear any error messages

	        System.out.println("Attendance successfully marked for today.");
	        
		} catch (Exception e) {
			// Handle errors
	        if (session != null && session.getTransaction() != null) {
	            session.getTransaction().rollback(); // Roll back if there's an error
	        }
	        errorLabel.setText("An error occurred while marking attendance.");
	        e.printStackTrace();
			
		} finally {
			if (session != null && session.isOpen()) {
                session.close();
            }
            // Shutdown Hibernate only when the application is terminating
            HibernateUtil.shutdown();
		}
	    
	}
	
	@FXML
	private void onAssign() throws IOException{
		
	}
	
//	Method to initialize the user data
    public void setUserDetails(Admin admin) {
        if (admin != null) {
            currentAdmin = admin;
            
            name.setText(admin.getName());
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
