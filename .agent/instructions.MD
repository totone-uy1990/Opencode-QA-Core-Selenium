# Contexto del Proyecto de Automatización

- **Usuario**: Ronal, QA Automation Engineer.
- **Stack Tecnológico**: Java (Prioridad Java 24), Gradle, Selenium WebDriver, Cucumber, JUnit 5 y Allure Reports.
- **Arquitectura**: Page Object Model (POM) Fluido con Lombok.
- **Idioma**: Explicaciones y comentarios siempre en español.

## Directrices de Desarrollo y Calidad
1. **Gestión Dinámica de Locadores (Estándar JSON)**: La definición de locadores DEBE extraerse a archivos JSON ubicados en `src/test/resources/locators/`. Los Page Objects no deben declarar objetos `By` como atributos de clase (`private final By`), sino usar la utilidad `LocatorManager.get("archivo.json", "llave")`. Esto desacopla el DOM de la lógica Java.
2. **Estrategia de Esperas**: Prohibido el uso de `Thread.sleep()`. Utiliza siempre `WebDriverWait` con condiciones de `ExpectedConditions`.
3. **Fluent API**: Siempre que sea posible, los métodos de los Page Objects deben devolver `this` (si la navegación se queda en la misma página) o una nueva instancia del siguiente Page Object.
4. **Lombok**: Utiliza `@Getter` para los locadores si es estrictamente necesario, y prefiere constructores generados con `@RequiredArgsConstructor`.
5. **Logs y Reportes**: Cada acción significativa debe incluir un paso de Allure (`Allure.step`) detallando la interacción.
6. **Uso de BasePage (Delegación)**: Está TERMINANTEMENTE PROHIBIDO usar `driver.findElement()` directamente en los Page Objects. Siempre debes invocar los métodos envolventes (wrappers) declarados en `BasePage` (ej: `clickElement(locator)`, `write(locator, text)`, `getTextOfWebElement(locator)`).

## Operativa del Agente
- **Refactorización**: Antes de cambiar un Page Object, analiza el impacto en el paralelismo (`ThreadLocal`).
- **Diagnóstico**: Si un test falla, utiliza la skill `ai-failure-analyst` para inspeccionar los archivos en `build/debug-artifacts`.