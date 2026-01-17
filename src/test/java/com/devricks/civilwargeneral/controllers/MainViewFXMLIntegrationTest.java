package com.devricks.civilwargeneral.controllers;

import com.devricks.civilwargeneral.CivilWarGeneral;
import com.devricks.civilwargeneral.orders.Order;
import com.devricks.civilwargeneral.testutil.FxTestUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration-style test that loads the real FXML and relies on MainView's
 * internal presenter fallback (OrdersLoaderImplementation + CommandSelector).
 * It verifies that initialize enables the Generate button (defaults present)
 * and that clicking the button appends a command to the list.
 */
class MainViewFXMLIntegrationTest {

    @BeforeAll
    static void setupFx() {
        FxTestUtils.ensurePlatformStarted();
    }

    @Test
    void fxml_loads_and_generate_adds_item() throws Exception {
        final Parent[] rootHolder = new Parent[1];
        final MainView[] controllerHolder = new MainView[1];

        FxTestUtils.runOnFxAndWait(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(CivilWarGeneral.class.getResource("main-view.fxml"));
                Parent root = loader.load();
                rootHolder[0] = root;
                controllerHolder[0] = loader.getController();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        Parent root = rootHolder[0];
        assertNotNull(root, "FXML root should not be null");

        // Find nodes by CSS id that we explicitly set in FXML
        Button generateBtn = (Button) root.lookup("#btnGenerateCommand");
        @SuppressWarnings("unchecked")
        ListView<Order> listView = (ListView<Order>) root.lookup("#commandList");

        assertNotNull(generateBtn, "Generate button should be present");
        assertNotNull(listView, "ListView should be present");

        // After initialize, defaults should exist so button should be enabled
        assertFalse(generateBtn.isDisable(), "Generate button should be enabled when defaults present");

        // Fire the button and expect one item added to the list
        FxTestUtils.runOnFxAndWait(generateBtn::fire);

        assertFalse(listView.getItems().isEmpty(), "ListView should contain at least one generated order");
    }
}
