package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import trannguyendiemhanh.LoginPage;

public class LoginTest extends BaseTest {

    private final String username = "2351067093";
    private final String password = "079305013483TNDh";

    @Test(description = "Login with valid account")
    public void loginWithCorrectPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(username, password);

        Assert.assertTrue(loginPage.isLoginSuccessful(), "Đăng nhập đúng mật khẩu thất bại");
    }

    @Test(description = "Login with the same valid account again")
    public void loginWithCorrectPasswordAgain() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(username, password);

        Assert.assertFalse(loginPage.isLoginFailed(), "Lần đăng nhập đúng nhưng hệ thống báo lỗi");
    }

    @Test(description = "Login with wrong password")
    public void loginWithWrongPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(username, "SaiMatKhau123");

        Assert.assertTrue(loginPage.isLoginFailed(), "Đăng nhập sai mật khẩu nhưng hệ thống không báo lỗi");
    }

    @Test(description = "Login with wrong username")
    public void loginWithWrongUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login("invaliduser@example.com", password);

        Assert.assertTrue(loginPage.isLoginFailed(), "Đăng nhập sai tài khoản nhưng hệ thống không báo lỗi");
    }
}