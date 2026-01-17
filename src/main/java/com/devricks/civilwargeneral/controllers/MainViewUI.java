package com.devricks.civilwargeneral.controllers;

import com.devricks.civilwargeneral.orders.Order;

/**
 * Interface representing the UI contract for the Main View.
 * Provides methods for the Presenter to update the UI.
 */
public interface MainViewUI {
    /**
     * Clears the current list of orders displayed in the UI.
     */
    void clearList();

    /**
     * Enables or disables the command generation button.
     *
     * @param enabled true to enable, false to disable
     */
    void setGeneratedEnabled(boolean enabled);

    /**
     * Appends a single order to the UI representation (e.g., a ListView).
     * Must be called on the JavaFX Application Thread. Nulls are ignored.
     *
     * @param order the order to add to the UI
     */
    // May want to change this to appendOrder, will reevaluate later
    void addOrder(Order order);
}
