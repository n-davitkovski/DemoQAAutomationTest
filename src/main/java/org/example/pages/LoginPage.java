package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameField = By.id("userName");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login");
    private By logoutButton = By.id("submit");
    private By errorMessage = By.id("name");
    private By loggedInUsername = By.id("userName-value");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Actions

    public void enterUsername(String username) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameField));

        element.clear();
        element.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(passwordField));

        element.clear();
        element.sendKeys(password);
    }

    public void clickLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    // Verification methods

    public boolean isLoginFormDisplayed() {
        return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(usernameField))
                .isDisplayed();
    }

    public boolean isUserLoggedIn() {
        return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(loggedInUsername))
                .isDisplayed();
    }

    public boolean isErrorMessageDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isUsernameRequiredMessageDisplayed() {
        return driver.findElement(usernameField).getAttribute("class")
                .contains("is-invalid");
    }

    public boolean isPasswordRequiredMessageDisplayed() {
        return driver.findElement(passwordField).getAttribute("class")
                .contains("is-invalid");
    }
}
