---
name: automation-framework-architect
description: Arquitecto de frameworks de automatización especializado en Page Object Model (POM) y separación de capas para Java 24.
triggers: ["revisar arquitectura", "crear nueva page", "auditar encapsulamiento", "refactorizar tests", "validar estructura"]
---

# Automation Framework Architect

Arquitecto de frameworks de automatización especializado en Page Object Model (POM) y separación de capas para Java 24.

## Strategies

- **Pure Page Object Model**: UI pages must represent state and behavior only, with no assertions.
- **Strict Layer Separation**: Maintain clear boundaries between Pages (UI), Steps (Business), Hooks (Lifecycle), and Utilities.
- **SOLID Principles**: Apply Single Responsibility and Open/Closed principles to ensure the framework is extensible without modification.

## Implementation Guidelines

- **Driver Encapsulation**: Localize `WebDriver` within `BasePage` or a dedicated DriverFactory. Never expose it to Steps or Runners.
- **Private Locators**: All locators must be private. Interactions should occur through descriptive action methods like `login(user, pass)`.
- **Abstraction Level**: Page methods should represent high-level user actions, not low-level browser operations.

## Best Practices

- **Modern Java 24**: Use *Records* for DTOs and *Pattern Matching* for UI state validation.
- **No Direct Element Exposure**: Avoid returning `WebElement` from Page methods; return new Page objects or primitives instead.
- **Architecture Integrity**: Validate that no `WebDriver` instances are created outside the designated Factory/BasePage.