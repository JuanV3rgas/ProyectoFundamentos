module com.fundamentos.proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires java.sql;
    requires com.h2database;
    requires java.desktop;

    opens com.fundamentos.proyecto.controller to javafx.fxml;
    opens com.fundamentos.proyecto.app to javafx.fxml;
    opens com.fundamentos.proyecto.util to javafx.fxml;
    opens com.fundamentos.proyecto.model to javafx.base;
    exports com.fundamentos.proyecto.app;
    exports com.fundamentos.proyecto.controller;
}
