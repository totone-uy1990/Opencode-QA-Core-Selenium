package coreLocators;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.Map;

public class LocatorReader {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static Map<String, Locator> read(String fileName) {
        try (InputStream is = LocatorReader.class.getResourceAsStream("/locators/" + fileName)) {
            if (is == null) {
                throw new RuntimeException("No se encontró el archivo de localizadores: /locators/" + fileName);
            }
            // Lee el JSON y lo convierte en un Mapa de objetos Locator
            return MAPPER.readValue(is, new TypeReference<Map<String, Locator>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Error al leer el archivo JSON: " + fileName, e);
        }
    }
}