package pages.cnarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class CheckoutPage extends BasePage {

    // Locadores de Dirección
    private final By txtFirstName = By.name("firstName");
    private final By txtLastName = By.name("lastName");
    private final By txtAddress = By.name("address");
    private final By btnProceedToPayment = By.xpath("//button[contains(text(),'Proceed to Payment')]");

    // Locadores de Pago
    private final By btnPayNow = By.xpath("//button[contains(text(),'Pay Now')]");
    private final By btnCancel = By.xpath("//button[contains(text(),'Cancel')]");

    @Override
    protected WebElement getElement(By locator) {
        return getWebElement(locator);
    }

    public CheckoutPage fillBillingDetails(String firstName, String lastName, String address) {
        write(txtFirstName, firstName);
        write(txtLastName, lastName);
        write(txtAddress, address);
        return this;
    }

    public CheckoutPage enterAddress(String address) {
        write(txtAddress, address);
        return this;
    }

    public CheckoutPage clearAddress() {
        // Aprovechamos que write limpia internamente el campo en BasePage
        write(txtAddress, "");
        return this;
    }

    public boolean isProceedToPaymentEnabled() {
        return getWebElement(btnProceedToPayment).isEnabled();
    }

    public CheckoutPage proceedToPayment() {
        clickElement(btnProceedToPayment);
        return this;
    }

    public SuccessPage confirmPayment() {
        clickElement(btnPayNow);
        return new SuccessPage();
    }

    public SuccessPage cancelPayment() {
        clickElement(btnCancel);
        return new SuccessPage(); 
    }
}
