# Civil War General - Installation Guide

This comprehensive installation guide provides step-by-step instructions for setting up the Civil War General development environment and running the application on Windows, macOS, and Linux platforms.

## Table of Contents

- [System Requirements](#system-requirements)
- [Quick Start](#quick-start)
- [Platform-Specific Installation](#platform-specific-installation)
- [Development Environment Setup](#development-environment-setup)
- [IDE Configuration](#ide-configuration)
- [Building from Source](#building-from-source)
- [Running the Application](#running-the-application)
- [Verification Steps](#verification-steps)
- [Common Issues](#common-issues)
- [Advanced Configuration](#advanced-configuration)

---

## System Requirements

### Minimum Requirements

| Component | Requirement | Recommended |
|-----------|-------------|-------------|
| **Operating System** | Windows 10, macOS 10.14, Ubuntu 18.04 | Windows 11, macOS 12+, Ubuntu 20.04+ |
| **Java Runtime** | JDK 17 or higher | JDK 21 LTS |
| **Memory (RAM)** | 512 MB available | 2 GB available |
| **Disk Space** | 100 MB free space | 500 MB free space |
| **Display** | 1024x768 resolution | 1920x1080 resolution |
| **Network** | Internet connection for dependencies | Broadband connection |

### Development Requirements

| Tool | Version | Purpose |
|------|---------|---------|
| **Java JDK** | 17+ | Core development platform |
| **Apache Maven** | 3.6.3+ | Build automation and dependency management |
| **Git** | 2.20+ | Version control |
| **IDE** | IntelliJ IDEA 2023.1+ / Eclipse 2023-03+ | Development environment |

---

## Quick Start

For users who want to run the application immediately:

### Option 1: Pre-built Release (Future)
```bash
# Download the latest release
curl -L -o civil-war-general.jar https://github.com/dev-ricks/Civil-War-General/releases/latest/download/civil-war-general.jar

# Run the application
java -jar civil-war-general.jar
```

### Option 2: Build from Source
```bash
# Clone the repository
git clone https://github.com/dev-ricks/Civil-War-General.git
cd Civil-War-General

# Build and run
mvn clean javafx:run
```

---

## Platform-Specific Installation

### Windows Installation

#### Prerequisites Installation

1. **Install Java JDK 17+**
   ```powershell
   # Using Chocolatey (recommended)
   choco install openjdk17

   # Or download from Oracle/OpenJDK website
   # https://jdk.java.net/17/
   ```

2. **Install Maven**
   ```powershell
   # Using Chocolatey
   choco install maven

   # Or download from Apache Maven website
   # https://maven.apache.org/download.cgi
   ```

3. **Install Git**
   ```powershell
   # Using Chocolatey
   choco install git

   # Or download from Git website
   # https://git-scm.com/download/win
   ```

#### Environment Variables Setup

1. **Set JAVA_HOME**
   ```powershell
   # Open System Properties > Environment Variables
   # Add new system variable:
   JAVA_HOME=C:\Program Files\OpenJDK\jdk-17.0.2

   # Add to PATH:
   %JAVA_HOME%\bin
   ```

2. **Set MAVEN_HOME**
   ```powershell
   # Add new system variable:
   MAVEN_HOME=C:\Program Files\Apache\maven-3.9.4

   # Add to PATH:
   %MAVEN_HOME%\bin
   ```

#### Verification
```powershell
java -version
mvn -version
git --version
```

### macOS Installation

#### Prerequisites Installation

1. **Install Java JDK 17+**
   ```bash
   # Using Homebrew (recommended)
   brew install openjdk@17

   # Add to shell profile (.zshrc or .bash_profile)
   echo 'export PATH="/opt/homebrew/opt/openjdk@17/bin:$PATH"' >> ~/.zshrc
   ```

2. **Install Maven**
   ```bash
   # Using Homebrew
   brew install maven
   ```

3. **Install Git**
   ```bash
   # Using Homebrew
   brew install git

   # Or use Xcode Command Line Tools
   xcode-select --install
   ```

#### Environment Variables Setup
```bash
# Add to ~/.zshrc or ~/.bash_profile
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export PATH=$JAVA_HOME/bin:$PATH
export MAVEN_HOME=/opt/homebrew/Cellar/maven/3.9.4/libexec
export PATH=$MAVEN_HOME/bin:$PATH
```

#### Verification
```bash
java -version
mvn -version
git --version
```

### Linux Installation (Ubuntu/Debian)

#### Prerequisites Installation

1. **Install Java JDK 17+**
   ```bash
   # Update package index
   sudo apt update

   # Install OpenJDK 17
   sudo apt install openjdk-17-jdk

   # Verify installation
   java -version
   ```

2. **Install Maven**
   ```bash
   # Install Maven
   sudo apt install maven

   # Verify installation
   mvn -version
   ```

3. **Install Git**
   ```bash
   # Install Git
   sudo apt install git

   # Verify installation
   git --version
   ```

#### Environment Variables Setup
```bash
# Add to ~/.bashrc or ~/.profile
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export PATH=$JAVA_HOME/bin:$PATH
export MAVEN_HOME=/usr/share/maven
export PATH=$MAVEN_HOME/bin:$PATH

# Reload shell configuration
source ~/.bashrc
```

### Linux Installation (CentOS/RHEL/Fedora)

#### Prerequisites Installation

1. **Install Java JDK 17+**
   ```bash
   # For CentOS/RHEL 8+
   sudo dnf install java-17-openjdk-devel

   # For older versions
   sudo yum install java-17-openjdk-devel
   ```

2. **Install Maven**
   ```bash
   # For CentOS/RHEL 8+
   sudo dnf install maven

   # For older versions
   sudo yum install maven
   ```

3. **Install Git**
   ```bash
   # For CentOS/RHEL 8+
   sudo dnf install git

   # For older versions
   sudo yum install git
   ```

---

## Development Environment Setup

### Repository Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/dev-ricks/Civil-War-General.git
   cd Civil-War-General
   ```

2. **Verify Project Structure**
   ```bash
   ls -la
   # Should show: pom.xml, src/, README.md, etc.
   ```

3. **Install Dependencies**
   ```bash
   mvn clean install
   ```

### Maven Configuration

#### Settings.xml Configuration (Optional)
Create or edit `~/.m2/settings.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 
          http://maven.apache.org/xsd/settings-1.0.0.xsd">
    
    <localRepository>${user.home}/.m2/repository</localRepository>
    
    <profiles>
        <profile>
            <id>civil-war-general</id>
            <properties>
                <maven.compiler.source>17</maven.compiler.source>
                <maven.compiler.target>17</maven.compiler.target>
            </properties>
        </profile>
    </profiles>
    
    <activeProfiles>
        <activeProfile>civil-war-general</activeProfile>
    </activeProfiles>
</settings>
```

---

## IDE Configuration

### IntelliJ IDEA Setup (Recommended)

#### Installation
1. **Download IntelliJ IDEA**
   - Community Edition (free): https://www.jetbrains.com/idea/download/
   - Ultimate Edition (paid): Enhanced features for enterprise development

2. **Install Required Plugins**
   - JavaFX Runtime for IDE (if not included)
   - Maven Integration (usually pre-installed)
   - Git Integration (usually pre-installed)

#### Project Import
1. **Open IntelliJ IDEA**
2. **Import Project**
   - File → Open → Select `Civil-War-General` directory
   - Choose "Import project from external model" → Maven
   - Use default settings and click "Finish"

3. **Configure Project SDK**
   - File → Project Structure → Project
   - Set Project SDK to Java 17+
   - Set Project language level to "17 - Sealed types, always-strict floating-point semantics"

4. **Configure Run Configuration**
   ```
   Main class: com.devricks.civilwargeneral.CivilWarGeneral
   VM options: --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml
   Working directory: $MODULE_WORKING_DIR$
   ```

### Eclipse Setup

#### Installation
1. **Download Eclipse IDE for Java Developers**
   - https://www.eclipse.org/downloads/

2. **Install Required Plugins**
   - e(fx)clipse for JavaFX support
   - Maven Integration (m2e) - usually pre-installed

#### Project Import
1. **Open Eclipse**
2. **Import Project**
   - File → Import → Existing Maven Projects
   - Browse to `Civil-War-General` directory
   - Select pom.xml and click "Finish"

3. **Configure Build Path**
   - Right-click project → Properties → Java Build Path
   - Ensure JRE System Library is Java 17+
   - Add JavaFX libraries if not automatically detected

### Visual Studio Code Setup

#### Installation
1. **Download VS Code**
   - https://code.visualstudio.com/

2. **Install Required Extensions**
   - Extension Pack for Java (Microsoft)
   - JavaFX Support (if available)
   - Maven for Java

#### Project Setup
1. **Open Folder**
   - File → Open Folder → Select `Civil-War-General` directory

2. **Configure Java**
   - Ctrl+Shift+P → "Java: Configure Runtime"
   - Set Java 17+ as the runtime

---

## Building from Source

### Standard Build Process

1. **Clean and Compile**
   ```bash
   mvn clean compile
   ```

2. **Run Tests**
   ```bash
   mvn test
   ```

3. **Package Application**
   ```bash
   mvn clean package
   ```

4. **Generate Documentation**
   ```bash
   mvn javadoc:javadoc
   ```

### Build Profiles

#### Development Profile
```bash
mvn clean compile -Pdevelopment
```

#### Production Profile
```bash
mvn clean package -Pproduction
```

### Build Verification

After successful build, verify the following files exist:
```
target/
├── classes/
├── Civil-War-General-1.0-SNAPSHOT.jar
├── maven-archiver/
└── generated-sources/
```

---

## Running the Application

### Method 1: Maven JavaFX Plugin (Recommended for Development)
```bash
mvn clean javafx:run
```

### Method 2: Direct JAR Execution
```bash
# Build the application first
mvn clean package

# Run the JAR file
java --module-path /path/to/javafx/lib \
     --add-modules javafx.controls,javafx.fxml \
     -jar target/Civil-War-General-1.0-SNAPSHOT.jar
```

### Method 3: IDE Run Configuration
- **IntelliJ IDEA**: Right-click `CivilWarGeneral.java` → Run
- **Eclipse**: Right-click project → Run As → Java Application
- **VS Code**: Open `CivilWarGeneral.java` → Click "Run" above main method

### Method 4: Maven Exec Plugin
```bash
mvn clean compile exec:java -Dexec.mainClass="com.devricks.civilwargeneral.CivilWarGeneral"
```

---

## Verification Steps

### 1. Application Launch Verification
- Application window opens (800x600 pixels)
- Window title shows "Civil War General"
- UI elements are properly displayed

### 2. Functionality Verification
- Click "Generate Command" button
- Verify random order appears in the list
- Verify order contains name and description
- Test multiple command generations

### 3. Configuration Verification
- Verify default orders load from JSON file
- Check that orders include:
  - Attack Forward
  - Attack Flank
  - Defend Forward
  - Retreat Backward

### 4. Error Handling Verification
- Test with missing configuration files
- Verify graceful error handling
- Check error messages are user-friendly

---

## Common Issues

### Java Version Issues

**Problem**: `UnsupportedClassVersionError`
```
Solution:
1. Verify Java version: java -version
2. Ensure Java 17+ is installed
3. Update JAVA_HOME environment variable
4. Restart terminal/IDE
```

**Problem**: JavaFX modules not found
```
Solution:
1. Ensure JavaFX is included in module path
2. Add VM arguments: --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml
3. For Maven: Use javafx:run goal instead of exec:java
```

### Maven Issues

**Problem**: Dependencies not downloading
```bash
# Clear local repository and re-download
rm -rf ~/.m2/repository/com/devricks
mvn clean install -U
```

**Problem**: Maven not found
```bash
# Verify Maven installation
mvn -version

# If not found, check PATH environment variable
echo $PATH  # Linux/macOS
echo %PATH% # Windows
```

### Build Issues

**Problem**: Compilation errors
```bash
# Clean and rebuild
mvn clean compile

# Check for syntax errors in source files
# Verify all imports are correct
```

**Problem**: Resource files not found
```bash
# Verify resources are in correct location:
src/main/resources/com/devricks/civilwargeneral/
├── default-orders.json
├── main-view.fxml
└── hello-view.fxml
```

### Runtime Issues

**Problem**: Application doesn't start
```
1. Check console output for error messages
2. Verify all dependencies are available
3. Test with: mvn clean javafx:run
4. Check JavaFX runtime is properly configured
```

**Problem**: UI elements not displaying
```
1. Verify FXML files are in resources directory
2. Check CSS files are properly referenced
3. Ensure controller classes are properly annotated
```

---

## Advanced Configuration

### Custom JVM Arguments

For better performance or debugging:

```bash
# Increased memory allocation
java -Xmx2g -Xms512m -jar civil-war-general.jar

# Enable debugging
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar civil-war-general.jar

# Enable JFR profiling
java -XX:+FlightRecorder -XX:StartFlightRecording=duration=60s,filename=profile.jfr -jar civil-war-general.jar
```

### Custom Configuration Files

Create custom order configurations:

1. **Copy default configuration**
   ```bash
   cp src/main/resources/com/devricks/civilwargeneral/default-orders.json custom-orders.json
   ```

2. **Modify orders as needed**
   ```json
   [
     {
       "name": "Custom Order",
       "description": "Your custom tactical order",
       "id": 100
     }
   ]
   ```

3. **Update application to use custom file** (requires code modification)

### Development Mode Configuration

For development with auto-reload capabilities:

```bash
# Use Maven with continuous compilation
mvn compile -Dfile.encoding=UTF-8 -Dmaven.compiler.debug=true

# Run with development profile
mvn clean javafx:run -Pdevelopment
```

### Logging Configuration

Create `src/main/resources/logback.xml` for custom logging:

```xml
<configuration>
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    
    <logger name="com.devricks.civilwargeneral" level="DEBUG"/>
    
    <root level="INFO">
        <appender-ref ref="STDOUT"/>
    </root>
</configuration>
```

---

## Next Steps

After successful installation:

1. **Explore the Application**
   - Generate multiple commands
   - Examine the order history
   - Test different scenarios

2. **Review Documentation**
   - Read [README.md](README.md) for project overview
   - Check [API.md](API.md) for technical details
   - Review [ARCHITECTURE.md](ARCHITECTURE.md) for system design

3. **Development Setup**
   - Configure your preferred IDE
   - Set up debugging configurations
   - Explore the source code structure

4. **Contributing**
   - Read [CONTRIBUTING.md](CONTRIBUTING.md) for contribution guidelines
   - Set up development branch
   - Run the test suite

---

## Support

If you encounter issues not covered in this guide:

1. **Check Documentation**
   - [TROUBLESHOOTING.md](TROUBLESHOOTING.md) for common problems
   - [API.md](API.md) for technical reference

2. **Community Support**
   - GitHub Issues: https://github.com/dev-ricks/Civil-War-General/issues
   - GitHub Discussions: https://github.com/dev-ricks/Civil-War-General/discussions

3. **Development Support**
   - Review existing issues and pull requests
   - Join development discussions
   - Contribute improvements to documentation

---

*This installation guide is maintained to ensure smooth setup across all supported platforms. Please report any installation issues or suggest improvements through GitHub issues.*
