# Civil War General

A JavaFX-based Civil War military command simulation application that provides tactical order generation and decision-making tools for military history enthusiasts, educators, and game developers.

![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![JavaFX](https://img.shields.io/badge/JavaFX-17.0.12-blue.svg)
![Maven](https://img.shields.io/badge/Maven-3.6+-green.svg)
![License](https://img.shields.io/badge/License-MIT-yellow.svg)

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Screenshots](#screenshots)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [Development](#development)
- [Testing](#testing)
- [Contributing](#contributing)
- [Roadmap](#roadmap)
- [License](#license)

## 🎯 Overview

Civil War General is a desktop application that simulates military command decision-making during the American Civil War period. The application currently focuses on **Stage 1** functionality: a command selection tool that randomly generates tactical orders from a predefined set of Civil War era military commands.

### Vision

The long-term vision includes AI-driven tactical decision-making based on:
- General attributes and experience
- Real-time battlefield conditions
- Troop state and morale
- Supply situation and logistics
- Historical accuracy and tactical doctrine

## ✨ Features

### Current Features (Stage 1)
- **Random Command Generation**: Intelligent selection from predefined tactical orders
- **Order Management**: Load, validate, and manage military commands from JSON files
- **Interactive UI**: Clean, military-themed JavaFX interface
- **Order History**: Session-based tracking of generated commands
- **Data Validation**: Comprehensive order integrity checking
- **Extensible Architecture**: Designed for future AI integration

### Default Tactical Orders
1. **Attack Forward** - Direct frontal assault tactics
2. **Attack Flank** - Flanking maneuver operations  
3. **Defend Forward** - Defensive positioning strategies
4. **Retreat Backward** - Tactical withdrawal procedures

## 🖼️ Screenshots

*Screenshots will be added as the UI is developed and refined.*

## 🚀 Installation

### Prerequisites
- **Java Development Kit (JDK) 17 or higher**
- **Maven 3.6 or higher**
- **Git** (for cloning the repository)

### Quick Start

1. **Clone the repository**
   ```bash
   git clone https://github.com/dev-ricks/Civil-War-General.git
   cd Civil-War-General
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run the application**
   ```bash
   mvn javafx:run
   ```

### Alternative: Build Executable JAR

```bash
mvn clean package
java -jar target/Civil-War-General-1.0-SNAPSHOT.jar
```

## 🎮 Usage

### Basic Operation

1. **Launch the Application**
   - Run the application using Maven or the executable JAR
   - The main window (800x600) will appear with the Civil War General interface

2. **Generate Commands**
   - Click the "Generate Command" button to randomly select a tactical order
   - Generated orders appear in the command history list
   - Each order displays the command name and detailed description

3. **View Command History**
   - All generated commands for the current session are displayed in the scrollable list
   - Orders include tactical details and strategic context

### Configuration

The application loads default orders from `src/main/resources/com/devricks/civilwargeneral/default-orders.json`. You can modify this file to add custom tactical orders following the JSON schema:

```json
{
  "name": "Command Name",
  "description": "Detailed tactical description",
  "id": 1
}
```

## 📁 Project Structure

```
Civil-War-General/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/devricks/civilwargeneral/
│   │   │       ├── CivilWarGeneral.java          # Main application entry
│   │   │       ├── HelloApplication.java         # Alternative entry point
│   │   │       ├── HelloController.java          # Demo controller
│   │   │       ├── ai/
│   │   │       │   └── CommandSelector.java      # Order selection logic
│   │   │       ├── controllers/
│   │   │       │   └── MainView.java             # Main UI controller
│   │   │       └── orders/
│   │   │           ├── Order.java                # Order data model
│   │   │           └── Orders.java               # Order collection manager
│   │   └── resources/
│   │       └── com/devricks/civilwargeneral/
│   │           ├── default-orders.json           # Default tactical orders
│   │           ├── main-view.fxml               # Main UI layout
│   │           └── hello-view.fxml              # Demo UI layout
│   └── test/
│       └── java/                                # Unit tests (to be implemented)
├── pom.xml                                      # Maven configuration
├── README.md                                    # This file
├── REQUIREMENTS_STAGE_1.md                     # Stage 1 requirements specification
├── CONTRIBUTING.md                              # Contribution guidelines
└── .gitignore                                   # Git ignore rules
```

## 🛠️ Development

### Technology Stack

- **Java 17+** - Core programming language
- **JavaFX 17.0.12** - Desktop UI framework
- **Maven** - Build automation and dependency management
- **Jackson** - JSON processing library
- **ControlsFX** - Enhanced UI controls
- **FormsFX** - Advanced form handling

### Architecture

The application follows the **Model-View-Controller (MVC)** pattern:

- **Model Layer**: `Order` and `Orders` classes for data management
- **View Layer**: FXML-based JavaFX user interfaces
- **Controller Layer**: UI controllers and business logic
- **Service Layer**: `CommandSelector` for order selection algorithms

### Building from Source

1. **Clone and navigate to the project**
   ```bash
   git clone https://github.com/dev-ricks/Civil-War-General.git
   cd Civil-War-General
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Run in development mode**
   ```bash
   mvn javafx:run
   ```

4. **Generate documentation**
   ```bash
   mvn javadoc:javadoc
   ```

### Code Style

- Follow standard Java naming conventions
- Use meaningful variable and method names
- Include comprehensive JavaDoc comments
- Maintain consistent indentation (4 spaces)
- Keep methods focused and concise

## 🧪 Testing

### Running Tests

```bash
# Run all tests
mvn test

# Run tests with coverage
mvn test jacoco:report

# Run specific test class
mvn test -Dtest=OrderTest
```

### Test Coverage Goals

- **Unit Tests**: 80%+ code coverage
- **Integration Tests**: End-to-end workflow validation
- **UI Tests**: User interaction and display verification

## 🤝 Contributing

We welcome contributions to the Civil War General project! Please see [CONTRIBUTING.md](CONTRIBUTING.md) for detailed guidelines.

### Quick Contribution Guide

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Setup

1. Ensure you have Java 17+ and Maven installed
2. Fork and clone the repository
3. Import the project into your IDE (IntelliJ IDEA recommended)
4. Run `mvn clean compile` to verify setup
5. Start developing!

## 🗺️ Roadmap

### Stage 1: Command Selection Tool ✅ *Current*
- [x] Random order selection from predefined sets
- [x] Basic JavaFX user interface
- [x] JSON-based order configuration
- [x] Order validation and management
- [ ] Comprehensive unit test suite
- [ ] User documentation and help system

### Stage 2: Enhanced Decision Making *Planned*
- [ ] AI-based command selection algorithms
- [ ] Battlefield condition analysis
- [ ] Troop state and morale factors
- [ ] Supply situation assessment
- [ ] Historical accuracy improvements

### Stage 3: Advanced Simulation *Future*
- [ ] Multi-unit command coordination
- [ ] Real-time battle simulation
- [ ] Historical scenario recreation
- [ ] Multiplayer support
- [ ] Advanced AI opponents

### Stage 4: Educational Platform *Vision*
- [ ] Interactive Civil War history lessons
- [ ] Tactical analysis tools
- [ ] Historical battle recreations
- [ ] Educational assessment features

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- Historical tactical references from Civil War military manuals
- JavaFX community for UI framework support
- Maven ecosystem for build automation
- Open source contributors and testers

## 📞 Support

- **Issues**: [GitHub Issues](https://github.com/dev-ricks/Civil-War-General/issues)
- **Discussions**: [GitHub Discussions](https://github.com/dev-ricks/Civil-War-General/discussions)
- **Documentation**: [Project Wiki](https://github.com/dev-ricks/Civil-War-General/wiki)

---

**Civil War General** - *Bringing tactical decision-making to the digital battlefield*

*Made with ⚔️ for military history enthusiasts and tactical simulation fans*
