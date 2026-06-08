package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

@Feature("TLU Student Login")
public class LoginTest extends BaseTest {

    private String username() {
        String value = System.getProperty("tlu.username", System.getenv("TLU_USERNAME"));
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing TLU_USERNAME. Add it as environment variable or GitHub Secret.");
        }
        return value;
    }

    private String password() {
        String value = System.getProperty("tlu.password", System.getenv("TLU_PASSWORD"));
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Missing TLU_PASSWORD. Add it as environment variable or GitHub Secret.");
        }
        return value;
    }

    @Test(description = "Login with valid account")
    @Description("Nhập đúng tài khoản và mật khẩu, kỳ vọng đăng nhập thành công.")
    public void loginWithCorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(username(), password());
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Đăng nhập đúng mật khẩu nhưng không thành công.");
    }

    @Test(description = "Login with wrong password")
    @Description("Nhập đúng tài khoản nhưng sai mật khẩu, kỳ vọng đăng nhập thất bại.")
    public void loginWithWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(username(), password() + "_wrong");
        Assert.assertTrue(loginPage.isLoginFailed(), "Đăng nhập sai mật khẩu nhưng hệ thống không báo thất bại.");
    }
}
