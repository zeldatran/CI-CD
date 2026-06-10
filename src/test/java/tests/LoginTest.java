package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    private final String username = "2351067093";
    private final String password = "079305013483";

    @Test(description = "Login with valid account")
    public void loginWithCorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(username, password);

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Đăng nhập đúng mật khẩu thất bại");
    }

    @Test(description = "Login with wrong password")
    public void loginWithWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(username, "SaiMatKhau123");

        Assert.assertTrue(loginPage.isLoginFailed(), "Đăng nhập sai mật khẩu nhưng hệ thống không báo lỗi");
    }
}