package pages.cnarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

import java.util.List;

public class LoginPage extends BasePage {
    private String TestLoginFlow = "https://www.cnarios.com/challenges/login-flow";

    private By userNameField = By.xpath("//label[text()='Username']/following-sibling::div//input");
    private By passwordField = By.xpath("//label[text()='Password']/following-sibling::div//input");
    private By loginButton = By.xpath("//button[text()='Login']");
    private By messageAdmin = By.xpath("//div[contains(@class, 'MuiAlert-message') and contains(., 'Success')]");
    private By dashboardLocator = By.xpath("//p[contains(text(), 'Dashboard')]");
    private By welcomeMessage = By.xpath("//h5[contains(text(), 'Welcome')]");
    private By alerts = By.cssSelector("div[role='alert']");

    public void navigateToTestLogin() {
        navigateTo(TestLoginFlow);
    }

    public void writeUsernameField(String words) {
        write(userNameField, words);
    }

    public void writePasswordField(String words) {
        write(passwordField, words);
    }

    public void clickButton() {
        clickElement(loginButton);
    }

    // validadores
    public String getAdminLoggedMessage() {
        return getTextOfWebElement(messageAdmin);
    }

    public WebElement adminMessageElement() {
        return getElement(messageAdmin);
    }

    public WebElement dashBoardElement() {
        return getElement(dashboardLocator);
    }

    public WebElement getMessageWelcome() {
        return getElement(welcomeMessage);
    }

    public List<WebElement> getErrorMessages() {
        return findElements(alerts);
    }

    @Override
    protected WebElement getElement(By locator) {
        return getWebElement(locator);
    }

}
