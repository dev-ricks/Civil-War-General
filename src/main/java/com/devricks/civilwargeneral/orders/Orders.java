package com.devricks.civilwargeneral.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.io.File;
import java.util.*;

/**
 * Collection management class for tactical military orders.
 * Handles adding, removing, searching, and persisting orders to/from JSON.
 */
public class Orders {
    private List<Order> orders;

    /**
     * Constructs an empty collection of orders.
     */
    public Orders() {
        this.orders = new ArrayList<>();
    }

    /**
     * Constructs a collection of orders with initial data.
     *
     * @param orders the initial list of orders
     */
    public Orders(List<Order> orders) {
        this.orders = new ArrayList<>(orders);
    }

    /**
     * Adds a single order to the collection.
     *
     * @param order the order to add
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Removes an order from the collection by its unique identifier.
     *
     * @param id the ID of the order to remove
     */
    public void removeOrderById(int id) {
        orders.removeIf(order -> order.getId() == id);
    }

    /**
     * Searches for an order by its unique identifier.
     *
     * @param id the ID of the order to find
     * @return an Optional containing the found Order, or empty if not found
     */
    public Optional<Order> getOrderById(int id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst();
    }

    /**
     * Returns a copy of all orders in the collection.
     *
     * @return a list of all orders
     */
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    /**
     * Loads orders from a JSON file located on the classpath.
     *
     * @param filePath the path to the JSON resource
     * @throws RuntimeException if the file is not found or cannot be parsed
     */
    public void loadFromFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream(filePath)) {
            if (inputStream == null) {
                throw new IllegalArgumentException("File not found: " + filePath);
            }
            this.orders = objectMapper.readValue(inputStream, new TypeReference<List<Order>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Failed to load orders from file: " + filePath, e);
        }
    }

    /**
     * Saves the current collection of orders to a local JSON file.
     *
     * @param filePath the path where the JSON file should be saved
     * @throws RuntimeException if the file cannot be written
     */
    public void saveToFile(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File(filePath);
            objectMapper.writeValue(file, orders);
        } catch (Exception e) {
            throw new RuntimeException("Failed to save orders to file: " + filePath, e);
        }
    }
}
