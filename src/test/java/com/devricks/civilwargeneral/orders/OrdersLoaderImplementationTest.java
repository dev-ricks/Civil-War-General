package com.devricks.civilwargeneral.orders;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrdersLoaderImplementationTest {

    // Note: these tests drive a small refactor: add a ctor that accepts (resourcePath, ordersFactory)
    private static final String VALID_DEFAULTS_PATH = "/com/devricks/civilwargeneral/default-orders.json";
    private static final String MISSING_PATH = "/com/devricks/civilwargeneral/does-not-exist.json";

    @Mock
    Orders orders;

    @Test
    void loadDefaults_returnsNonNull_and_nonEmpty_whenDefaultsExist() throws Exception {
        // Given a real loader using the classpath defaults (no factory override)
        // This test assumes your real defaults file exists and is non-empty.
        OrdersLoader loader = new OrdersLoaderImplementation();

        Orders result = loader.loadDefaults();

        assertNotNull(result, "Orders should not be null");
        assertNotNull(result.getAllOrders(), "Orders list should not be null");
        assertFalse(result.getAllOrders().isEmpty(), "Defaults should contain at least one order");
    }

    @Test
    void loadDefaults_throws_whenResourceMissing() {
        // Drive: a constructor that accepts a custom path and a factory
        Supplier<Orders> factory = () -> orders; // will not be used because resource lookup fails first
        OrdersLoaderImplementation loader = new OrdersLoaderImplementation(MISSING_PATH, factory);

        OrdersLoadException ex = assertThrows(OrdersLoadException.class, loader::loadDefaults);
        assertTrue(ex.getMessage().contains(MISSING_PATH), "Error message should include the missing path");
    }

    @Test
    void loadDefaults_rethrows_OrdersLoadException_from_Orders_load() throws Exception {
        // Given a valid resource path but Orders.loadFromFile throws OrdersLoadException
        // Orders.loadFromFile does not declare a checked exception; in production it wraps
        // failures into a RuntimeException. Simulate that by wrapping OrdersLoadException
        // as the cause of a RuntimeException so the loader can unwrap and rethrow it.
        doThrow(new RuntimeException(new OrdersLoadException("boom")))
                .when(orders).loadFromFile(VALID_DEFAULTS_PATH);

        OrdersLoaderImplementation loader = new OrdersLoaderImplementation(VALID_DEFAULTS_PATH, () -> orders);

        OrdersLoadException ex = assertThrows(OrdersLoadException.class, loader::loadDefaults);
        assertEquals("boom", ex.getMessage()); // original message preserved
        // Verify interaction order: resource check happens before loadFromFile
        InOrder inOrder = inOrder(orders);
        // We can only verify loadFromFile; resource existence is internal to loader
        inOrder.verify(orders).loadFromFile(VALID_DEFAULTS_PATH);
    }

    @Test
    void loadDefaults_wraps_unexpected_runtime_exceptions() throws Exception {
        doThrow(new RuntimeException("bad json state"))
                .when(orders).loadFromFile(VALID_DEFAULTS_PATH);

        OrdersLoaderImplementation loader = new OrdersLoaderImplementation(VALID_DEFAULTS_PATH, () -> orders);

        OrdersLoadException ex = assertThrows(OrdersLoadException.class, loader::loadDefaults);
        assertTrue(ex.getMessage().toLowerCase().contains("failed"));
        assertNotNull(ex.getCause());
        assertEquals("bad json state", ex.getCause().getMessage());
    }

    @Test
    void loadDefaults_uses_single_orders_instance_and_correct_path() throws Exception {
        OrdersLoaderImplementation loader = new OrdersLoaderImplementation(VALID_DEFAULTS_PATH, () -> orders);

        // When: call load
        // And: stub to simulate a non-empty result so presenter would enable the button
        // Not needed for this behavior verification; avoiding unnecessary stubbing

        Orders result = loader.loadDefaults();

        assertSame(orders, result, "Should return the same Orders instance created by the factory");
        verify(orders, times(1)).loadFromFile(VALID_DEFAULTS_PATH);
        verifyNoMoreInteractions(orders);
    }
}
