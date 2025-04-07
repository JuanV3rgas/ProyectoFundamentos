module com.fundamentos.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.sql;
    requires com.h2database;

    opens com.fundamentos.proyecto.controller to javafx.fxml;
    opens com.fundamentos.proyecto.app to javafx.fxml;
    opens com.fundamentos.proyecto.util to javafx.fxml;

    exports com.fundamentos.proyecto.app;
    exports com.fundamentos.proyecto.controller;


}
