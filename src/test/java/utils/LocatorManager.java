package utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class LocatorManager {

    private static final Map<String, JsonNode> cache = new HashMap<>();
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Obtiene un objeto By leyendo un archivo JSON de recursos.
     *
     * @param fileName Nombre del archivo JSON (ej. "practice_form.json")
     * @param locatorKey Etiqueta del locator dentro del JSON (ej. "firstNameInput")
     * @return Objeto By de Selenium
     */
    public static By get(String fileName, String locatorKey) {
        JsonNode root = getRootNode(fileName);
        JsonNode node = root.path(locatorKey);

        if (node.isMissingNode()) {
            throw new IllegalArgumentException("El locator clave '" + locatorKey + "' no existe en " + fileName);
        }

        String type = node.path("type").asText().toLowerCase();
        String value = node.path("value").asText();

        return switch (type) {
            case "id" -> By.id(value);
            case "css" -> By.cssSelector(value);
            case "xpath" -> By.xpath(value);
            case "name" -> By.name(value);
            case "classname" -> By.className(value);
            default -> throw new IllegalArgumentException("Tipo de locator no soportado: " + type);
        };
    }

    private static JsonNode getRootNode(String fileName) {
        if (!cache.containsKey(fileName)) {
            try (InputStream in = LocatorManager.class.getResourceAsStream("/locators/" + fileName)) {
                if (in == null) {
                    throw new RuntimeException("No se encontró el archivo de localizadores: /locators/" + fileName);
                }
                cache.put(fileName, mapper.readTree(in));
            } catch (Exception e) {
                throw new RuntimeException("Error cargando el archivo JSON de localizadores: " + fileName, e);
            }
        }
        return cache.get(fileName);
    }
}
