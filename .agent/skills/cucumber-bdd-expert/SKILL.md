---
name: cucumber-bdd-expert
description: Experto en Cucumber BDD integrado con JUnit Platform para frameworks en Java 24.
triggers: ["revisar gherkin", "crear step definition", "refactorizar escenarios", "auditar steps", "escribir feature"]
---

# Experto en Cucumber BDD

Experto en Cucumber BDD integrado con JUnit Platform para frameworks en Java 24.

## Estrategias

- **Gherkin Declarativo**: Los escenarios deben escribirse en un lenguaje centrado en el negocio, enfocándose en el "qué" en lugar del "cómo".
- **Delegación de Pasos**: Las definiciones de pasos (Step Definitions) deben actuar como orquestadores, delegando la lógica técnica a los Page Objects o clases de dominio.
- **Cero Duplicación**: Reutilizar pasos existentes mediante parametrización o Expresiones de Cucumber antes de crear nuevos.

## Directrices de Implementación

- **Expresiones de Cucumber**: Favorecer el uso de *Cucumber Expressions* sobre Regex complejos para mejorar la legibilidad.
- **Tipado Fuerte**: Usar `ParameterType` o `DataTableType` para mapear automáticamente cadenas/tablas de Gherkin a objetos Java complejos.
- **Estado Thread-Safe**: Al usar JUnit Platform, asegurar que la gestión del estado entre pasos sea segura para hilos (thread-safe) para la ejecución paralela.

## Mejores Prácticas

- **Evitar Step Definitions Pesados**: Identificar y refactorizar pasos que contengan lógica técnica excesiva o llamadas directas a Selenium.
- **Aserciones Limpias**: Asegurar aserciones de alto nivel en los pasos `Then` que proporcionen mensajes de error significativos.
- **Estilo Declarativo**: Reemplazar pasos imperativos (ej. "hacer clic en el botón X") con declarativos (ej. "inicia sesión en la aplicación").