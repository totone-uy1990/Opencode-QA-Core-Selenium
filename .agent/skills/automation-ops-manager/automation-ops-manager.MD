---
name: automation-ops-manager
description: Gestor de ejecución y reportes. Úsalo para correr tests con Gradle, filtrar por tags de Cucumber y gestionar reportes de Allure.
triggers: ["ejecutar tests", "correr smoke", "generar reporte", "limpiar proyecto", "ver resultados"]
---

# Automation Ops Manager (Gradle & Reporting)

Este skill permite al agente interactuar con el sistema de construcción (Gradle) y las herramientas de reporte (Allure) de tu proyecto de automatización.

## Comandos de Ejecución (Gradle)

El agente tiene permiso para ejecutar y sugerir los siguientes comandos en la terminal integrada:
- **Limpieza**: `./gradlew clean`
- **Ejecución Total**: `./gradlew test`
- **Ejecución por Tags**: `./gradlew test -Dcucumber.filter.tags="@{tag}"` (Reemplazar {tag} por el valor solicitado).
- **Paralelismo**: Ajustar `-Ddataproviderthreadcount={n}` si el framework lo soporta.

## Gestión de Reportes (Allure)

- **Generación**: `./gradlew allureReport`
- **Visualización**: `./gradlew allureServe`
- **Análisis**: Tras la ejecución, el agente debe buscar la carpeta `allure-results` para confirmar si se generaron datos.

## Monitoreo de Salud del Proyecto
- Si el build falla por dependencias, revisar el archivo `build.gradle` y validar la compatibilidad con Java 24.
- Identificar errores de compilación y sugerir la corrección inmediata.