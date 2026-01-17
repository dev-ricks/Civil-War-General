/**
 * The module definition for the Civil War General application.
 * Defines dependencies and exports/opens for JavaFX, Jackson, and testing frameworks.
 */
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
    requires java.desktop;

    opens com.devricks.civilwargeneral to javafx.fxml;
    opens com.devricks.civilwargeneral.orders to com.fasterxml.jackson.databind, org.junit.platform.commons, org.mockito;
    opens com.devricks.civilwargeneral.ai to org.junit.platform.commons, org.mockito;
    opens com.devricks.civilwargeneral.controllers to javafx.fxml;
    exports com.devricks.civilwargeneral;
    exports com.devricks.civilwargeneral.controllers;
    exports com.devricks.civilwargeneral.orders;
    exports com.devricks.civilwargeneral.ai;
}