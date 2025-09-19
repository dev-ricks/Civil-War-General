# Civil War General - System Architecture

This document provides comprehensive architectural documentation for the Civil War General system, including design patterns, component interactions, and evolutionary architecture supporting the staged development approach from random command selection to AI-driven tactical decision-making.

## Table of Contents

- [Architectural Overview](#architectural-overview)
- [Design Principles](#design-principles)
- [System Components](#system-components)
- [Data Flow Architecture](#data-flow-architecture)
- [Design Patterns](#design-patterns)
- [Technology Stack](#technology-stack)
- [Stage Evolution Architecture](#stage-evolution-architecture)
- [Security Architecture](#security-architecture)
- [Performance Architecture](#performance-architecture)
- [Deployment Architecture](#deployment-architecture)

---

## Architectural Overview

Civil War General follows a **layered architecture** based on the **Model-View-Controller (MVC)** pattern, designed for evolutionary development across multiple stages. The architecture prioritizes extensibility, maintainability, and clear separation of concerns.

### High-Level Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION LAYER                       │
├─────────────────────────────────────────────────────────────┤
│  JavaFX UI Components  │  FXML Layouts  │  CSS Styling     │
│  - MainView.fxml       │  - Controllers │  - Themes        │
│  - Dialog Components   │  - Event Handlers                 │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                    CONTROLLER LAYER                         │
├─────────────────────────────────────────────────────────────┤
│  UI Controllers        │  Event Coordination                │
│  - MainView            │  - User Input Validation          │
│  - DialogControllers   │  - UI State Management            │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                     SERVICE LAYER                           │
├─────────────────────────────────────────────────────────────┤
│  Business Logic        │  AI Components                     │
│  - CommandSelector     │  - Future: BattlefieldAnalyzer    │
│  - OrderValidator      │  - Future: TacticalAI             │
│  - ConfigurationMgr    │  - Future: HistoricalAccuracy     │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                      MODEL LAYER                            │
├─────────────────────────────────────────────────────────────┤
│  Data Models           │  Domain Objects                    │
│  - Order               │  - Future: BattlefieldConditions  │
│  - Orders              │  - Future: TroopState             │
│  - Configuration       │  - Future: SupplyState            │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                   PERSISTENCE LAYER                         │
├─────────────────────────────────────────────────────────────┤
│  Data Access           │  File I/O                          │
│  - JSON Serialization  │  - Configuration Files            │
│  - Resource Loading    │  - Future: Database Integration   │
└─────────────────────────────────────────────────────────────┘
```

---

## Design Principles

### 1. **Separation of Concerns**
Each layer has distinct responsibilities:
- **Presentation**: User interface and user experience
- **Controller**: User input handling and UI coordination
- **Service**: Business logic and algorithms
- **Model**: Data representation and validation
- **Persistence**: Data storage and retrieval

### 2. **Extensibility First**
Architecture designed for future AI integration:
- Plugin-based selection algorithms
- Extensible data models
- Configurable decision-making pipelines
- Modular component design

### 3. **Loose Coupling**
Components interact through well-defined interfaces:
- Dependency injection ready
- Interface-based programming
- Event-driven communication
- Minimal direct dependencies

### 4. **High Cohesion**
Related functionality grouped together:
- Package organization by domain
- Single responsibility principle
- Clear module boundaries
- Focused class design

### 5. **Testability**
Architecture supports comprehensive testing:
- Mockable dependencies
- Isolated components
- Clear input/output contracts
- Deterministic behavior

---

## System Components

### Core Components Diagram

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   CivilWarGeneral   │    │   MainView      │    │  CommandSelector │
│   (Application)     │    │   (Controller)  │    │   (AI Service)   │
│                     │    │                 │    │                  │
│ + main()            │    │ + initialize()  │    │ + randomSelector()│
│ + start()           │    │ + onButtonClick()│    │ + validateOrder() │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         │                       │                       │
         ▼                       ▼                       ▼
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│     Orders      │    │     Order       │    │  Configuration  │
│  (Collection)   │    │   (Entity)      │    │   (Settings)    │
│                 │    │                 │    │                 │
│ + loadFromFile()│    │ + isValid()     │    │ + loadDefaults()│
│ + addOrder()    │    │ + getName()     │    │ + getProperty() │
│ + getAllOrders()│    │ + getDescription()   │ + setProperty() │
└─────────────────┘    └─────────────────┘    └─────────────────┘
```

### Component Responsibilities

| Component | Layer | Responsibility | Future Evolution |
|-----------|-------|----------------|------------------|
| `CivilWarGeneral` | Presentation | Application lifecycle | Multi-window support |
| `MainView` | Controller | UI event handling | Advanced UI controls |
| `CommandSelector` | Service | Order selection logic | AI-based algorithms |
| `Orders` | Model | Order collection management | Advanced querying |
| `Order` | Model | Order data and validation | Extended attributes |
| `Configuration` | Persistence | Settings management | Database integration |

---

## Data Flow Architecture

### Current Data Flow (Stage 1)

```
User Input → UI Controller → Service Layer → Data Model → JSON File
    ↓             ↓              ↓             ↓           ↓
[Button Click] → [Event Handler] → [Random Selection] → [Order Object] → [File I/O]
    ↑             ↑              ↑             ↑           ↑
User Display ← UI Update ← Service Response ← Data Validation ← File Loading
```

### Detailed Flow Sequence

```
1. Application Startup
   ├── CivilWarGeneral.main()
   ├── JavaFX Application.launch()
   ├── MainView.initialize()
   ├── Orders.loadFromFile()
   └── UI Display Ready

2. Command Generation
   ├── User clicks "Generate Command"
   ├── MainView.onCommandButtonClick()
   ├── CommandSelector.randomOrderSelector()
   ├── Orders.getAllOrders()
   ├── SecureRandom selection
   ├── Order validation
   ├── UI ListView update
   └── Command displayed

3. Error Handling
   ├── File loading errors
   ├── Validation failures
   ├── UI error display
   └── Graceful degradation
```

### Future Data Flow (Stage 2+)

```
Battlefield Context → AI Analysis → Tactical Decision → Order Selection → Execution
        ↓                ↓              ↓               ↓              ↓
[Weather, Terrain] → [ML Algorithm] → [Strategy Choice] → [Order Object] → [Unit Commands]
        ↑                ↑              ↑               ↑              ↑
Historical Data ← Learning System ← Feedback Loop ← Performance Metrics ← Battle Results
```

---

## Design Patterns

### 1. **Model-View-Controller (MVC)**
**Implementation**: Clear separation between data, presentation, and control logic
```java
// Model
public class Order { /* Data and validation */ }

// View  
public class MainView.fxml { /* UI layout */ }

// Controller
public class MainView { /* Event handling */ }
```

### 2. **Strategy Pattern** (Future)
**Purpose**: Pluggable selection algorithms
```java
public interface SelectionStrategy {
    Order selectOrder(Orders orders, Context context);
}

public class RandomStrategy implements SelectionStrategy { /* Current */ }
public class AIStrategy implements SelectionStrategy { /* Future */ }
public class HistoricalStrategy implements SelectionStrategy { /* Future */ }
```

### 3. **Observer Pattern** (Future)
**Purpose**: Event-driven battlefield updates
```java
public interface BattlefieldObserver {
    void onConditionChange(BattlefieldEvent event);
}

public class TacticalAI implements BattlefieldObserver { /* Future */ }
```

### 4. **Factory Pattern** (Future)
**Purpose**: Order creation based on context
```java
public class OrderFactory {
    public static Order createOrder(OrderType type, Context context) {
        // Dynamic order creation based on battlefield conditions
    }
}
```

### 5. **Command Pattern** (Future)
**Purpose**: Executable military orders
```java
public interface ExecutableOrder {
    void execute(Unit unit);
    void undo();
    boolean canExecute(Unit unit);
}
```

---

## Technology Stack

### Current Technology Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    PRESENTATION TIER                        │
├─────────────────────────────────────────────────────────────┤
│  JavaFX 17.0.12    │  FXML Layouts    │  CSS Styling       │
│  ControlsFX 11.2.1 │  FormsFX 11.6.0  │  Scene Builder     │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                    APPLICATION TIER                         │
├─────────────────────────────────────────────────────────────┤
│  Java 17+          │  Maven 3.6+      │  JUnit 5.10.2      │
│  Jackson 2.x       │  SLF4J Logging   │  Mockito Testing   │
└─────────────────────────────────────────────────────────────┘
                                │
                                ▼
┌─────────────────────────────────────────────────────────────┐
│                      DATA TIER                              │
├─────────────────────────────────────────────────────────────┤
│  JSON Files        │  Resource Loading │  File I/O          │
│  Jackson Parsing   │  Configuration    │  Future: Database  │
└─────────────────────────────────────────────────────────────┘
```

### Technology Decisions

| Technology | Purpose | Rationale | Future Evolution |
|------------|---------|-----------|------------------|
| **JavaFX** | Desktop UI | Rich desktop experience, FXML support | WebView integration |
| **Maven** | Build system | Dependency management, standard lifecycle | Multi-module builds |
| **Jackson** | JSON processing | Robust serialization, annotation support | XML/YAML support |
| **JUnit 5** | Testing framework | Modern testing features, parameterized tests | Integration testing |
| **SLF4J** | Logging | Flexible logging facade | Structured logging |

---

## Stage Evolution Architecture

### Stage 1: Foundation (Current)
```
Simple Architecture
├── Random Selection
├── Basic UI
├── JSON Configuration
└── Manual Testing
```

### Stage 2: Intelligence (Planned)
```
Enhanced Architecture
├── AI Decision Engine
│   ├── Battlefield Analysis
│   ├── Troop Assessment
│   └── Supply Evaluation
├── Context-Aware Selection
├── Machine Learning Integration
└── Automated Testing
```

### Stage 3: Simulation (Future)
```
Complex Architecture
├── Multi-Unit Coordination
├── Real-Time Simulation
├── Network Communication
├── Advanced AI Opponents
└── Performance Optimization
```

### Stage 4: Education (Vision)
```
Platform Architecture
├── Learning Management
├── Historical Accuracy
├── Assessment Tools
├── Content Management
└── Analytics Dashboard
```

### Architectural Evolution Strategy

1. **Incremental Enhancement**
   - Maintain backward compatibility
   - Add new interfaces without breaking existing code
   - Gradual migration of components

2. **Plugin Architecture**
   - Modular selection algorithms
   - Configurable AI components
   - Extensible data models

3. **Service-Oriented Design**
   - Microservice preparation
   - API-first development
   - Scalable component design

---

## Security Architecture

### Current Security Measures

1. **Input Validation**
   - Order data validation
   - File path sanitization
   - JSON schema validation

2. **Secure Random Generation**
   - `SecureRandom` for order selection
   - Cryptographically secure algorithms
   - Unpredictable command generation

3. **Resource Protection**
   - Classpath resource loading
   - Controlled file access
   - Configuration validation

### Future Security Enhancements

1. **Authentication & Authorization**
   - User management system
   - Role-based access control
   - Session management

2. **Data Protection**
   - Encrypted configuration files
   - Secure communication protocols
   - Data integrity validation

3. **Audit & Monitoring**
   - Command execution logging
   - Security event tracking
   - Performance monitoring

---

## Performance Architecture

### Current Performance Characteristics

| Operation | Target Performance | Current Implementation |
|-----------|-------------------|----------------------|
| Order Selection | < 100ms | SecureRandom O(1) |
| File Loading | < 5s | Jackson streaming |
| UI Responsiveness | 60 FPS | JavaFX threading |
| Memory Usage | < 512MB | Lightweight objects |

### Performance Optimization Strategy

1. **Caching Strategy**
   - Order collection caching
   - Configuration caching
   - UI component reuse

2. **Lazy Loading**
   - On-demand resource loading
   - Progressive UI initialization
   - Deferred computation

3. **Asynchronous Processing**
   - Background file operations
   - Non-blocking UI updates
   - Concurrent order processing

### Future Performance Considerations

1. **AI Processing**
   - GPU acceleration for ML
   - Distributed computing
   - Real-time decision making

2. **Simulation Performance**
   - Multi-threading for units
   - Spatial partitioning
   - Level-of-detail optimization

---

## Deployment Architecture

### Current Deployment Model

```
Standalone Desktop Application
├── Single JAR distribution
├── Embedded dependencies
├── Local file system
└── Platform-specific launchers
```

### Development Environment

```
Developer Workstation
├── Java 17+ JDK
├── Maven 3.6+
├── IDE (IntelliJ IDEA recommended)
├── Git version control
└── Local testing environment
```

### Future Deployment Options

1. **Enhanced Desktop**
   - Native installers (MSI, DMG, DEB)
   - Auto-update mechanisms
   - Platform integration

2. **Cloud-Enabled**
   - Configuration synchronization
   - Online leaderboards
   - Multiplayer coordination

3. **Educational Platform**
   - Web-based deployment
   - Institutional integration
   - Content management system

---

## Architectural Decision Records (ADRs)

### ADR-001: MVC Architecture Choice
**Decision**: Adopt Model-View-Controller pattern
**Rationale**: Clear separation of concerns, testability, maintainability
**Consequences**: More classes, but better organization and extensibility

### ADR-002: JavaFX for Desktop UI
**Decision**: Use JavaFX instead of Swing or web technologies
**Rationale**: Modern UI capabilities, FXML support, CSS styling
**Consequences**: Java 17+ requirement, but rich user experience

### ADR-003: JSON for Configuration
**Decision**: Use JSON instead of XML or properties files
**Rationale**: Human-readable, widely supported, schema validation
**Consequences**: Jackson dependency, but flexible data structure

### ADR-004: Extensible AI Architecture
**Decision**: Design for future AI integration from Stage 1
**Rationale**: Avoid major refactoring in later stages
**Consequences**: More complex initial design, but smooth evolution

---

## Quality Attributes

### Maintainability
- **Modular Design**: Clear package structure and component boundaries
- **Documentation**: Comprehensive inline and external documentation
- **Testing**: Unit tests with high coverage, integration tests
- **Code Standards**: Consistent formatting and naming conventions

### Extensibility
- **Plugin Architecture**: Support for new selection algorithms
- **Interface-Based Design**: Easy to add new implementations
- **Configuration-Driven**: Behavior modification without code changes
- **Event-Driven**: Loose coupling through event mechanisms

### Performance
- **Efficient Algorithms**: O(1) selection, optimized data structures
- **Resource Management**: Proper memory and file handle management
- **Caching**: Strategic caching of frequently accessed data
- **Asynchronous Operations**: Non-blocking UI and background processing

### Usability
- **Intuitive Interface**: Clear, military-themed UI design
- **Responsive Design**: Immediate feedback for user actions
- **Error Handling**: Graceful error recovery and user messaging
- **Accessibility**: Keyboard navigation and screen reader support

---

*This architecture document evolves with the system and is maintained to reflect current design decisions and future architectural vision. Regular reviews ensure alignment with project goals and technological advances.*
