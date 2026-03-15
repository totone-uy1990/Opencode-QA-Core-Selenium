package steps.cnarios;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import assertions.CustomAssertions;
import pages.cnarios.PracticeFormPage;

import java.util.List;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PracticeFormSteps {

    private final PracticeFormPage practiceFormPage = new PracticeFormPage();

    @Given("un administrador accede al formulario de inscripción de estudiantes")
    public void un_administrador_accede_al_formulario_de_inscripción_de_estudiantes() {
        practiceFormPage.navigateToForm();
    }

    @When("registra a un estudiante con los siguientes datos:")
    public void registra_a_un_estudiante_con_los_siguientes_datos(DataTable dataTable) {
        // Convierte la tabla clave-valor (horizontal o vertical) en un Map
        Map<String, String> data = dataTable.asMaps().get(0);
        // Si la tabla viene con "Campo" y "Valor" (2 columnas)| en lugar de cabeceras, la aplanamos
        if (data.containsKey("Campo") && data.containsKey("Valor")) {
           data = dataTable.asLists().stream()
                   .skip(1) // Omitir headers
                   .collect(Collectors.toMap(e -> e.get(0), e -> e.get(1)));
        }
        
        practiceFormPage.fillForm(data);
        practiceFormPage.submitForm();
    }

    @Then("el sistema debe confirmar el registro exitoso")
    public void el_sistema_debe_confirmar_el_registro_exitoso() {
        CustomAssertions.assertTrue(practiceFormPage.isSuccessModalDisplayed(), "El modal de éxito no se mostró");
    }

    @Then("la ficha del estudiante debe mostrar la información ingresada correctamente")
    public void la_ficha_del_estudiante_debe_mostrar_la_información_ingresada_correctamente() {
        // Validación pendiente de las celdas del modal (se puede implementar si se piden selectores)
    }

    @When("el administrador intenta enviar el formulario sin completar datos")
    public void el_administrador_intenta_enviar_el_formulario_sin_completar_datos() {
        practiceFormPage.submitForm();
    }

    @Then("el sistema debe marcar en rojo los campos obligatorios incompletos")
    public void el_sistema_debe_marcar_en_rojo_los_campos_obligatorios_incompletos() {
       CustomAssertions.assertTrue(practiceFormPage.areRequiredFieldsInvalid(), "El formulario no activó la validación HTML5 para campos obligatorios");
    }

    @When("registra a un estudiante con un número de teléfono de {string}")
    public void registra_a_un_estudiante_con_un_número_de_teléfono_de(String invalidNumber) {
        practiceFormPage.fillForm(Map.of(
                "Nombre Completo", "Prueba Negativa",
                "Género", "Masculino",
                "Teléfono", invalidNumber
        ));
        practiceFormPage.submitForm();
    }

    @Then("el sistema no debe permitir el registro y marca el campo del teléfono como inválido")
    public void el_sistema_no_debe_permitir_el_registro_y_marca_el_campo_del_teléfono_como_inválido() {
        CustomAssertions.assertTrue(practiceFormPage.areRequiredFieldsInvalid(), "El formulario validó exitosamente con un teléfono inválido");
    }
}
