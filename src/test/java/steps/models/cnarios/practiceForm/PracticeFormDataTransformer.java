package steps.models.cnarios.practiceForm;

import io.cucumber.java.DataTableType;
import java.util.Map;

public class PracticeFormDataTransformer {
    
    @DataTableType
    public PracticeFormData transform(Map<String, String> entry) {
        PracticeFormData data = new PracticeFormData();
        data.setNombreCompleto(entry.get("Nombre Completo"));
        data.setEmail(entry.get("Email"));
        data.setGeneroStr(entry.get("Género"));
        data.setTelefono(entry.get("Teléfono"));
        data.setFechaNacimiento(entry.get("Fecha Nacimiento"));
        data.setMateriasStr(entry.get("Materias"));
        data.setHobbiesStr(entry.get("Hobbies"));
        data.setFoto(entry.get("Foto"));
        data.setDireccion(entry.get("Dirección"));
        data.setUbicacion(entry.get("Ubicación"));
        return data;
    }
}
