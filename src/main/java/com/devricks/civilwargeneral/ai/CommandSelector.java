package com.devricks.civilwargeneral.ai;

import com.devricks.civilwargeneral.orders.Order;
import com.devricks.civilwargeneral.orders.Orders;
import java.security.SecureRandom;

/**
 * Engine for selecting tactical orders from a collection.
 * Uses cryptographically secure random number generation to ensure fair distribution.
 */
public class CommandSelector {

    /**
     * Default constructor for CommandSelector.
     */
    public CommandSelector() {
    }

    /**
     * Randomly selects a valid order from the provided collection.
     *
     * @param orders the collection of orders to select from
     * @return a randomly selected valid Order, or null if the collection is empty or invalid
     */
    // Maybe this should be static if state is not a property needed for commandselector, will evaluate later on
    public Order randomOrderSelector(Orders orders) {
        if (orders == null || orders.getAllOrders().isEmpty()) {
            return null;
        }
        int randomIndex = randomNumber(orders.getAllOrders().size() - 1);
        Order selectedOrder = orders.getAllOrders().get(randomIndex);
        if (selectedOrder == null || !selectedOrder.isValid()) {
            return null;
        }
        return selectedOrder;
    }

    private int randomNumber(int max) {
        SecureRandom secureRandom = new SecureRandom();
        return secureRandom.nextInt(max + 1);
    }
}
