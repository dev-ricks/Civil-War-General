module com.devricks.civilwargeneral {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    opens com.devricks.civilwargeneral to javafx.fxml;
    exports com.devricks.civilwargeneral;
    opens com.devricks.civilwargeneral.orders to com.fasterxml.jackson.databind;
}