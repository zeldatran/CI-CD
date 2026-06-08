package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final List<By> usernameLocators = List.of(
            By.cssSelector("input[name='username']"),
            By.cssSelector("input[formcontrolname='username']"),
            By.cssSelector("input[type='text']"),
            By.cssSelector("input[placeholder*='Tài khoản']"),
            By.cssSelector("input[placeholder*='Username']")
    );

    private final List<By> passwordLocators = List.of(
            By.cssSelector("input[name='password']"),
            By.cssSelector("input[formcontrolname='password']"),
            By.cssSelector("input[type='password']"),
            By.cssSelector("input[placeholder*='Mật khẩu']"),
            By.cssSelector("input[placeholder*='Password']")
    );

    private final List<By> loginButtonLocators = List.of(
            By.cssSelector("button[type='submit']"),
            By.xpath("//button[contains(.,'Đăng nhập') or contains(.,'Login')]")
    );

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(25));
    }

    public void open() {
        driver.get("https://sinhvien1.tlu.edu.vn/#/login");
        wait.until(d -> d.getCurrentUrl().contains("login") || d.findElements(By.cssSelector("input")).size() > 0);
    }

    public void login(String username, String password) {
        type(firstVisible(usernameLocators), username);
        type(firstVisible(passwordLocators), password);
        firstClickable(loginButtonLocators).click();
    }

    public boolean isLoginSuccessful() {
        try {
            return wait.until(d -> !d.getCurrentUrl().contains("/login") && !d.getCurrentUrl().endsWith("#/login"));
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginFailed() {
        try {
            wait.withTimeout(Duration.ofSeconds(8)).until(d ->
                    d.getCurrentUrl().contains("login") ||
                    d.findElements(By.cssSelector(".alert, .error, .invalid-feedback, .toast, .mat-mdc-snack-bar-container, .swal2-popup")).size() > 0 ||
                    d.getPageSource().toLowerCase().contains("sai") ||
                    d.getPageSource().toLowerCase().contains("không đúng") ||
                    d.getPageSource().toLowerCase().contains("invalid"));
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            wait.withTimeout(Duration.ofSeconds(25));
        }
    }

    private WebElement firstVisible(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Cannot find visible element by provided locators");
    }

    private WebElement firstClickable(List<By> locators) {
        for (By locator : locators) {
            try {
                return wait.until(ExpectedConditions.elementToBeClickable(locator));
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("Cannot find clickable element by provided locators");
    }

    private void type(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }
}
