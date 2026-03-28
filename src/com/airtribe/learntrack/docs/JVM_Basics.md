# JVM_Basics.md

## The Java Trio: JDK, JRE, and JVM

Think of these as layers of a toolset:

* **JVM (Java Virtual Machine):** The engine. It is the program that actually executes your Java bytecode. It translates the generic Java code into machine specific instructions for your computer.
* **JRE (Java Runtime Environment):** The container. It includes the JVM plus the standard libraries (pre written code) needed to *run* Java applications. If you only want to play a Java game, you only need the JRE.
* **JDK (Java Development Kit):** The toolkit. It includes the JRE plus developer tools like `javac` (the compiler). You need this to *write, compile, and build* Java programs.



---

## What is Bytecode?
When you write Java code, it is saved in a `.java` file. When you compile it, the compiler turns that code into **bytecode** (saved as a `.class` file).

Bytecode is a "middle man" language. It isn't human-readable code, but it isn't machine code yet either. It is designed to be understood and executed by the **JVM**, regardless of which operating system (Windows, macOS, or Linux) the JVM is running on.

---

## "Write Once, Run Anywhere" (WORA)
This is Java’s most famous promise. Because your code is compiled into universal **bytecode** rather than machine specific code, it does not need to be rewritten or recompiled for different computer architectures.

As long as the target computer has a **JVM** installed, it can take that identical bytecode and translate it into the specific language the local processor understands. This allows developers to focus on writing the program once, knowing it will function consistently on any device equipped with a compatible JVM.

---
