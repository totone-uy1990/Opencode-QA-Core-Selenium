---
name: automation-ops-manager
description: Gestor de ejecución y reportes. Úsalo para correr tests con Gradle, filtrar por tags de Cucumber y gestionar reportes de Allure.
triggers: ["ejecutar tests", "correr smoke", "generar reporte", "limpiar proyecto", "ver resultados"]
---

# Gestor de Operaciones de Automatización

Gestor de ejecución y reportes. Úsalo para correr tests con Gradle, filtrar por tags de Cucumber y gestionar reportes de Allure.

## Estrategias

- **Automatización de Builds**: Usar Gradle para todas las tareas del ciclo de vida, incluyendo limpieza, compilación y pruebas.
- **Ejecución Selectiva**: Utilizar etiquetas (tags) de Cucumber para ejecutar suites de pruebas específicas (ej. @Smoke, @Regression).
- **Reportes Automatizados**: Garantizar la generación de resultados de Allure y proporcionar métodos para la visualización y análisis de reportes.

## Directrices de Implementación

- **Comandos de Terminal**: 
  - Limpiar: `./gradlew clean`
  - Todas las Pruebas: `./gradlew test`
  - Pruebas Etiquetadas: `./gradlew test -Dcucumber.filter.tags="@tag"`
- **Optimización Paralela**: Configurar `maxParallelForks` en `build.gradle` y sugerir ajustes en tiempo de ejecución para el recuento de hilos.

## Mejores Prácticas

- **Gestión de Recursos**: Monitorear la salud del proyecto (dependencias, versión de Java) usando el estado de Gradle.
- **Ciclo de Reportes**: Siempre verificar el directorio `allure-results` después de la ejecución para confirmar la integridad de los datos.
- **Diagnóstico de Errores**: En caso de fallos en el build, priorizar la investigación de la configuración de `build.gradle` y la consistencia del toolchain.