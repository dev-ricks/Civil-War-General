package com.devricks.civilwargeneral.orders;

import java.net.URL;
import java.util.function.Supplier;

/**
 * Implementation of the OrdersLoader interface that loads orders from the classpath.
 */
public class OrdersLoaderImplementation implements OrdersLoader {

    private static final String DEFAULTS_PATH =
            "/com/devricks/civilwargeneral/default-orders.json";

    private final String resourcePath;
    private final Supplier<Orders> ordersFactory;

    /**
     * Constructs a new OrdersLoaderImplementation with default settings.
     * Uses the default classpath resource and a default Orders factory.
     */
    public OrdersLoaderImplementation() {
        this(DEFAULTS_PATH, Orders::new);
    }

    /**
     * Constructs a new OrdersLoaderImplementation with specified resource path and factory.
     *
     * @param resourcePath the path to the JSON resource on the classpath
     * @param ordersFactory the factory used to create new Orders instances
     */
    public OrdersLoaderImplementation(String resourcePath, Supplier<Orders> ordersFactory) {
        this.resourcePath = resourcePath;
        this.ordersFactory = ordersFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Orders loadDefaults() throws OrdersLoadException {
        try {
            // Verify the resource exists on the classpath (under src/main/resources)
            URL url = getClass().getResource(resourcePath);
            if (url == null) {
                throw new OrdersLoadException(
                        "Default orders resource not found: " + resourcePath
                );
            }
            Orders orders = ordersFactory.get();
            // Reuse existing file/resource loading logic in Orders
            orders.loadFromFile(resourcePath);
            return orders;
        } catch (OrdersLoadException e) {
            // Re-throw as-is to preserve the explicit type
            throw e;
        } catch (RuntimeException e) {
            // If Orders.loadFromFile wrapped an OrdersLoadException, unwrap and rethrow it
            Throwable cause = e.getCause();
            if (cause instanceof OrdersLoadException ole) {
                throw ole;
            }
            // Otherwise, wrap the unexpected runtime exception
            throw new OrdersLoadException("Failed to load default orders", e);
        } catch (Exception e) {
            // Wrap any other unexpected exceptions
            throw new OrdersLoadException("Failed to load default orders", e);
        }
    }
}
