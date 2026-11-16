package org.example.tests;

import org.example.BaseTest;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends BaseTest{
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        driver.get("https://demoqa.com/login");

        loginPage = new LoginPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyLoginPageIsDisplayedTest() {
        assertTrue(loginPage.isLoginFormDisplayed(),
                "Login form should be displayed.");
    }

    @Test
    public void successfulLoginTest() {
        loginPage.login("validUsername", "validPassword");

        assertTrue(loginPage.isUserLoggedIn(),
                "User should be logged in successfully.");
    }

    @Test
    public void unsuccessfulLoginWithIncorrectPasswordTest() {
        loginPage.login("validUsername", "incorrectPassword");

        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed.");

        assertEquals("Invalid username or password!",
                loginPage.getErrorMessage());
    }

    @Test
    public void unsuccessfulLoginWithIncorrectUsernameTest() {
        loginPage.login("incorrectUsername", "validPassword");

        assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed.");
    }

    @Test
    public void unsuccessfulLoginWithEmptyCredentialsTest() {
        loginPage.clickLogin();

        assertTrue(loginPage.isUsernameRequiredMessageDisplayed(),
                "Username required message should be displayed.");
    }

    @Test
    public void unsuccessfulLoginWithEmptyPasswordTest() {
        loginPage.enterUsername("validUsername");
        loginPage.clickLogin();

        assertTrue(loginPage.isPasswordRequiredMessageDisplayed(),
                "Password required message should be displayed.");
    }

    @Test
    public void unsuccessfulLoginWithEmptyUsernameTest() {
        loginPage.enterPassword("validPassword");
        loginPage.clickLogin();

        assertTrue(loginPage.isUsernameRequiredMessageDisplayed(),
                "Username required message should be displayed.");
    }

    @Test
    public void successfulLogoutTest() {
        loginPage.login("validUsername", "validPassword");

        assertTrue(loginPage.isUserLoggedIn(),
                "User should be logged in before logging out.");

        loginPage.clickLogout();

        assertTrue(loginPage.isLoginFormDisplayed(),
                "Login form should be displayed after logout.");
    }
}
