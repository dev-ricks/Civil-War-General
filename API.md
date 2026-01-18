# Civil War General API Documentation

This document provides comprehensive technical documentation for the Civil War General API, including all public classes, methods, and interfaces available for developers working with or extending the system.

## Table of Contents

- [Overview](#overview)
- [Core Data Models](#core-data-models)
- [AI Components](#ai-components)
- [UI Controllers](#ui-controllers)
- [JSON Schema](#json-schema)
- [Usage Examples](#usage-examples)
- [Extension Points](#extension-points)
- [Error Handling](#error-handling)

---

## Overview

The Civil War General API is organized into several key packages following the MVC (Model-View-Controller) architectural pattern:

- **`com.devricks.civilwargeneral.orders`** - Data models for military orders
- **`com.devricks.civilwargeneral.ai`** - Command selection and AI logic
- **`com.devricks.civilwargeneral.controllers`** - UI controllers and event handling
- **`com.devricks.civilwargeneral`** - Main application classes

---

## Core Data Models

### Order Class

**Package**: `com.devricks.civilwargeneral.orders`

Represents a single military tactical order with validation capabilities.

#### Constructor

```java
public Order()
public Order(String name, String description, int id)
```

#### Public Methods

| Method | Return Type | Description |
|--------|-------------|-------------|
| `getName()` | `String` | Gets the order name |
| `setName(String name)` | `void` | Sets the order name |
| `getDescription()` | `String` | Gets the order description |
| `setDescription(String description)` | `void` | Sets the order description |
| `getId()` | `int` | Gets the unique order identifier |
| `setId(int id)` | `void` | Sets the unique order identifier |
| `isValid()` | `boolean` | Validates order completeness |
| `toString()` | `String` | Returns formatted order representation |
| `equals(Object o)` | `boolean` | Compares orders for equality |
| `hashCode()` | `int` | Returns hash code for the order |

#### Validation Rules

- **Name**: Must be non-null and non-empty string
- **Description**: Must be non-null and non-empty string  
- **ID**: Must be positive integer (> 0)

#### Example Usage

```java
Order order = new Order("Attack Forward", "Direct frontal assault", 1);
if (order.isValid()) {
    System.out.println("Order is valid: " + order.toString());
}
```

---

### Orders Class

**Package**: `com.devricks.civilwargeneral.orders`

Manages collections of military orders with JSON persistence capabilities.

#### Constructor

```java
public Orders()
public Orders(List<Order> orders)
```

#### Public Methods

| Method | Return Type | Description |
|--------|-------------|-------------|
| `addOrder(Order order)` | `void` | Adds an order to the collection |
| `removeOrderById(int id)` | `void` | Removes order by unique identifier |
| `getOrderById(int id)` | `Optional<Order>` | Retrieves order by ID |
| `getAllOrders()` | `List<Order>` | Returns copy of all orders |
| `loadFromFile(String filePath)` | `void` | Loads orders from JSON file |
| `saveToFile(String filePath)` | `void` | Saves orders to JSON file |

#### File Operations

- **Load**: Reads JSON array from classpath resources
- **Save**: Writes JSON array to file system
- **Validation**: Automatically validates loaded orders
- **Error Handling**: Throws `RuntimeException` for file operations

#### Example Usage

```java
Orders orders = new Orders();
orders.loadFromFile("/com/devricks/civilwargeneral/default-orders.json");

Order newOrder = new Order("Cavalry Charge", "Mounted assault", 5);
orders.addOrder(newOrder);

Optional<Order> found = orders.getOrderById(1);
if (found.isPresent()) {
    System.out.println("Found: " + found.get().getName());
}
```

---

## AI Components

### CommandSelector Class

**Package**: `com.devricks.civilwargeneral.ai`

Provides intelligent selection algorithms for tactical orders.

#### Public Methods

| Method | Return Type | Description |
|--------|-------------|-------------|
| `randomOrderSelector(Orders orders)` | `Order` | Randomly selects valid order |

#### Algorithm Details

- **Random Generation**: Uses `SecureRandom` for cryptographically secure selection
- **Validation**: Ensures selected order is valid before return
- **Error Handling**: Returns `null` for invalid inputs or empty collections
- **Distribution**: Provides equal probability for all valid orders

#### Example Usage

```java
Orders orders = new Orders();
orders.loadFromFile("/path/to/orders.json");

CommandSelector selector = new CommandSelector();
Order selectedOrder = selector.randomOrderSelector(orders);

if (selectedOrder != null) {
    System.out.println("Selected: " + selectedOrder.getName());
} else {
    System.out.println("No valid order found");
}
```

#### Future Extension Points

The `CommandSelector` class is designed for future AI enhancements:

```java
// Future Stage 2 methods (planned)
public Order aiBasedSelector(Orders orders, BattlefieldConditions conditions)
public Order contextualSelector(Orders orders, TroopState troops, SupplyState supply)
public Order strategicSelector(Orders orders, GeneralAttributes general)
```

---

## UI Controllers

### MainViewUI Interface

**Package**: `com.devricks.civilwargeneral.controllers`

Defines the contract for the Main View, allowing the Presenter to update the UI without direct dependency on JavaFX components.

| Method | Return Type | Description |
|--------|-------------|-------------|
| `clearList()` | `void` | Clears the command history list |
| `setGeneratedEnabled(boolean)` | `void` | Enables/disables the generate button |
| `addOrder(Order)` | `void` | Adds a selected order to the list |

---

### MainViewPresenter Class

**Package**: `com.devricks.civilwargeneral.controllers`

Coordinates logic between the data models and the UI.

#### Constructor

```java
public MainViewPresenter(MainViewUI ui, OrdersLoader loader, CommandSelector selector)
```

#### Public Methods

| Method | Return Type | Description |
|--------|-------------|-------------|
| `initialize()` | `void` | Loads default orders and sets initial UI state |
| `onGenerateClicked()` | `void` | Handles command generation logic |

---

### MainView Class (View Implementation)

**Package**: `com.devricks.civilwargeneral.controllers`

JavaFX controller implementing the `MainViewUI` interface.

#### FXML Components

| Component | Type | fx:id | Description |
|-----------|------|-------|-------------|
| Generate Button | `Button` | `btnGenerateCommand` | Triggers command selection |
| Command List | `ListView<Order>` | `commandList` | Displays order history |

#### Event Handling

```java
@FXML
public void onCommandButtonClick(ActionEvent event) {
    if (presenter != null) {
        presenter.onGenerateClicked();
    }
}
```

#### Example Integration

```xml
<!-- FXML Integration -->
<Button fx:id="btnGenerateCommand" 
        onAction="#onCommandButtonClick" 
        text="Generate Command"/>
<ListView fx:id="commandList" 
          prefHeight="200.0" 
          prefWidth="500.0"/>
```

---

## JSON Schema

### Order JSON Format

The system uses the following JSON schema for order persistence:

```json
{
  "$schema": "http://json-schema.org/draft-07/schema#",
  "type": "array",
  "items": {
    "type": "object",
    "properties": {
      "name": {
        "type": "string",
        "minLength": 1,
        "description": "The tactical order name"
      },
      "description": {
        "type": "string", 
        "minLength": 1,
        "description": "Detailed tactical description"
      },
      "id": {
        "type": "integer",
        "minimum": 1,
        "description": "Unique order identifier"
      }
    },
    "required": ["name", "description", "id"],
    "additionalProperties": false
  }
}
```

### Example JSON File

```json
[
  {
    "name": "Attack Forward",
    "description": "Take your unit and attack in force in a forward direction.",
    "id": 1
  },
  {
    "name": "Attack Flank", 
    "description": "Take your unit and attack in force on a flank of the enemy.",
    "id": 2
  },
  {
    "name": "Defend Forward",
    "description": "Take your unit, prepare defenses to the forward direction.", 
    "id": 3
  },
  {
    "name": "Retreat Backward",
    "description": "Take your unit retreat hastily to the backward direction.",
    "id": 4
  }
]
```

---

## Usage Examples

### Basic Order Management

```java
// Create and validate orders
Order order = new Order("Cavalry Charge", "Mounted unit assault", 5);
if (!order.isValid()) {
    throw new IllegalArgumentException("Invalid order configuration");
}

// Manage order collections
Orders orderCollection = new Orders();
orderCollection.addOrder(order);
orderCollection.saveToFile("custom-orders.json");
```

### Command Selection Workflow

```java
// Load orders from configuration
Orders orders = new Orders();
try {
    orders.loadFromFile("/com/devricks/civilwargeneral/default-orders.json");
} catch (RuntimeException e) {
    System.err.println("Failed to load orders: " + e.getMessage());
    return;
}

// Generate commands
CommandSelector selector = new CommandSelector();
for (int i = 0; i < 5; i++) {
    Order command = selector.randomOrderSelector(orders);
    if (command != null) {
        System.out.println("Command " + (i+1) + ": " + command.getName());
    }
}
```

### UI Integration

```java
public class MainView implements MainViewUI {
    @FXML private Button btnGenerateCommand;
    @FXML private ListView<Order> commandList;
    private MainViewPresenter presenter;
    
    @FXML
    public void onCommandButtonClick(ActionEvent event) {
        if (presenter != null) {
            presenter.onGenerateClicked();
        }
    }
}
```

---

## Extension Points

### Future AI Integration

The current API is designed to support future AI enhancements:

#### Battlefield Conditions (Stage 2)

```java
// Planned interface for Stage 2
public interface BattlefieldConditions {
    WeatherCondition getWeather();
    TerrainType getTerrain();
    TimeOfDay getTime();
    EnemyPosition getEnemyPositions();
}
```

#### Troop State Management (Stage 2)

```java
// Planned interface for Stage 2  
public interface TroopState {
    int getMoraleLevel();
    int getStrength();
    int getFatigue();
    EquipmentState getEquipment();
}
```

#### Supply Situation (Stage 2)

```java
// Planned interface for Stage 2
public interface SupplyState {
    int getAmmunitionLevel();
    int getFoodSupplies();
    int getMedicalSupplies();
    TransportCapacity getTransport();
}
```

### Custom Order Types

Extend the `Order` class for specialized order types:

```java
public class CavalryOrder extends Order {
    private int horseCount;
    private CavalryFormation formation;
    
    // Additional cavalry-specific methods
    public boolean requiresHorses() { return true; }
    public int getMinimumHorseCount() { return horseCount; }
}
```

### Custom Selection Algorithms

Implement custom selection logic:

```java
public class HistoricalAccuracySelector extends CommandSelector {
    @Override
    public Order randomOrderSelector(Orders orders) {
        // Filter orders by historical period
        List<Order> historicalOrders = filterByPeriod(orders.getAllOrders());
        return selectFromFiltered(historicalOrders);
    }
}
```

---

## Error Handling

### Exception Types

| Exception | Cause | Handling |
|-----------|-------|----------|
| `RuntimeException` | File I/O operations | Catch and provide user feedback |
| `IllegalArgumentException` | Invalid file paths | Validate paths before use |
| `NullPointerException` | Null order collections | Check for null before operations |

### Best Practices

```java
// Proper error handling example
try {
    Orders orders = new Orders();
    orders.loadFromFile(configPath);
    
    CommandSelector selector = new CommandSelector();
    Order result = selector.randomOrderSelector(orders);
    
    if (result == null) {
        logger.warn("No valid orders available for selection");
        showUserMessage("No commands available");
    } else {
        processOrder(result);
    }
    
} catch (RuntimeException e) {
    logger.error("Failed to load order configuration", e);
    showErrorDialog("Configuration Error", e.getMessage());
}
```

### Validation Guidelines

```java
// Always validate before processing
public void processOrder(Order order) {
    if (order == null) {
        throw new IllegalArgumentException("Order cannot be null");
    }
    
    if (!order.isValid()) {
        throw new IllegalArgumentException("Order validation failed: " + order);
    }
    
    // Process valid order
    executeOrder(order);
}
```

---

## API Versioning

The Civil War General API follows semantic versioning:

- **Major Version**: Breaking API changes
- **Minor Version**: New features, backward compatible
- **Patch Version**: Bug fixes, backward compatible

### Current Version: 0.1.0

- Initial API release
- Basic order management
- Random command selection
- JavaFX UI integration

### Planned Version: 1.0.0 (Stage 1 Complete)

- Comprehensive testing coverage
- Performance optimizations
- Enhanced error handling
- API stability guarantees

### Planned Version: 2.0.0 (Stage 2)

- AI-based selection algorithms
- Battlefield condition integration
- Extended order attributes
- Breaking changes for AI support

---

*This API documentation is maintained alongside the codebase and updated with each release. For the most current information, refer to the JavaDoc comments in the source code.*
