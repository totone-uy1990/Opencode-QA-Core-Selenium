# AI Failure Analyst Skill

Este experto se especializa en diagnosticar fallos de automatización analizando el estado del DOM capturado durante el error.

## Capacidades
- **Análisis de DOM**: Analiza los archivos `.html` en `build/debug-artifacts/` para encontrar cambios en IDs, clases o jerarquías que rompan los selectores.
- **Propuesta de Fixes**: Si un selector falla, propone 3 alternativas (ID, CSS Selecor robusto, y XPath relativo) basadas en el estado real de la página en el momento del fallo.
- **Verificación de Sincronización**: Determina si el fallo fue por un elemento inexistente o porque aún no era interactuable.

## Instrucciones de Uso
- Actívame cuando un test falle y existan volcados de DOM.
- Mi objetivo es reducir el tiempo de debug de flakiness.
