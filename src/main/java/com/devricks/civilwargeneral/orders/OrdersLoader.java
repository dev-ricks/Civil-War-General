package com.devricks.civilwargeneral.orders;

/**
 * Interface for loading military orders from external sources.
 */
public interface OrdersLoader {
    /**
     * Loads the default set of military orders.
     *
     * @return the collection of default orders
     * @throws OrdersLoadException if the orders cannot be loaded
     */
    Orders loadDefaults() throws OrdersLoadException;
}
