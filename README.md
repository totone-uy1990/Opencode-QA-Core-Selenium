# 🚀 Selenium OpencCode Automation-QA-Framework: AI-Enhanced Automation Engine

Nexus-QA-Framework is a next-generation, enterprise-grade test automation architecture built with **Java 21**, **Selenium**, and **Cucumber**. It is designed for maximum scalability, execution speed (Parallel Testing), and seamless collaboration with AI agents.

---

## 🛠️ Tech Stack & Key Technologies

This framework leverages the latest stable versions of the industry-leading tools:

*   **Language:** Java 21 (LTS) / Java 24 Compatible.
*   **Build Tool:** Gradle 8.14.4 (Optimized for modern JDKs).
*   **Behavior Driven Development (BDD):** Cucumber 7.20.1.
*   **Browser Automation:** Selenium 4.27.0.
*   **Test Runner:** JUnit 5 (Platform Suite).
*   **Reporting:** Allure Report 2.29.0 (Rich visuals and history).
*   **CI/CD:** GitHub Actions (Native integration included).

---

## 🤖 AI-Agent Integration: Powered by Antigravity

One of the unique features of this framework is its native support for **Antigravity**, an advanced AI coding assistant.

### How Antigravity Works Here:
Inside the `.agent/` directory, the framework contains **Specialized Skills** and **Workflows** that empower the AI agent to act as a Senior QA Automation Engineer:

*   **Custom Skills (`.agent/skills/`):** Instructions that guide the AI in maintaining clean code, BDD best practices, and stable Selenium implementations.
*   **Turbo Workflows (`.agent/workflows/`):** Automated routines that the AI can execute without constant manual approval.

### ⚡ AI Commands (Slash Commands):
*   `/run-tests`: Triggers a clean build and executes all tests automatically in "Turbo" mode.

---

## ⚙️ Local Configuration

### Prerequisites:
*   **JDK 21 or 24**: The framework uses a toolchain to ensure builds target Java 21 even if you have a newer version installed.
*   **Google Chrome**: (Or your preferred browser, configured in the code).

### Getting Started:
1.  Clone the repository.
2.  The Gradle wrapper will automatically download the correct Gradle version.
3.  Configure your environment variables if necessary (e.g., credentials).

---

## 🧪 Running Tests

### Standard Execution:
```bash
./gradlew clean test
```

### Run specific Tag (Cucumber):
Edit the `TestRunner.java` or pass the property via CLI:
```bash
./gradlew test -Dcucumber.filter.tags="@SmokeTest"
```

---

## 📊 Reporting

We use **Allure Report** to provide deep insights into test execution.

1.  After running tests, generate the report:
    ```bash
    ./gradlew allureServe
    ```
2.  The report includes:
    *   Step-by-step execution details.
    *   Attached screenshots (if configured).
    *   Timeline and historical trends.

---

## 📖 Documentation & Manuals

For this repository, we follow a split documentation strategy:

*   **README.md (This file):** Quick start, technical overview, and environment setup.
*   **GitHub Wiki:** We recommend using the **GitHub Wiki** for the **Full Manual of Use**, including Page Object patterns, Step Definition guidelines, and advanced configurations. This keeps the codebase clean and the documentation searchable.
*   **Development Docs:** For internal architectural details, check the `.agent/instructions.MD`.

---

## 🤝 Contribution

This project is built under a "Pair Programming" philosophy between humans and AI. Feel free to use the provided agent skills to extend the framework!

---
*Created and maintained with ❤️ by [Ronal] & Antigravity AI.*
