---
name: automation-ops-manager
description: Gestor de ejecución y reportes. Úsalo para correr tests con Gradle, filtrar por tags de Cucumber y gestionar reportes de Allure.
triggers: ["ejecutar tests", "correr smoke", "generar reporte", "limpiar proyecto", "ver resultados"]
---

# Automation Ops Manager

Gestor de ejecución y reportes. Úsalo para correr tests con Gradle, filtrar por tags de Cucumber y gestionar reportes de Allure.

## Strategies

- **Build Automation**: Use Gradle for all lifecycle tasks including cleaning, compiling, and testing.
- **Selective Execution**: Utilize Cucumber tags to run specific test suites (e.g., @Smoke, @Regression).
- **Automated Reporting**: Guarantee Allure results generation and provide methods for report serving and analysis.

## Implementation Guidelines

- **Terminal Commands**: 
  - Clean: `./gradlew clean`
  - All Tests: `./gradlew test`
  - Tagged Tests: `./gradlew test -Dcucumber.filter.tags="@tag"`
- **Parallel Optimization**: Configure `maxParallelForks` in `build.gradle` and suggest runtime adjustments to thread counts.

## Best Practices

- **Resource Management**: Monitor project health (dependencies, Java version) using Gradle status.
- **Reporting Cycle**: Always verify the `allure-results` directory post-execution to confirm data integrity.
- **Error Diagnosis**: In case of build failures, prioritize investigating the `build.gradle` configuration and toolchain consistency.