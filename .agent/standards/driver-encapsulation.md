# Estándar: Encapsulamiento del Driver y Capas

Este estándar define cómo debe interactuar el código con Selenium WebDriver para asegurar la estabilidad, el paralelismo y la mantenibilidad del framework.

## Regla de Oro
**EL DRIVER NUNCA DEBE SER USADO DIRECTAMENTE FUERA DE BASEPAGE.**

### Jerarquía de Responsabilidades:
1. **BasePage**: Única capa que "ve" al Driver. Contiene métodos genéricos (wrappers) como `clickElement`, `write`, `getWebElement`, etc.
2. **Page Objects**: Heredan de `BasePage`. Orquestan los métodos genéricos para crear acciones de negocio (ej: `loginAs(user)`, `addProductToCart()`).
3. **Step Definitions**: Interactúan unicamente con los Page Objects. No conocen nada de Selenium ni de `By` locators.

## Directrices de Implementación

### 1. Acciones Genéricas en BasePage
Si necesitas una acción nueva de Selenium (ej: drag and drop), **NO** la implementes en el Page Object ni en el Step.
- Créala en `BasePage` de forma genérica.
- Expónla como un método `protected` o `public` según corresponda.

```java
// BIEN (En BasePage.java)
public void hoverOverElement(By locator) {
    Actions actions = new Actions(getDriverFromThread());
    actions.moveToElement(findElement(locator)).perform();
}
```

### 2. Uso en Page Objects
El Page Object usa el método de su padre (`BasePage`).

```java
// BIEN (En LoginPage.java)
public void moveToLogo() {
    hoverOverElement(logoLocator);
}
```

### 3. Prohibiciones en Step Definitions
Está terminantemente prohibido:
- Llamar a `getDriverFromThread()` desde un Step.
- Realizar aserciones sobre objetos `WebElement` directamente si estos han sido obtenidos saltándose la capa de abstracción.
- Instanciar `Actions`, `WebDriverWait` o cualquier clase de Selenium en los Steps.

## ¿Qué hacer si falta una acción?
1. Identifica el comando de Selenium necesario.
2. Añade el wrapper en `BasePage.java` siguiendo el patrón existente (manejo de esperas, logs, etc.).
3. Usa ese wrapper en tu Page Object.
4. Invoca el método del Page Object desde tu Step.
