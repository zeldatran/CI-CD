package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.chrome.ChromeOptions;
import trannguyendiemhanh.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    private static final String USERNAME = "2351067093";
    private static final String PASSWORD = "079305013483TNDh";

    @BeforeMethod
    public void setup() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testSuccessfulLogin1() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);

        Assert.assertTrue(
                loginPage.isLoginSuccessful()
        );
    }

    @Test
    public void testSuccessfulLogin2() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);

        Assert.assertTrue(
                loginPage.isLoginSuccessful()
        );
    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
