package org.openjfx.ems;

import java.io.IOException;

import javafx.fxml.FXML;

public class HomeController {
	@FXML
    private void ShowUserLoginPage() throws IOException {
        App.setRoot("secondary");
    }
	
	@FXML
    private void ShowAdminLoginPage() throws IOException {
        App.setRoot("primary");
    }
}
