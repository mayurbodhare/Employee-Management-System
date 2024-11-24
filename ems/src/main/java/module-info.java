module org.openjfx.ems {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;

    opens org.openjfx.ems to javafx.fxml;
    opens org.openjfx.ems.Entity to org.hibernate.orm.core; // Allow Hibernate to access the entity package

    exports org.openjfx.ems;
}
