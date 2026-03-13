package pages.cnarios;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class ProductListPage extends BasePage {

    // Ubica el contenedor de la tarjeta por texto "Smartphone Stand" y navega a su botón 'Add to Cart'
    private final By btnAddToCartSmartphoneStand = By.xpath("//h6[text()='Smartphone Stand']/ancestor::div[contains(@class,'MuiPaper-root')]//button[text()='Add to Cart']");
    private final By btnCartIcon = By.xpath("//span[text()='Cart']/ancestor::li | //button[contains(@aria-label, 'cart')] | //span[contains(text(), 'Cart')]/ancestor::button | //div[contains(@class,'MuiBadge-root')]/ancestor::button");
    private final By tabTestCases = By.xpath("//button[contains(text(),'Test Cases')]");

    @Override
    protected WebElement getElement(By locator) {
        return getWebElement(locator);
    }

    public ProductListPage open(String url) {
        navigateTo(url);
        return this;
    }

    public ProductListPage closeTestCasesTab() {
        if (elementIsDisplayed(tabTestCases)) {
            clickElement(tabTestCases);
        }
        return this;
    }

    public ProductListPage addSmartphoneStandToCart() {
        clickElement(btnAddToCartSmartphoneStand);
        return this;
    }

    public CartPage goToCart() {
        clickElement(btnCartIcon);
        return new CartPage();
    }
}
