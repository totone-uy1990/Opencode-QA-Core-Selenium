package coreLocators;

import java.util.HashMap;
import java.util.Map;

public class LocatorRepository {
    private static final Map<String, Map<String, Locator>> CACHE = new HashMap<>();// guarda los localizadores en
                                                                                   // memoria

    public static Locator get(String page, String locatorName) {

        if (!CACHE.containsKey(page)) {
            CACHE.put(page, LocatorReader.read(page + ".json"));
        }

        return CACHE.get(page).get(locatorName);
    }
}
