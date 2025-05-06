package com.devricks.civilwargeneral.orders;

import java.util.Objects;

public class Order {
    private String name;
    private String description;
    private int id;

    public Order(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

    public Order() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
