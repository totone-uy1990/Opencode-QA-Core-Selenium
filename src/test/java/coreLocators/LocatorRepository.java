package coreLocators;

import java.util.HashMap;
import java.util.Map;

public class LocatorRepository {
    private static final Map<String, Map<String, Locator>> CACHE = new HashMap<>();// guarda los localizadores en  memoria

    
    
    
//usamos simplemente esta clase para obtener los localizadores
//recibe como parametro el nombre de la pagina y el nombre del localizador
//utiliza el metodo read() de la clase LocatorReader para leer el archivo json
//utiliza el metodo get() de la clase LocatorReader para obtener el localizador
    public static Locator get(String page, String locatorName) {

        if (!CACHE.containsKey(page)) {
            CACHE.put(page, LocatorReader.read(page + ".json"));
        }

        return CACHE.get(page).get(locatorName);
    }
}
