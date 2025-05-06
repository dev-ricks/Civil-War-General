package com.devricks.civilwargeneral;

import com.devricks.civilwargeneral.orders.Order;
import com.devricks.civilwargeneral.orders.Orders;
import com.devricks.civilwargeneral.ai.CommandSelector;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        Orders orders = new Orders();
        CommandSelector commandSelector = new CommandSelector();
        orders.loadFromFile("/com/devricks/civilwargeneral/default-orders.json");
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        welcomeText.setText(selectedOrder != null ? selectedOrder.toString() : "No valid order found.");
    }
}