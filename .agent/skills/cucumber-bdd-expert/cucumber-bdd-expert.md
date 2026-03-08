---
name: cucumber-bdd-expert
description: Experto en Cucumber BDD integrado con JUnit Platform para frameworks en Java 24.
triggers: ["revisar gherkin", "crear step definition", "refactorizar escenarios", "auditar steps", "escribir feature"]
---

# Cucumber BDD Expert (Step Definitions & Escenarios)

Este skill proporciona guías para mantener una capa de orquestación de pruebas limpia, legible y altamente mantenible, optimizada para la ejecución en JUnit Platform con Java 24.

## Reglas de Step Definitions

- **Delegación Obligatoria**: Los Step Definitions NO deben contener lógica de Selenium ni aserciones técnicas complejas. Su única responsabilidad es delegar acciones a las clases de `Page` o `Domain`.
- **Cero Duplicación**: Antes de implementar un nuevo paso, verificar si existe uno equivalente que pueda reutilizarse mediante parametrización o expresiones regulares.
- **Responsabilidad Única**: Un paso debe representar una única acción de negocio o una única validación de resultado.

## Diseño de Escenarios (Gherkin)

- **Orientación a Negocio**: Los escenarios deben redactarse en lenguaje declarativo (qué hace el usuario) y evitar el lenguaje imperativo (clic en el botón X, escribir en el campo Y).
- **Legibilidad**: Utilizar `Background` para precondiciones comunes y `Scenario Outline` para pruebas basadas en datos, manteniendo los escenarios concisos.
- **Aislamiento de Steps**: Evitar que un paso dependa del estado interno generado por otro paso previo de forma implícita.

## Optimización para Java 24 y JUnit Platform

- **Cucumber Expressions**: Prefiere el uso de *Cucumber Expressions* sobre expresiones regulares complejas para mejorar la legibilidad del código Java.
- **Paralelismo Seguro**: Dado que usas JUnit Platform, asegura que el manejo de estado entre pasos sea *thread-safe* para permitir la ejecución paralela sin colisiones.
- **Tipado Fuerte**: Aprovecha las mejoras de Java 24 para definir transformadores de parámetros (`ParameterType`) que conviertan strings de Gherkin en objetos de dominio complejos automáticamente.

## Análisis de Código BDD

Al revisar o generar código, este skill debe:
1. Detectar y señalar "Fat Step Definitions" (pasos con demasiada lógica técnica).
2. Proponer refactorizaciones para mover lógica de localización hacia el Page Object Model.
3. Asegurar que las aserciones finales en los pasos de tipo `Then` sean claras y utilicen métodos descriptivos.

Si detectas una mala práctica de BDD (como pasos excesivamente detallados o acoplamiento fuerte), este skill debe sugerir una mejora estructural inmediata.