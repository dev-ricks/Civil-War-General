package com.devricks.civilwargeneral.controllers;

import com.devricks.civilwargeneral.ai.CommandSelector;
import com.devricks.civilwargeneral.orders.Order;
import com.devricks.civilwargeneral.orders.OrdersLoaderImplementation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;


/**
 * Controller implementation for the Main View.
 * This class serves as the View in the MVP architecture and is linked to the FXML.
 */
public class MainView implements MainViewUI {
    @FXML
    private Button btnGenerateCommand;
    @FXML
    private ListView<Order> commandList;

    private MainViewPresenter presenter;

    /**
     * Default constructor for MainView.
     */
    public MainView() {
    }

    /**
     * Sets the presenter for this view.
     *
     * @param presenter the presenter to associate with this view
     */
    public void setPresenter(MainViewPresenter presenter) {
        this.presenter = presenter;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearList() {
        commandList.getItems().clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGeneratedEnabled(boolean enabled) {
        btnGenerateCommand.setDisable(!enabled);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addOrder(Order order) {
        if (order == null) return;
        commandList.getItems().add(order);
    }

    /**
     * Initializes the view. If no presenter is set, it creates a default one.
     * This is called automatically by JavaFX after the FXML is loaded.
     */
    @FXML
    public void initialize() {
        if (presenter == null) {
            var loader = new OrdersLoaderImplementation();
            var selector = new CommandSelector();
            this.presenter = new MainViewPresenter(this, loader, selector);
        }
        presenter.initialize();
    }

    /**
     * Event handler for the command generation button click.
     *
     * @param event the action event
     */
    @FXML
    public void onCommandButtonClick(ActionEvent event) {
        if (presenter != null) {
            presenter.onGenerateClicked();
        }
    }
}
