package org.openjfx.ems;

import java.io.IOException;
import javafx.fxml.FXML;

public class UserLoginController {

    @FXML
    private void goBackToHome() throws IOException {
        App.setRoot("home");
    }
    
    @FXML
    private void goToUserSignup() throws IOException {
        App.setRoot("user_signup");
    }
}
