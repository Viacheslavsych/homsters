import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;


public class LoginPage extends BasePage {
    private By emailOrPhoneLocator = By.id("UserName");
    private By passwordLocator = By.id("Password");
    private By emailOrPhoneSecondaryLocator = By.className("js-user-name");
    private By passwordSecondaryLocator = By.className("js-user-password");
    private By submitPrimaryButtonLocator = By.cssSelector("button.btn.btn-primary.btn-full");
    private By submitSecondaryButtonLocator = By.cssSelector("button.b-button.b-button--full.b-button--confirm-secondary");
    private By assertLoginTextLocator = By.className("fsz16");
    private By loginMenuPopupButtonLocator = By.cssSelector("div.b-complex-header__button.b-complex-header__button--login.js-get-popup-autification");
    private By loginActiveMenuPopupButtonLocator = By.cssSelector("div.b-complex-header__button.b-complex-header__button--login-active.js-toogle-main-menu");
    private By logoutExitButtonLocator = By.cssSelector("body > div.b-general-intent.b-general-intent--full.b-general-menu.js-menu-general-container.b-general-menu--main-page > div.b-general-menu__wrapper.js-general-menu--wrapper.active > div.b-general-menu__container.js-general-menu--container.active > div:nth-child(3) > form > button");
    private By assertLoginSecondaryTextLocator = By.cssSelector("body > div.b-popup.js-popup.active > div > div.b-popup__content.js-popup__content > div > div.b-modal-authentication__tab-wrapper > div > div.b-modal-authentication__tab-content.active.js-autification-content > div.b-modal-authentication__sub-tab.active.js-autification-block > div > div.b-modal-authentication__form-wrapper > div");


    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage login(String emailOrPhone, String password) {
        driver.findElement(emailOrPhoneLocator).sendKeys(emailOrPhone);
        driver.findElement(passwordLocator).sendKeys(password);
        driver.findElement(submitPrimaryButtonLocator).click();
        return new LoginPage(driver);
    }

    public LoginPage loginFromPopup(String emailOrPhone, String password) {
        driver.findElement(loginMenuPopupButtonLocator).click();
        Assert.assertTrue(checkIsLoginPopupOpen("Войти на сайт"), "Войти на сайт");
        driver.findElement(emailOrPhoneSecondaryLocator).sendKeys(emailOrPhone);
        driver.findElement(passwordSecondaryLocator).sendKeys(password);
        driver.findElement(submitSecondaryButtonLocator).click();
        return new LoginPage(driver);
    }

    public void goToURL(String URL) {
        driver.get(URL);
    }

    public boolean checkIsLoginSuccess(String expectedLoginMessage) {
        String actualLoginMessage = driver.findElement(assertLoginTextLocator).getText();
        if (actualLoginMessage.contains(expectedLoginMessage)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIsLoginPopupOpen(String expectedLoginMessage) {
        String actualLoginMessage = driver.findElement(assertLoginSecondaryTextLocator).getText();
        if (actualLoginMessage.contains(expectedLoginMessage)) {
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        driver.findElement(loginActiveMenuPopupButtonLocator).click();
        driver.findElement(logoutExitButtonLocator).click();
    }
}