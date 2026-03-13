---
name: selenium-parallel-expert
description: Experto en ejecuciones paralelas y concurrencia con Selenium, JUnit 5 y Java 24. Úsalo para ThreadLocal, condiciones de carrera y ciclos de vida thread-safe.
triggers: ["configurar paralelo", "error de concurrencia", "ajustar threadlocal", "debuggear sesión", "fallo aleatorio"]
---

# Experto en Paralelismo con Selenium

Experto en ejecuciones paralelas y concurrencia con Selenium, JUnit 5 y Java 24. Úsalo para ThreadLocal, condiciones de carrera y ciclos de vida thread-safe.

## Estrategias

- **Aislamiento de Hilos**: Usar `ThreadLocal<WebDriver>` para asegurar que cada hilo de ejecución de pruebas tenga su propia sesión aislada.
- **Seguridad de Recursos Concurrentes**: Prohibir el uso de variables estáticas compartidas para objetos o estados que no sean seguros para hilos.
- **Operaciones Atómicas**: Usar primitivas atómicas (ej. `AtomicInteger`) cuando se requieran contadores o métricas compartidas.

## Directrices de Implementación

- **Consistencia del Ciclo de Vida**: Asegurar que el `.quit()` de WebDriver y el `.remove()` de `ThreadLocal` ocurran en el mismo contexto de hilo que la inicialización.
- **Singletons Seguros**: Implementar singletons usando `enum` o inicialización perezosa segura para hilos para mantener la consistencia entre hilos.
- **Gestión de Memoria**: Siempre llamar a `.remove()` en las variables `ThreadLocal` en los hooks `@After` para prevenir fugas de memoria en el pool de JUnit.

## Mejores Prácticas

- **Evitar el Estado Global**: El estado debe estar encapsulado dentro del hilo actual; evitar pasar la instancia del driver como una variable estática global.
- **Colecciones Concurrentes**: Usar `ConcurrentHashMap` o `CopyOnWriteArrayList` para cualquier necesidad mínima de configuración compartida.
- **Conciencia de Virtual Threads**: Si se utilizan hilos virtuales de Java, asegurar que las operaciones de larga duración no bloqueen innecesariamente los hilos portadores subyacentes.