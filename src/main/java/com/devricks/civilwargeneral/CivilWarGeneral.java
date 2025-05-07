package com.devricks.civilwargeneral;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CivilWarGeneral extends Application {

    @Override
    public void start(Stage primaryWindow) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CivilWarGeneral.class.getResource("main-view.fxml"));
        Scene sceneContainer = new Scene(fxmlLoader.load(), 800, 600);
        primaryWindow.setTitle("Civil War General");
        primaryWindow.setScene(sceneContainer);
        primaryWindow.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
