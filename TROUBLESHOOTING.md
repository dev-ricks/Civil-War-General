# Troubleshooting Guide

This guide provides solutions to common issues you might encounter while using or developing the Civil-War-General application.

## Table of Contents
- [Common Issues](#common-issues)
- [Java and JavaFX Issues](#java-and-javafx-issues)
- [Build and Maven Issues](#build-and-maven-issues)
- [Runtime Errors](#runtime-errors)
- [UI/Display Issues](#ui-display-issues)
- [Order and Command Issues](#order-and-command-issues)
- [Performance Problems](#performance-problems)
- [Getting Help](#getting-help)

## Common Issues

### Application Fails to Start
- **Symptom**: Application crashes immediately on startup
  - **Solution 1**: Verify Java 17 or later is installed
    ```bash
    java -version
    ```
  - **Solution 2**: Ensure JAVA_HOME environment variable is set correctly
  - **Solution 3**: Check for conflicting Java versions in your PATH

### Missing JavaFX Modules
- **Symptom**: `Error: JavaFX runtime components are missing, and are required to run this application`
  - **Solution 1**: Run with JavaFX modules included:
    ```bash
    mvn clean javafx:run
    ```
  - **Solution 2**: If using an IDE, ensure JavaFX SDK is properly configured in your project structure

## Java and JavaFX Issues

### Java Version Mismatch
- **Symptom**: `UnsupportedClassVersionError` or similar version-related errors
  - **Solution**: Ensure you're using Java 17 or later
  - **Verify Installation**:
    ```bash
    java -version
    javac -version
    ```

### JavaFX Not Found
- **Symptom**: `ClassNotFoundException` for JavaFX classes
  - **Solution 1**: For Maven, ensure these dependencies are in your pom.xml:
    ```xml
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>17.0.12</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>17.0.12</version>
    </dependency>
    ```
  - **Solution 2**: For manual execution, include JavaFX modules in the classpath

## Build and Maven Issues

### Maven Build Fails
- **Symptom**: Build fails with compilation errors
  - **Solution 1**: Clean and rebuild the project
    ```bash
    mvn clean install
    ```
  - **Solution 2**: Update Maven dependencies
    ```bash
    mvn clean install -U
    ```
  - **Solution 3**: Check for network issues if dependencies can't be resolved

### Dependency Conflicts
- **Symptom**: `NoSuchMethodError` or `NoClassDefFoundError`
  - **Solution 1**: Check for version conflicts
    ```bash
    mvn dependency:tree
    ```
  - **Solution 2**: Use Maven's dependency management to resolve conflicts

## Runtime Errors

### JSON Parsing Errors
- **Symptom**: `JsonProcessingException` when loading orders
  - **Solution 1**: Verify the JSON syntax in `default-orders.json`
  - **Solution 2**: Check file permissions in the resources directory
  - **Solution 3**: Ensure all required fields are present in the JSON file

### NullPointerException
- **Symptom**: Application crashes with NullPointerException
  - **Solution 1**: Check the stack trace for the specific line causing the issue
  - **Solution 2**: Verify that all required properties are initialized in FXML files
  - **Solution 3**: Ensure all @FXML annotated fields are properly injected

## UI/Display Issues

### Blank or Frozen UI
- **Symptom**: Application starts but shows a blank or frozen screen
  - **Solution 1**: Check for uncaught exceptions in the console
  - **Solution 2**: Verify UI updates are performed on the JavaFX Application Thread
  - **Solution 3**: Look for infinite loops or long-running operations on the UI thread

### Styling Issues
- **Symptom**: CSS styles not being applied
  - **Solution 1**: Check for typos in CSS selectors
  - **Solution 2**: Ensure CSS files are in the correct resources directory
  - **Solution 3**: Verify the CSS file is loaded in your FXML or Java code

## Order and Command Issues

### No Orders Available
- **Symptom**: No orders appear in the application
  - **Solution 1**: Check if `default-orders.json` exists in the resources directory
  - **Solution 2**: Verify the JSON structure matches the expected format
  - **Solution 3**: Check the application logs for loading errors

### Command Selection Not Working
- **Symptom**: Clicking the generate command button does nothing
  - **Solution 1**: Check the console for error messages
  - **Solution 2**: Verify the button's onAction handler is correctly set in FXML
  - **Solution 3**: Ensure the CommandSelector is properly initialized

## Performance Problems

### Slow Application Startup
- **Symptom**: Application takes a long time to start
  - **Solution 1**: Check for unnecessary initialization code
  - **Solution 2**: Look for large resource files being loaded at startup
  - **Solution 3**: Enable Java module system for better startup performance

### High Memory Usage
- **Symptom**: Application uses too much memory
  - **Solution 1**: Check for memory leaks in long-lived objects
  - **Solution 2**: Use Java VisualVM or similar tools to profile memory usage
  - **Solution 3**: Adjust JVM memory settings if needed
    ```
    -Xms512m -Xmx1024m
    ```

## Getting Help

If you encounter an issue not covered in this guide:

1. **Check the Logs**: Look for error messages in the console or log files
2. **Search Issues**: Check the project's issue tracker for similar reports
3. **Create a New Issue**: If you can't find a solution, please create a new issue with:
   - Steps to reproduce the problem
   - Expected vs. actual behavior
   - Environment details (OS, Java version, etc.)
   - Relevant error messages or stack traces

## Additional Resources

- [JavaFX Documentation](https://openjfx.io/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Project Wiki](https://github.com/dev-ricks/Civil-War-General/wiki)

---
*Last Updated: 2025-03-20*
