package com.devricks.civilwargeneral.controllers;

import com.devricks.civilwargeneral.ai.CommandSelector;
import com.devricks.civilwargeneral.orders.Order;
import com.devricks.civilwargeneral.orders.Orders;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

public class MainView {
    public Button btnGenerateCommand;
    public ListView<Order> commandList;

    Orders orders;

    public void initialize() {
        commandList.getItems().clear();
        orders = new Orders();
        orders.loadFromFile("/com/devricks/civilwargeneral/default-orders.json");
    }

    public void onCommandButtonClick(ActionEvent actionEvent) {
        System.out.println("Event triggered by: " + actionEvent.getSource());
        CommandSelector commandSelector = new CommandSelector();
        Order selectedOrder = commandSelector.randomOrderSelector(orders);
        commandList.getItems().add(selectedOrder);
    }
}
