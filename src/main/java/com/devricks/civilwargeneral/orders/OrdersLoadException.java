package com.devricks.civilwargeneral.orders;

/**
 * Exception thrown when there is an issue loading military orders.
 */
public class OrdersLoadException extends Exception {
    /**
     * Constructs a new OrdersLoadException with the specified message.
     *
     * @param message the detail message
     */
    public OrdersLoadException(String message) {
        super(message);
    }

    /**
     * Constructs a new OrdersLoadException with the specified message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public OrdersLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
