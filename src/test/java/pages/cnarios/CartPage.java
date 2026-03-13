package pages.cnarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class CartPage extends BasePage {

    private final By btnProceedToAddress = By.xpath("//button[contains(text(),'Proceed to Address')]");

    @Override
    protected WebElement getElement(By locator) {
        return getWebElement(locator);
    }

    public CheckoutPage proceedToAddress() {
        clickElement(btnProceedToAddress);
        return new CheckoutPage();
    }
}
