---
name: selenium-stability-specialist
description: Especialista en estabilidad de pruebas y robustez de localizadores para Selenium y Java 24. Úsalo para eliminar flakiness y optimizar esperas explícitas.
triggers: ["mejorar locator", "test inestable", "flaky test", "error de espera", "inspeccionar elemento"]
---

# Selenium Stability Specialist (Robustez y Estabilidad)

Este skill combate la inestabilidad de las pruebas (flakiness) mediante el diseño de localizadores robustos y gestión inteligente de tiempos en Java 24.

## Estrategias de Localización Robusta

- **Prioridad de Atributos**: `ID` > `Name` > `data-testid` / `data-automation-id` > `CSS Selectors` > `XPath` (relativo).
- **Evitar XPaths Frágiles**: Prohibidos los XPaths absolutos o basados en índices.
- **Uso del Browser Agent**: El agente debe usar el navegador integrado de Antigravity para validar que un selector sea único y estable antes de sugerirlo.

## Gestión de Esperas y Validaciones

- **Esperas Explícitas**: Encapsular `WebDriverWait` en `WaitUtils`. Prohibido el uso de `Implicit Waits` mezclados con `Explicit Waits`.
- **Java 24 Modern Waits**: Utilizar lambdas y métodos de referencia de Java 24 para definir condiciones de espera personalizadas más legibles.
- **Validación Preventiva**: Verificar visibilidad y "clickability" antes de la interacción para evitar `StaleElementReferenceException`.

## Análisis de Estabilidad
1. Detectar localizadores que dependan de la estructura profunda del DOM.
2. Sugerir la implementación de "Smart Waits" si se detectan fallos por carga asíncrona.
3. Priorizar el uso de `data-attributes` y, si no existen, sugerir al equipo de desarrollo su implementación.