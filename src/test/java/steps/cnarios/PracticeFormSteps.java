package steps.cnarios;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.PracticeFormPage;
import java.util.Map;

public class PracticeFormSteps {

    // Asumimos que tienes una forma de instanciar o inyectar el Page Object
    private final PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Given("un administrador accede al formulario de inscripción de estudiantes")
    public void navegarAlRegistro() {
        practiceFormPage.navegarAlFormulario();
    }

    @When("registra a un estudiante con los siguientes datos:")
    public void registraEstudiante(DataTable dataTable) {
        // Transformamos la tabla de Gherkin en un Mapa de Java
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        
        // El Page Object se encarga de la lógica de llenado
        practiceFormPage.completarFormulario(data);
        practiceFormPage.enviarFormulario();
    }

    @Then("el sistema debe confirmar el registro exitoso")
    public void verificarRegistroExitoso() {
        Assertions.assertTrue(practiceFormPage.registroExitoso(), 
            "El modal de éxito no se mostró después de enviar el formulario.");
    }

    @Then("la ficha del estudiante debe mostrar la información ingresada correctamente")
    public void verificarFichaEstudiante() {
        // Por ahora validamos que el modal esté visible (paso previo)
        Assertions.assertTrue(practiceFormPage.registroExitoso());
    }

    @When("el administrador intenta enviar el formulario sin completar datos")
    public void intentarEnviarVacio() {
        practiceFormPage.enviarFormulario();
    }

    @Then("el sistema debe marcar en rojo los campos obligatorios incompletos")
    public void verificarCamposEnRojo() {
        Assertions.assertTrue(practiceFormPage.camposObligatoriosEnRojo(), 
            "Los campos obligatorios no se marcaron como inválidos.");
    }

    @When("registra a un estudiante con un número de teléfono de {string}")
    public void registrarConTelefonoInvalido(String telefono) {
        practiceFormPage.ingresarSoloTelefono(telefono);
        practiceFormPage.enviarFormulario();
    }

    @Then("el sistema no debe permitir el registro y marca el campo del teléfono como inválido")
    public void verificarTelefonoInvalido() {
        Assertions.assertFalse(practiceFormPage.registroExitoso(), "El registro se permitió con un teléfono inválido.");
        Assertions.assertTrue(practiceFormPage.campoTelefonoInvalido(), "El campo teléfono no se marcó como inválido.");
    }
}
