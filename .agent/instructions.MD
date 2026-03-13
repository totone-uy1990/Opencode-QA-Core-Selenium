# Contexto del Proyecto de Automatización

- **Usuario**: Ronal, QA Automation Engineer.
- **Stack Tecnológico**: Java (Prioridad Java 24), Gradle, Selenium WebDriver, Cucumber, JUnit 5 y Allure Reports.
- **Arquitectura**: Page Object Model (POM) Fluido con Lombok.
- **Idioma**: Explicaciones y comentarios siempre en español.

## Directrices de Desarrollo y Calidad
1. **Encapsulamiento de Locadores**: Todos los objetos `By` deben ser `private final`. No expongas elementos internos; expón acciones (métodos).
2. **Estrategia de Esperas**: Prohibido el uso de `Thread.sleep()`. Utiliza siempre `WebDriverWait` con condiciones de `ExpectedConditions`.
3. **Fluent API**: Siempre que sea posible, los métodos de los Page Objects deben devolver `this` (si la navegación se queda en la misma página) o una nueva instancia del siguiente Page Object.
4. **Lombok**: Utiliza `@Getter` para los locadores si es estrictamente necesario, y prefiere constructores generados con `@RequiredArgsConstructor`.
5. **Logs y Reportes**: Cada acción significativa debe incluir un paso de Allure (`Allure.step`) detallando la interacción.
6. **Uso de BasePage (Delegación)**: Está TERMINANTEMENTE PROHIBIDO usar `driver.findElement()` directamente en los Page Objects. Siempre debes invocar los métodos envolventes (wrappers) declarados en `BasePage` (ej: `clickElement(locator)`, `write(locator, text)`, `getTextOfWebElement(locator)`).

## Operativa del Agente
- **Refactorización**: Antes de cambiar un Page Object, analiza el impacto en el paralelismo (`ThreadLocal`).
- **Diagnóstico**: Si un test falla, utiliza la skill `ai-failure-analyst` para inspeccionar los archivos en `build/debug-artifacts`.