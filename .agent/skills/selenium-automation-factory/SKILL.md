---
name: selenium-automation-factory
description: Generador de código Java/Selenium basado en sesiones exploratorias. Cumple estrictamente con POM y Fluent API.
triggers: ["generar código", "crear test", "scaffold", "transpilar sesión"]
---

# Fábrica de Automatización Selenium

Transforma descripciones de pasos y selectores en código Java robusto y mantenible.

## Capacidades

- **Generador de Page Objects**: Crea o actualiza clases Java extendiendo de `BasePage` con locadores privados y métodos fluidos.
- **Generador de Gherkin**: Crea archivos `.feature` basados en la narrativa de la sesión exploratoria.
- **Implementador de Steps**: Crea las definiciones de pasos vinculando el Gherkin con los Page Objects.

## Directrices de Generación (Obligatorias)

- **Encapsulamiento**: SIEMPRE `private final By`. Nunca exponer WebElements.
- **Fluent API**: SIEMPRE retornar `this` o el siguiente objeto de página.
- **BDD Puro**: Los steps deben ser declarativos, delegando la lógica a las páginas.
- **Lombok**: Usar `@Getter` solo si es necesario y preferir constructores limpios.

## Proceso de Transformación Técnica

La fábrica consume el archivo `.json` de la sesión y aplica las siguientes reglas de mapeo:
1.  **Acción `type`** -> Debe mapearse exclusivamente al método `write(By locator, String value)` heredado de `BasePage`.
2.  **Acción `click`** -> Debe mapearse exclusivamente al método `clickElement(By locator)` heredado de `BasePage`.
3.  **Prohibición de API Directa**: NUNCA generar código con `driver.findElement()`. Todas las interacciones deben delegarse a los métodos wrappers comprobados de `BasePage`.
3.  **Campo `target_page`** -> Si cambia, el método en Java debe instanciar y retornar la nueva clase (ej. `return new HomePage();`).
4.  **Campo `is_navigation`** -> Dispara la lógica de Fluent API para cambiar de contexto de Page Object.

## Integración
- Depende de: `automation-framework-architect` y `locators-strategy-expert`.
- Entrada: Archivos de sesión (`.json`) siguiendo el esquema definido en `intelligent-explorer-core`.
- Salida: Código Java 24 (`.java`) y archivos Cucumber (`.feature`).
