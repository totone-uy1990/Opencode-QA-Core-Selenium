---
name: locators-strategy-expert
description: Especialista en diseño de selectores avanzados y estrategias de localización para aplicaciones web complejas.
triggers: ["definir locator", "selector complejo", "xpath relativo", "css selector", "identificar elemento"]
---

# Experto en Estrategias de Localizadores

Especialista en el diseño de selectores precisos, mantenibles y performantes, priorizando la semántica del DOM y la estabilidad ante cambios en la UI.

## Estrategias de Selección

- **Pirámide de Prioridad**:
    1. `ID` / `Name` (Atributos estáticos únicos).
    2. `data-*` (Atributos dedicados a automatización como `data-testid`).
    3. `Role` / `Aria-Label` (Accesibilidad y semántica).
    4. `CSS Selectors` (Jerarquía visual y clases estables).
    5. `Relative XPath` (Relaciones entre elementos hermanos o padres cuando no hay identificadores).

- **Estrategia Descriptiva**: Los nombres de los locadores en Java deben reflejar exactamente qué son (ej: `txtLoginUsername`, `btnSubmitOrder`).

## Directrices de Implementación

- **Evitar XPaths Absolutos**: Están prohibidos por ser extremadamente frágiles ante cualquier cambio estructural.
- **Selectores de Texto Dinámicos**: Usar `contains()` de XPath con precaución, prefiriendo normalizar espacios (`normalize-space()`).
- **Indexación Zero**: Evitar el uso de índices de lista `[1]`, `[2]` en selectores; en su lugar, buscar relaciones de texto o atributos únicos de los elementos de la lista.

## Mejores Prácticas

- **Independencia del Diseño**: Los selectores no deben depender de estilos visuales que cambien frecuentemente (evitar clases como `color-red` o `margin-top-10`).
- **Colaboración con Devs**: Si un elemento es imposible de localizar de forma única, el agente debe sugerir la adición de un `data-automation-id` en el código fuente de la aplicación.
