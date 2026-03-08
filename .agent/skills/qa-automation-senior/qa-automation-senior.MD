---
name: qa-automation-senior
description: Experto en arquitectura de automatización con Java 24, Selenium y Cucumber. Úsalo para diseñar, refactorizar o auditar frameworks, asegurando paralelismo y el uso de Gradle.
triggers: ["crear test", "refactorizar framework", "configurar paralelo", "debuggear fallo", "auditar código"]
---

# QA Automation Senior (Java/Selenium/Cucumber)

Este skill proporciona guías estrictas para el desarrollo y mantenimiento de frameworks de automatización de alto rendimiento, optimizados para Java 24 y Gradle.

## Principios de Arquitectura

- **Separación de Capas**: Mantener independencia total entre `Pages` (UI), `Steps` (Negocio) y `Runners` (Configuración).
- **Page Object Model (POM) Puro**: Las clases de página deben devolver nuevos objetos de página o tipos de datos primitivos, nunca exponer `WebElement` directamente.
- **Manejo de Driver**: Utilizar `ThreadLocal<WebDriver>` para garantizar la seguridad de hilos en ejecuciones paralelas. Nunca compartir instancias de driver entre hilos.

## Reglas de Implementación (Java 24 & Selenium)

- **Prohibido `Thread.sleep()`**: Utilizar exclusivamente `WebDriverWait` con `ExpectedConditions` encapsulados en métodos de utilidad.
- **Localización Robusta**: Preferir IDs, nombres o selectores CSS sobre XPaths frágiles.
- **Modern Java 24**: 
    - Aprovechar `Records` para la inmutabilidad de datos de prueba.
    - Usar `Pattern Matching` y `Switch Expressions` para manejar estados de la aplicación de forma limpia.
- **JUnit 5 & JUnit Platform**: Utilizar las extensiones de JUnit 5 para el ciclo de vida y la integración nativa con Cucumber.

## Gestión de Ejecución con Gradle

- **Comandos**: El agente debe conocer y sugerir comandos de Gradle para ejecuciones específicas (ej. `./gradlew clean test -Dcucumber.filter.tags="@Smoke"`).
- **Paralelismo**: Validar la configuración en `build.gradle` para asegurar que el `maxParallelForks` esté alineado con los recursos del sistema.

## Flujo de Trabajo para Nuevas Pruebas

1. **Analizar el Feature**: Validar que los pasos sean declarativos.
2. **Actualizar el POM**: Encapsular elementos nuevos siguiendo el patrón de diseño.
3. **Implementar Steps**: Orquestar acciones sin lógica técnica pesada.
4. **Validar Paralelismo**: Verificar que no existan variables `static` que rompan la ejecución concurrente.