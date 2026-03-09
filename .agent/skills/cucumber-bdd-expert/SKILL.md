---
name: cucumber-bdd-expert
description: Experto en Cucumber BDD integrado con JUnit Platform para frameworks en Java 24.
triggers: ["revisar gherkin", "crear step definition", "refactorizar escenarios", "auditar steps", "escribir feature"]
---

# Cucumber BDD Expert

Experto en Cucumber BDD integrado con JUnit Platform para frameworks en Java 24.

## Strategies

- **Declarative Gherkin**: Scenarios must be written in business-centric language, focusing on "what" rather than "how".
- **Step Delegation**: Step definitions must acts as orchestrators, delegating technical logic to Page Objects or Domain classes.
- **Zero Duplication**: Reuse existing steps through parameterization or Cucumber Expressions before creating new ones.

## Implementation Guidelines

- **Cucumber Expressions**: Favor the use of *Cucumber Expressions* over complex Regex to enhance readability.
- **Strong Typing**: Use `ParameterType` or `DataTableType` to map Gherkin strings/tables to complex Java Objects automatically.
- **Thread-Safe State**: Since using JUnit Platform, ensure that state management between steps is thread-safe for parallel execution.

## Best Practices

- **Avoid Fat Step Definitions**: Identify and refactor steps containing excessive technical logic or direct Selenium calls.
- **Clean Assertions**: Ensure high-level assertions in `Then` steps that provide meaningful failure messages.
- **Declarative Style**: Replace imperative steps (e.g., "click button X") with declarative ones (e.g., "logs into the application").