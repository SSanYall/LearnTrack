# Setup_Instructions.md

## JDK Version
* **JDK Version:** 25.0.2 (Oracle OpenJDK)

---

## Hello World Execution
To run the program in IntelliJ IDEA:

1.  **Code:** A simple class was created with a `main` method:
    ```java
    public class Main {
        public static void main(String[] args) {
            System.out.println("Hello World");
        }
    }
    ```
2.  **Run:** Clicked the **green play button** in the top right toolbar or next to the code line.
3.  **Alternatively Open your Terminal:** Navigate to the folder where your `.java` file is saved.
4.  **Compile the code:** Run the following command to turn your `.java` file into a `.class` (bytecode) file:
    ```bash
    javac YourFileName.java
    ```
    
5.  **Run the program:** Execute the compiled class file using this command:
    ```bash
    java YourFileName
    ```
6. **Result:** The output `Hello World` appeared in the **Run tool window** at the bottom of the IDE, confirming the environment is configured correctly.

---
