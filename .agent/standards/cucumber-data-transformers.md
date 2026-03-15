# Estándar: Data Transformers con Cucumber y Lombok

En este proyecto, utilizamos `DataTableTransformers` para manejar conjuntos de datos complejos desde los archivos `.feature` hacia los `Step Definitions` de manera tipada y limpia.

## ¿Cuándo usarlos?
Es recomendable usar un Data Transformer cuando:
1. **Multiplicidad de Datos**: Un paso de Gherkin recibe más de 2-3 parámetros relacionados (ej: formularios de registro, credenciales, datos de perfil).
2. **Tablas Gherkin**: Cuando usamos tablas en los pasos para representar un objeto de negocio.
3. **Mantenibilidad**: Para evitar que los Step Definitions dependan de claves de un `Map<String, String>` escritas a mano (hardcoded).

## Estructura de Implementación

### 1. El Modelo (POJO con Lombok)
Ubicación: `src/test/java/steps/models/cnarios/...`
Usamos `@Data` de Lombok para generar automáticamente getters, setters, toString, etc.

```java
@Data
public class LoginData {
    private String username;
    private String password;
}
```

### 2. El Transformer
Ubicación: En el mismo paquete que el modelo.
Utiliza la anotación `@DataTableType` de Cucumber.

```java
public class LoginDataTransformer {
    @DataTableType
    public LoginData loginDataTransformer(Map<String, String> entry) {
        LoginData data = new LoginData();
        data.setUsername(entry.get("username"));
        data.setPassword(entry.get("password"));
        return data;
    }
}
```

### 3. El Step Definition
El Step recibe directamente el objeto `LoginData`.

```java
@When("the user enters his credentials:")
public void fillingUsernameField(LoginData loginData) {
    loginPage.writeUsernameField(loginData.getUsername());
    loginPage.writePasswordField(loginData.getPassword());
}
```

## Beneficios
- **Tipado Fuerte**: Si cambias un nombre de campo en el modelo, el IDE te avisará.
- **Limpieza**: El Step Definition se enfoca en la lógica de negocio, no en parsear mapas.
- **Reutilización**: El mismo modelo puede ser usado en múltiples steps.
