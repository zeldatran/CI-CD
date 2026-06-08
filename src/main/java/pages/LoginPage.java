package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//button[contains(text(),'Đăng nhập')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void open() {
        driver.get("https://sinhvien1.tlu.edu.vn/#/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).clear();
        driver.findElement(usernameInput).sendKeys(username);

        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).clear();
        driver.findElement(passwordInput).sendKeys(password);

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public boolean isLoginSuccessful() {
        try {
            return wait.until(d ->
                    !d.getCurrentUrl().contains("#/login")
                            && !d.getCurrentUrl().contains("/login")
            );
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLoginFailed() {
        try {
            return wait.until(d ->
                    d.getCurrentUrl().contains("#/login")
                            || d.getPageSource().contains("Đăng nhập")
                            || d.getPageSource().toLowerCase().contains("sai")
                            || d.getPageSource().toLowerCase().contains("không")
            );
        } catch (Exception e) {
            return false;
        }
    }
}