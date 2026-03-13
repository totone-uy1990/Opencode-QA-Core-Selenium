---
name: intelligent-explorer-core
description: Core de exploración autónoma capaz de procesar historias de usuario (Jira o Texto) y navegar usando Playwright.
triggers: ["explorar jira", "explorar texto", "analizar historia", "iniciar misión"]
---

# Explorador Inteligente Core

Este experto se encarga de la navegación autónoma para validar requisitos de negocio.

## Capacidades

- **Análisis de Requisitos**: Descompone una historia de usuario en una serie de pasos lógicos de navegación y validación.
- **Navegación Autónoma**: Utiliza el MCP de Playwright para interactuar con la aplicación, buscando cumplir los criterios de aceptación.
- **Manejo de Incertidumbre**: Si un elemento no se encuentra, busca alternativas semánticas o reporta el bloqueo.

## Lineamientos de Sesión Exploratoria (Heurísticas)

Para asegurar una cobertura completa, el explorador debe seguir estas heurísticas:
1.  **Camino Feliz (Happy Path)**: Primero, cumplir el objetivo principal de la historia de usuario.
2.  **Validación de Límites**: Probar inputs con valores vacíos, excesivamente largos o caracteres especiales.
3.  **Exploración Negativa**: Intentar forzar errores (ej. login fallido) para capturar cómo la app maneja fallos.
4.  **Consistencia Visual**: Verificar que no haya elementos encimados o errores de consola (`console.error`).
5.  **Estado de Autenticación**: Probar qué sucede si se intenta acceder a una URL protegida sin login.

## Especificación del Archivo de Sesión (.json)

El archivo generado debe seguir esta estructura para que sea procesable por la `selenium-automation-factory`:

```json
{
  "metadata": {
    "session_name": "Nombre de la sesión",
    "source": "Jira-ID o Texto",
    "base_url": "URL inicial"
  },
  "steps": [
    {
      "order": 1,
      "description": "Descripción del paso en lenguaje natural",
      "action": "click | type | select | assert",
      "locator": {
        "type": "id | css | xpath | data-testid",
        "value": "selector_valor"
      },
      "value": "valor_si_aplica",
      "target_page": "NombreDeLaPaginaDestino",
      "is_navigation": true
    }
  ]
}
```

## Estructura del Reporte de Sesión (Markdown)

Al finalizar, se debe generar un archivo `exploration_report.md` con esta estructura estándar:

# Reporte de Sesión Exploratoria: [ID/Nombre]

- **Explorador/Modo**: [IA Autónoma | Manual Registrada]
- **Enfoque (Charter)**: [Breve descripción del objetivo de la sesión]
- **Duración**: [Tiempo de ejecución]

### 🎯 Objetivos Cubiertos
- [x] [Criterio de Jira o Acción de Texto 1]
- [x] [Criterio de Jira o Acción de Texto 2]

### 🔍 Hallazgos y Observaciones
- **Puntos Positivos**: [Qué funcionó correctamente y es estable]
- **Anomalías/Bugs**: [Comportamientos inesperados o fallos con captura de pantalla]
- **Bloqueos**: [Elementos no alcanzables o errores técnicos]

### 🚫 Fuera de Alcance (No Probado)
- [Listado de áreas o escenarios que se omitieron intencionalmente o por falta de tiempo/acceso]

### 🛠️ Próximos Pasos (Automatización)
- [Resumen de Page Objects y Features que se generarán a partir de esta sesión]

## Directrices de Operación
- **Prioridad de Misión**: Centrarse en los Criterios de Aceptación (AC) definidos en la historia.
- **Evidencia**: Tomar capturas de pantalla de los hitos importantes y de cualquier error encontrado en `build/exploratory-sessions/screenshots/`.
- **Reporte Técnico**: Al finalizar la misión, generar el JSON de sesión y el reporte Markdown detallado.

## Integración
- Consume: `locators-strategy-expert` para elegir los mejores selectores durante la exploración.
- Produce: Archivo de sesión para `selenium-automation-factory`.
