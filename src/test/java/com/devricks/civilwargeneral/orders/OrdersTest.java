package com.devricks.civilwargeneral.orders;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrdersTest {

    @Test
    void addOrder() {
        Orders orders = new Orders();
        Order order = new Order("Advance", "Move forward", 1);

        orders.addOrder(order);

        assertEquals(1, orders.getAllOrders().size());
        assertEquals(order, orders.getAllOrders().get(0));
    }

    @Test
    void removeOrderById() {
        Orders orders = new Orders();
        orders.addOrder(new Order("A", "D", 1));
        orders.addOrder(new Order("B", "D", 2));

        orders.removeOrderById(1);

        assertEquals(1, orders.getAllOrders().size());
        assertEquals(2, orders.getAllOrders().get(0).getId());
    }

    @Test
    void getOrderById() {
        Orders orders = new Orders();
        Order order = new Order("Advance", "Move", 10);
        orders.addOrder(order);

        assertTrue(orders.getOrderById(10).isPresent());
        assertEquals(order, orders.getOrderById(10).get());
        assertTrue(orders.getOrderById(99).isEmpty());
    }

    @Test
    void getAllOrders() {
        Orders orders = new Orders();
        orders.addOrder(new Order("A", "D", 1));

        var copy = orders.getAllOrders();
        assertEquals(1, copy.size());

        // Mutating the returned list should not affect the internal list
        copy.clear();
        assertEquals(1, orders.getAllOrders().size());
    }

    @Test
    void loadFromFile() {
        Orders orders = new Orders();
        orders.loadFromFile("/com/devricks/civilwargeneral/default-orders.json");

        assertNotNull(orders.getAllOrders());
        assertFalse(orders.getAllOrders().isEmpty(), "Default orders should load and be non-empty");
        // Spot-check first element fields from resource file
        Order first = orders.getAllOrders().get(0);
        assertTrue(first.getId() > 0);
        assertNotNull(first.getName());
        assertNotNull(first.getDescription());
    }

    @Test
    void saveToFile() {
        Orders orders = new Orders();
        orders.addOrder(new Order("One", "Desc1", 1));
        orders.addOrder(new Order("Two", "Desc2", 2));

        try {
            java.nio.file.Path temp = java.nio.file.Files.createTempFile("orders-test", ".json");
            try {
                orders.saveToFile(temp.toString());

                // Read file directly with Jackson to verify contents
                var mapper = new com.fasterxml.jackson.databind.ObjectMapper();
                java.util.List<Order> list = mapper.readValue(temp.toFile(), new com.fasterxml.jackson.core.type.TypeReference<java.util.List<Order>>() {});
                assertEquals(2, list.size());
                assertEquals(1, list.get(0).getId());
                assertEquals(2, list.get(1).getId());
            } finally {
                java.nio.file.Files.deleteIfExists(temp);
            }
        } catch (Exception e) {
            fail("Unexpected exception in saveToFile test: " + e.getMessage());
        }
    }
}