---
description: Uso obligatorio de aserciones personalizadas (CustomAssertions)
---

# Uso de Custom Assertions y Excepciones

Para mantener la integridad conceptual y una gestión unificada de errores en el framework, **nunca se deben usar las aserciones de JUnit (`org.junit.jupiter.api.Assertions`) de manera directa en los Step Definitions o Pages.**

## Reglas de Implementación

1. **Uso de CustomAssertions**: Todas las validaciones deben pasar a través de los métodos expuestos en la clase `assertions.CustomAssertions`. 
2. **Excepciones de Automatización**: Al fallar una aserción o lógica de negocio, se deben levantar las excepciones propias encapsuladas en el paquete `customExceptions` (como `FrameworkException`, `VerificationException` o `AutomationException`), en vez de permitir que JUnit dispare genéricamente `AssertionFailedError`.
3. **Punto único de fallo**: Consolidar las aserciones en `CustomAssertions` nos permite manejar reportes integrados a Allure o captura de screenshots ante fallos de manera transparente, sin poblar de infraestructura los Step Definitions.

### Ejemplos

❌ **INCORRECTO (Uso Directo de JUnit)**:
```java
import org.junit.jupiter.api.Assertions;

@Then("el sistema debe confirmar el registro exitoso")
public void el_sistema_debe_confirmar_el_registro_exitoso() {
    Assertions.assertTrue(page.isSuccessModalDisplayed(), "Modal no se mostró");
}
```

✅ **CORRECTO (Uso de Custom Assertions que disparan Custom Exceptions)**:
```java
import assertions.CustomAssertions;

@Then("el sistema debe confirmar el registro exitoso")
public void el_sistema_debe_confirmar_el_registro_exitoso() {
    CustomAssertions.assertTrue(page.isSuccessModalDisplayed(), "El modal de éxito no se mostró");
}
```
