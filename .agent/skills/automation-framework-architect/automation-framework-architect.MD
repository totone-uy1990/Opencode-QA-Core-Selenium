---
name: automation-framework-architect
description: Arquitecto de frameworks de automatización especializado en Page Object Model (POM) y separación de capas para Java 24.
triggers: ["revisar arquitectura", "crear nueva page", "auditar encapsulamiento", "refactorizar tests", "validar estructura"]
---

# Automation Framework Architect (Estructura y Encapsulamiento)

Este skill proporciona guías para mantener la integridad estructural del framework de automatización, evitando el acoplamiento y garantizando la mantenibilidad en entornos de Java 24 y Gradle.

## Reglas de Encapsulamiento y Diseño

- **Page Object Model Puro**: Las clases de página deben representar exclusivamente el estado y el comportamiento de la interfaz de usuario. No deben contener aserciones (`Assert.assertTrue`, etc.).
- **Ocultamiento del Driver**: El objeto `WebDriver` debe estar encapsulado dentro de la clase base de la página (`BasePage`) o una clase de utilidad de Driver. Nunca debe exponerse directamente en las clases de `Steps` o `TestRunner`.
- **Localizadores Privados**: Todos los localizadores (`By`, `@FindBy`) deben ser privados. El acceso a los elementos se realiza exclusivamente a través de métodos de acción descriptivos (ej. `login(user, pass)` en lugar de `clickLoginButton()`).
- **Acciones vs. Detalles**: Los métodos de las clases de página deben representar acciones de alto nivel para el usuario, no detalles técnicos de bajo nivel del navegador.

## Separación Estricta de Capas

- **Pages**: Interacción con el DOM y acciones de la UI.
- **Steps**: Orquestación del flujo de negocio y realización de aserciones.
- **Hooks**: Gestión del ciclo de vida (setup/teardown) del entorno.
- **Utilities/Config**: Funciones auxiliares genéricas y gestión de propiedades del entorno.

## Optimización para Java 24 y Antigravity

- **Modern Java**: Utiliza *Records* para el manejo de datos de prueba (Data Transfer Objects) y *Pattern Matching* para simplificar la lógica de validación de estados en la UI.
- **Validación Automática**: El agente debe advertir si detecta la instanciación de un `WebDriver` fuera de la capa de `DriverFactory` o `BasePage`.
- **Integración con Browser Agent**: Si un localizador falla durante la revisión de código, el agente debe proponer el uso del navegador integrado para inspeccionar el DOM y sugerir un selector más robusto (CSS/XPath).

## Análisis de Diseño y SOLID

Al revisar o generar código, este skill debe:
1. Detectar fugas de encapsulamiento (ej. devolver un `WebElement` desde una Page).
2. Proponer alternativas cuando se detecte lógica de aserción dentro de las páginas.
3. Asegurar que no haya código duplicado entre diferentes clases de página mediante el uso de herencia (`BasePage`) o composición.
4. Aplicar los principios SOLID (especialmente Responsabilidad Única y Abierto/Cerrado) para facilitar la extensión del framework sin modificar el código base existente.

Si una solución compromete la arquitectura o la separación de responsabilidades, este skill debe advertirlo y proponer una alternativa robusta.