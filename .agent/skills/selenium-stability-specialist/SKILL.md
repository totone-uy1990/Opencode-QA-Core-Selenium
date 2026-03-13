---
name: selenium-stability-specialist
description: Especialista en estabilidad de pruebas y robustez de localizadores para Selenium y Java 24. Úsalo para eliminar flakiness y optimizar esperas explícitas.
triggers: ["mejorar locator", "test inestable", "flaky test", "error de espera", "inspeccionar elemento"]
---

# Especialista en Estabilidad con Selenium

Especialista en estabilidad de pruebas y robustez de localizadores para Selenium y Java 24. Úsalo para eliminar flakiness y optimizar esperas explícitas.

## Estrategias

- **Prioridad de Localizadores**: Favorecer atributos robustos en este orden: `ID` > `Name` > `data-testid` > `Role/Label` > `CSS` > `XPath Relativo`.
- **Sincronización Explícita**: Usar solo `WebDriverWait` con `ExpectedConditions` específicas. Las esperas mixtas están estrictamente prohibidas.
- **Interacción Segura**: Validar la visibilidad y capacidad de clic antes de cualquier interacción para prevenir `StaleElementReferenceException`.

## Directrices de Implementación

- **Selectores Robustos**: Evitar XPaths absolutos o aquellos que dependan de clases CSS dinámicas (ej. los `css-*` de MUI).
- **Esperas Inteligentes**: Encapsular la lógica de estabilización dentro de `WaitUtils`, usando lambdas de Java 24 para condiciones de espera personalizadas.
- **Verificación del DOM**: Usar el **Browser Agent** para verificar que un localizador propuesto sea único y estable en el DOM en vivo.

## Mejores Prácticas

- **Cero Inestabilidad (Flakiness)**: Auditar periódicamente las pruebas en busca de fragilidad estructural y problemas de carga asíncrona.
- **Defensa de Atributos de Datos**: Sugerir al equipo de desarrollo la implementación de atributos `data-automation-id` estables cuando falten.
- **Sincronización Safe for Threads**: Asegurar que los objetos de sincronización se creen por hilo para soportar la ejecución paralela de forma segura.