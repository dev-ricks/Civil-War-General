package com.devricks.civilwargeneral.orders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.InputStream;
import java.io.File;
import java.util.*;

public class Orders {
    private List<Order> orders;

    public Orders() {
        this.orders = new ArrayList<>();
    }

    public Orders(List<Order> orders) {
        this.orders = new ArrayList<>(orders);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public void removeOrderById(int id) {
        orders.removeIf(order -> order.getId() == id);
    }

    public Optional<Order> getOrderById(int id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst();
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

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
