module org.openjfx.ems {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.openjfx.ems to javafx.fxml;
    exports org.openjfx.ems;
}
