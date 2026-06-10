package trannguyendiemhanh;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void open() {
        driver.get("https://sinhvien1.tlu.edu.vn/#/login");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input")));
    }

    public void login(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        List<WebElement> inputs = wait.until(d -> {
            List<WebElement> visibleInputs = d.findElements(By.cssSelector("input")).stream()
                    .filter(WebElement::isDisplayed)
                    .toList();
            return visibleInputs.size() >= 2 ? visibleInputs : null;
        });

        WebElement usernameInput = inputs.get(0);
        WebElement passwordInput = inputs.get(1);

        typeValue(usernameInput, username);
        typeValue(passwordInput, password);

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button")));
        loginButton.click();
    }

    private void typeValue(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));"
                        + "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
                element);
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