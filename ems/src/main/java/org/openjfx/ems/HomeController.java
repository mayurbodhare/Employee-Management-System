package org.openjfx.ems;

import java.io.IOException;

import javafx.fxml.FXML;

public class HomeController {
	@FXML
    private void ShowUserLoginPage() throws IOException {
        App.setRoot("user_login");
    }
	
	@FXML
    private void ShowAdminLoginPage() throws IOException {
        App.setRoot("admin_login");
    }
}
