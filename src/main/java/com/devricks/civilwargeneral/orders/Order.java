package com.devricks.civilwargeneral.orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;

/**
 * Data model representing a tactical military command.
 * Contains the name, description, and unique identifier for an order.
 */
public class Order {
    private String name;
    private String description;
    private int id;

    /**
     * Constructs a new Order with specified details.
     *
     * @param name the name of the order
     * @param description the detailed description of the order
     * @param id the unique identifier for the order
     */
    public Order(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    /**
     * Default constructor for Jackson JSON deserialization.
     */
    public Order() {
    }

    /**
     * Returns the name of the order.
     *
     * @return the order name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the order.
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the description of the order.
     *
     * @return the order description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the order.
     *
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the unique identifier for the order.
     *
     * @return the order ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique identifier for the order.
     *
     * @param id the ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Validates if the order has all required fields correctly populated.
     * An order is valid if it has a non-null, non-empty name and description, and a positive ID.
     *
     * @return true if valid, false otherwise
     */
    @JsonIgnore
    public boolean isValid() {
        return name != null && !name.isEmpty() && description != null && !description.isEmpty() && id > 0;
    }

    @Override
    public String toString() {
        return "Order{" +
               "name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", id=" + id +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
               Objects.equals(name, order.name) &&
               Objects.equals(description, order.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id);
    }
}
