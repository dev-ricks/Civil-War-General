package com.devricks.civilwargeneral.controllers;

import com.devricks.civilwargeneral.ai.CommandSelector;
import com.devricks.civilwargeneral.orders.Orders;
import com.devricks.civilwargeneral.orders.OrdersLoadException;
import com.devricks.civilwargeneral.orders.OrdersLoader;

/**
 * Presenter for the Main View in the MVP architecture.
 * Coordinates between the data loading, selection engine, and the UI.
 */
public final class MainViewPresenter {
    private final MainViewUI ui;
    private final OrdersLoader loader;
    private final CommandSelector selector;
    private Orders orders;

    /**
     * Constructs a new MainViewPresenter.
     *
     * @param ui the UI interface to interact with
     * @param loader the loader for military orders
     * @param selector the selection engine for picking orders
     */
    public MainViewPresenter(MainViewUI ui, OrdersLoader loader, CommandSelector selector) {
        this.ui = ui;
        this.loader = loader;
        this.selector = selector;
    }

    /**
     * Initializes the presenter by loading default orders and updating the UI state.
     */
    public void initialize() {
        ui.clearList();
        try {
            orders = loader.loadDefaults();
            boolean hasOrders = orders != null && !orders.getAllOrders().isEmpty();
            ui.setGeneratedEnabled(hasOrders);
        } catch (OrdersLoadException e) {
            ui.setGeneratedEnabled(false);
        }
    }

    /**
     * Handles the event when the generate button is clicked.
     * Triggers a random order selection and updates the UI history.
     */
    public void onGenerateClicked() {
        if (orders == null || orders.getAllOrders().isEmpty()) {
            return;
        }
        var selected = selector.randomOrderSelector(orders);
        if (selected != null) {
            ui.addOrder(selected);
        }
    }
}
