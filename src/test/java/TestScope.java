import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class TestScope extends BaseTest {
    private LoginPage loginPage;
    private ConsoleLogging consoleLogging;
    private final String EMAILORPHONE = "bgu39626@aegde.com";
    private final String PASSWORD = "qwerty123456";
    private final String STARTURL = "https://homsters.kz";
    private final String LOGINURL = "https://homsters.kz/account/login";
    private final String ASSERTLOGINMESSAGE = ", с возвращением. Последний раз вы искали:";

    public ArrayList<String> addDataInArrayListUrl() {
        ArrayList<String> listUrl = new ArrayList<String>();
        listUrl.add("https://homsters.kz/");
        listUrl.add("https://homsters.kz/estate/search");
        listUrl.add("https://homsters.kz/tau-development/abay-130");
        listUrl.add("https://homsters.kz/reviews");
        listUrl.add("https://homsters.kz/leya-komfort-review");
        return listUrl;
    }

    @BeforeMethod
    public void setUp() {
        loginPage = new LoginPage(driver);
        consoleLogging = new ConsoleLogging(driver);
    }

    @Test
    public void loginOnPage() {
        loginPage.goToURL(LOGINURL);
        loginPage.login(EMAILORPHONE, PASSWORD);
        Assert.assertTrue(loginPage.checkIsLoginSuccess(ASSERTLOGINMESSAGE));
        loginPage.logout();
    }

    @Test
    public void loginOnPageUsingPopup() {
        loginPage.goToURL(STARTURL);
        loginPage.loginFromPopup(EMAILORPHONE, PASSWORD);
        loginPage.logout();
    }

    @Test
    public void printErrorInConsole() {
        consoleLogging.setUp();
        for (String url : addDataInArrayListUrl()) {
            driver.get(url);
            consoleLogging.printLog();
            System.out.println("-----------------------------------------------");
        }
    }
}