---
name: selenium-parallel-expert
description: Experto en ejecuciones paralelas y concurrencia con Selenium, JUnit 5 y Java 24. Úsalo para ThreadLocal, condiciones de carrera y ciclos de vida thread-safe.
triggers: ["configurar paralelo", "error de concurrencia", "ajustar threadlocal", "debuggear sesión", "fallo aleatorio"]
---

# Selenium Parallel Expert

Experto en ejecuciones paralelas y concurrencia con Selenium, JUnit 5 y Java 24. Úsalo para ThreadLocal, condiciones de carrera y ciclos de vida thread-safe.

## Strategies

- **Thread Isolation**: Use `ThreadLocal<WebDriver>` to ensure each test execution thread has its own isolated session.
- **Concurrent Resource Safety**: Prohibit the use of shared static variables for non-thread-safe objects or state.
- **Atomic Operations**: Use atomic primitives (e.g., `AtomicInteger`) when counters or shared metrics are required.

## Implementation Guidelines

- **Lifecycle Consistency**: Ensure that WebDriver `.quit()` and `ThreadLocal.remove()` occur in the same thread context as initialization.
- **Safe Singletons**: Implement singletons using `enum` or thread-safe lazy initialization to maintain consistency across threads.
- **Memory Management**: Always call `.remove()` on `ThreadLocal` variables in `@After` hooks to prevent memory leaks in the JUnit pool.

## Best Practices

- **Avoid Global State**: State must be encapsulated within the current thread; avoid passing the driver instance as a static global.
- **Concurrent Collections**: Use `ConcurrentHashMap` or `CopyOnWriteArrayList` for any minimal shared configuration needs.
- **Virtual Threads Awareness**: If leveraging Java virtual threads, ensure that long-running operations do not block the underlying carrier threads redundantly.