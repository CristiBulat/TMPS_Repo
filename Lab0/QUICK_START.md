# Lab 1 - Quick Start Guide

## 🚀 How to Compile and Run

### Option 1: Using Maven (Recommended)

1.  **Compile the code:**
    ```bash
    mvn compile
    ```

2.  **Run the main demonstration class:**
    ```bash
    mvn exec:java -Dexec.mainClass="md.utm.tmps.lab1.Main"
    ```

3.  **Run the JUnit tests:**
    ```bash
    mvn test
    ```

### Option 2: Using IntelliJ IDEA

1.  Open the project in IntelliJ.
2.  Wait for Maven to resolve dependencies.
3.  Navigate to `src/main/java/md/utm/tmps/lab1/Main.java`.
4.  Right-click inside the file and select **Run 'Main.main()'**.
5.  To run tests, navigate to `src/test/java/md/utm/tmps/lab1/SOLIDPrinciplesTest.java`, right-click, and select **Run 'SOLIDPrinciplesTest'**.