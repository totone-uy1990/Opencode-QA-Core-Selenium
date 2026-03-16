package pages;

import coreLocators.LocatorRepository;
import coreLocators.LocatorUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.Map;

public class PracticeFormPage extends BasePage {
    
    private static final String PAGE = "practice_form";

    @Override
    protected WebElement getElement(By locator) {
        return getWebElement(locator);
    }

    // Helper para obtener el By de forma limpia
    private By get(String key) {
        return LocatorUtils.getBy(LocatorRepository.get(PAGE, key));
    }

    public void navegarAlFormulario() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void completarFormulario(Map<String, String> data) {
        data.forEach((campo, valor) -> {
            if (valor == null || valor.isEmpty()) return;
            
            switch (campo) {
                case "Nombre Completo" -> ingresarNombreCompleto(valor);
                case "Email"           -> write(get("userEmailInput"), valor);
                case "Género"          -> seleccionarGenero(valor);
                case "Teléfono"        -> write(get("userNumberInput"), valor);
                case "Fecha Nacimiento"-> ingresarFechaNacimiento(valor);
                case "Materias"        -> ingresarMaterias(valor);
                case "Hobbies"         -> seleccionarHobbies(valor);
                case "Dirección"       -> write(get("currentAddressTextarea"), valor);
                case "Ubicación"       -> seleccionarUbicacion(valor);
                default -> System.out.println("ADVERTENCIA: Campo no mapeado -> " + campo);
            }
        });
    }

    private void ingresarNombreCompleto(String nombre) {
        String[] partes = nombre.split(" ");
        write(get("firstNameInput"), partes[0]);
        if (partes.length > 1) write(get("lastNameInput"), partes[1]);
    }

    private void seleccionarGenero(String genero) {
        String key = switch (genero.toLowerCase()) {
            case "femenino" -> "genderFemaleRadio";
            case "otro"     -> "genderOtherRadio";
            default         -> "genderMaleRadio";
        };
        clickElement(get(key));
    }

    private void ingresarFechaNacimiento(String fecha) {
        // En este sitio, a veces es más estable limpiar y enviar Keys
        WebElement input = getWebElement(get("dateOfBirthInput"));
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.BACK_SPACE);
        input.sendKeys(fecha);
        input.sendKeys(Keys.ENTER);
    }

    private void ingresarMaterias(String materias) {
        WebElement input = getWebElement(get("subjectsInput"));
        for (String materia : materias.split(",")) {
            input.sendKeys(materia.trim());
            input.sendKeys(Keys.ENTER);
        }
    }

    private void seleccionarHobbies(String hobbies) {
        if (hobbies.contains("Sports")) clickElement(get("hobbiesSportsCheckbox"));
        if (hobbies.contains("Reading")) clickElement(get("hobbiesReadingCheckbox"));
        if (hobbies.contains("Music")) clickElement(get("hobbiesMusicCheckbox"));
    }

    private void seleccionarUbicacion(String ubicacion) {
        String[] partes = ubicacion.split("-");
        // State
        WebElement stateInput = getWebElement(get("stateInput"));
        stateInput.sendKeys(partes[0].trim());
        stateInput.sendKeys(Keys.ENTER);
        
        // City
        if (partes.length > 1) {
            WebElement cityInput = getWebElement(get("cityInput"));
            cityInput.sendKeys(partes[1].trim());
            cityInput.sendKeys(Keys.ENTER);
        }
    }

    public void ingresarSoloTelefono(String telefono) {
        write(get("userNumberInput"), telefono);
    }

    public void enviarFormulario() {
        // Usamos JS click porque a veces el botón está tapado por banners en demoqa
        WebElement btn = getWebElement(get("submitButton"));
        ((JavascriptExecutor) getDriverFromThread()).executeScript("arguments[0].click();", btn);
    }

    public boolean registroExitoso() {
        try {
            // Damos un tiempo pequeño para que el modal cargue
            return elementIsDisplayed(get("successModalTitle"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean camposObligatoriosEnRojo() {
        // En DemoQA, al validar, el formulario recibe la clase 'was-validated'
        // y los campos ':invalid' muestran estilos específicos.
        return elementIsDisplayed(get("wasValidatedForm"));
    }

    public boolean campoTelefonoInvalido() {
        // Verificamos si el campo de teléfono tiene el pseudo-estado :invalid de CSS
        WebElement telefono = getWebElement(get("userNumberInput"));
        String script = "return window.getComputedStyle(arguments[0], ':invalid') !== null || arguments[0].matches(':invalid');";
        return (Boolean) ((JavascriptExecutor) getDriverFromThread()).executeScript(script, telefono);
    }
}
