package org.openjfx.ems;

import java.io.IOException;
import javafx.fxml.FXML;

public class AdminLoginController {

    @FXML
    private void goBackToHome() throws IOException {
        App.setRoot("home");
    }
    
    @FXML
    private void goToAdminSignup() throws IOException {
        App.setRoot("admin_signup");
    }
}