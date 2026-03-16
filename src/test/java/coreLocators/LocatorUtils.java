package coreLocators;

import org.openqa.selenium.By;

public class LocatorUtils {

    public static By getBy(Locator locator) {
        if (locator == null) {
            throw new IllegalArgumentException("El objeto Locator no puede ser nulo");
        }

        String type = locator.getType().toLowerCase();
        String value = locator.getValue();

        return switch (type) {
            case "id" -> By.id(value);
            case "name" -> By.name(value);
            case "css" -> By.cssSelector(value);
            case "xpath" -> By.xpath(value);
            case "classname", "class" -> By.className(value);
            case "linktext" -> By.linkText(value);
            default -> throw new IllegalArgumentException("Tipo de localizador no soportado: " + type);
        };
    }
}
