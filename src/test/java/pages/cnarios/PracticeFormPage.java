package pages.cnarios;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.BasePage;
import utils.LocatorManager;

import java.util.Map;

public class PracticeFormPage extends BasePage {

    private static final String LOCATORS_FILE = "practice_form.json";

    public PracticeFormPage() {
        // Constructor vacío ya que el driver se obtiene estáticamente del ThreadLocal
    }

    @Override
    protected org.openqa.selenium.WebElement getElement(By locator) {
        return getWebElement(locator);
    }

    private By getLocator(String key) {
        return LocatorManager.get(LOCATORS_FILE, key);
    }

    public void navigateToForm() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void fillForm(Map<String, String> data) {
        if (data.containsKey("Nombre Completo")) {
            String[] names = data.get("Nombre Completo").split(" ");
            write(getLocator("firstNameInput"), names[0]);
            if (names.length > 1) {
                write(getLocator("lastNameInput"), names[1]);
            }
        }
        if (data.containsKey("Email")) write(getLocator("userEmailInput"), data.get("Email"));
        if (data.containsKey("Género")) {
            switch (data.get("Género").toLowerCase()) {
                case "masculino": clickElement(getLocator("genderMaleRadio")); break;
                case "femenino": clickElement(getLocator("genderFemaleRadio")); break;
                default: clickElement(getLocator("genderOtherRadio")); break;
            }
        }
        if (data.containsKey("Teléfono")) write(getLocator("userNumberInput"), data.get("Teléfono"));
        
        if (data.containsKey("Fecha Nacimiento")) {
            clickElement(getLocator("dateOfBirthInput"));
            getWebElement(getLocator("dateOfBirthInput")).sendKeys(Keys.ESCAPE);
        }
        
        if (data.containsKey("Materias")) {
            String[] subjects = data.get("Materias").split(", ");
            WebElement subjectEl = getWebElement(getLocator("subjectsInput"));
            for (String subject : subjects) {
                subjectEl.sendKeys(subject);
                subjectEl.sendKeys(Keys.ENTER);
            }
        }
        
        if (data.containsKey("Hobbies")) {
            scrollIntoView(getLocator("hobbiesSportsCheckbox"));
            if (data.get("Hobbies").contains("Sports")) clickElement(getLocator("hobbiesSportsCheckbox"));
            if (data.get("Hobbies").contains("Reading")) clickElement(getLocator("hobbiesReadingCheckbox"));
            if (data.get("Hobbies").contains("Music")) clickElement(getLocator("hobbiesMusicCheckbox"));
        }

        if (data.containsKey("Dirección")) write(getLocator("currentAddressTextarea"), data.get("Dirección"));
        
        if (data.containsKey("Ubicación")) {
            scrollIntoView(getLocator("submitButton"));
            String[] location = data.get("Ubicación").split(" - ");
            if (location.length > 0) {
                 getWebElement(getLocator("stateInput")).sendKeys(location[0]);
                 getWebElement(getLocator("stateInput")).sendKeys(Keys.ENTER);
            }
            if (location.length > 1) {
                 getWebElement(getLocator("cityInput")).sendKeys(location[1]);
                 getWebElement(getLocator("cityInput")).sendKeys(Keys.ENTER);
            }
        }
    }

    private void scrollIntoView(By locator) {
        ((org.openqa.selenium.JavascriptExecutor) getDriverFromThread()).executeScript("arguments[0].scrollIntoView(true);", getWebElement(locator));
    }

    public void submitForm() {
        ((org.openqa.selenium.JavascriptExecutor) getDriverFromThread()).executeScript("arguments[0].click();", getWebElement(getLocator("submitButton")));
    }

    public boolean isSuccessModalDisplayed() {
        return elementIsDisplayed(getLocator("successModalTitle"));
    }
    
    public boolean areRequiredFieldsInvalid() {
        return Boolean.parseBoolean(getWebElement(getLocator("firstNameInput")).getAttribute("required")) &&
               elementIsDisplayed(getLocator("wasValidatedForm"));
    }
}
