---
name: selenium-parallel-expert
description: Experto en ejecuciones paralelas y concurrencia con Selenium, JUnit 5 y Java 24. Úsalo para ThreadLocal, condiciones de carrera y ciclos de vida thread-safe.
triggers: ["configurar paralelo", "error de concurrencia", "ajustar threadlocal", "debuggear sesión", "fallo aleatorio"]
---

# Selenium Parallel Expert (Concurrencia y Thread-Safety)

Este skill proporciona guías críticas para garantizar que las pruebas automatizadas se ejecuten de forma segura y aislada en entornos concurrentes, aprovechando las capacidades de Java 24 y JUnit Platform.

## Reglas de Oro para Paralelismo

- **Aislamiento Total**: Queda estrictamente prohibido el uso de variables `static` para almacenar estados de prueba o instancias de `WebDriver` que no estén encapsuladas en un `ThreadLocal`.
- **Acceso a Driver**: El acceso al `WebDriver` debe realizarse siempre a través de un método `getDriver()` que consulte el `ThreadLocal` del hilo actual.
- **Ciclo de Vida (Lifecycle)**: Los métodos de `@Before` y `@After` (Hooks) deben garantizar que el `setup` y `teardown` (quit) del driver ocurran dentro del contexto del mismo hilo.

## Optimización para Java 24 y Entornos Modernos

- **Gestión de Memoria**: El agente debe sugerir el uso de `ThreadLocal.withInitial()` para inicializaciones limpias y asegurar que se llame a `.remove()` para evitar fugas de memoria en el pool de hilos de JUnit.
- **Virtual Threads (Opcional)**: Si se explora el uso de hilos virtuales de Java para tareas de red o API testing, este skill debe guiar la implementación para no bloquear el Event Loop.
- **Colecciones Concurrentes**: Priorizar el uso de `ConcurrentHashMap` o `CopyOnWriteArrayList` si existe alguna necesidad mínima de compartir estados de configuración.

## Validación de Seguridad de Hilos (Thread-Safe)

- **Singletons**: Deben implementarse mediante `enum` o *Lazy Initialization* segura para evitar inconsistencias entre hilos.
- **Evitar Condiciones de Carrera**: Al manipular contadores de reportes o logs, asegurar el uso de primitivas atómicas como `AtomicInteger`.

## Análisis de Código Concurrent

Al revisar o generar código, este skill debe:
1. Identificar accesos a recursos compartidos sin protección (ej. variables estáticas globales).
2. Validar que la instancia del driver no se filtre entre clases de forma insegura.
3. Confirmar la limpieza del hilo (`ThreadLocal.remove()`) tras el `driver.quit()`.

Si una implementación compromete la seguridad de hilos, este skill debe emitir una advertencia clara y proponer la refactorización a un modelo aislado.