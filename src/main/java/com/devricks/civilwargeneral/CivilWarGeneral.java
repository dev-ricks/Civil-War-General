package com.devricks.civilwargeneral;

import com.devricks.civilwargeneral.ai.CommandSelector;
import com.devricks.civilwargeneral.controllers.MainView;
import com.devricks.civilwargeneral.controllers.MainViewPresenter;
import com.devricks.civilwargeneral.orders.OrdersLoaderImplementation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main entry point for the Civil War General application.
 * This class handles the JavaFX application lifecycle and sets up the
 * Model-View-Presenter (MVP) components using a custom controller factory.
 */
public class CivilWarGeneral extends Application {

    /**
     * Default constructor for CivilWarGeneral.
     */
    public CivilWarGeneral() {
    }

    /**
     * Starts the JavaFX application by loading the FXML and initializing the MVP components.
     *
     * @param primaryWindow the primary stage for this application
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage primaryWindow) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CivilWarGeneral.class.getResource(
                "main-view.fxml"));
        fxmlLoader.setControllerFactory(type -> {
            if (type == MainView.class) {
                MainView view = new MainView();
                var ordersLoader = new OrdersLoaderImplementation();
                var selector = new CommandSelector();
                var presenter = new MainViewPresenter(view, ordersLoader, selector);
                view.setPresenter(presenter);
                return view;
            }
            try {
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Failed to construct controller: " + type, e);
            }
        });
        Parent root = fxmlLoader.load();
        primaryWindow.setScene(new Scene(root));
        primaryWindow.setTitle("Civil War General");
        primaryWindow.show();
        /* keeping temp to compare with new approach
        Scene sceneContainer = new Scene(fxmlLoader.load(), 800, 600);
        primaryWindow.setScene(sceneContainer);
        primaryWindow.setTitle("Civil War General");
        primaryWindow.show();
        */
    }

    /**
     * The main method that serves as the entry point for the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
