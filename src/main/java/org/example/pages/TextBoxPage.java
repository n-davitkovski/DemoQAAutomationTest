package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TextBoxPage {

    private WebDriver driver;

    private By fullName = By.id("userName");
    private By email = By.id("userEmail");
    private By currentAddress = By.id("currentAddress");
    private By permanentAddress = By.id("permanentAddress");
    private By submitButton = By.id("submit");

    private By output = By.id("output");

    public TextBoxPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterFullName(String name) {
        driver.findElement(fullName).sendKeys(name);
    }

    public void enterEmail(String emailAddress) {
        driver.findElement(email).sendKeys(emailAddress);
    }

    public void enterCurrentAddress(String address) {
        driver.findElement(currentAddress).sendKeys(address);
    }

    public void enterPermanentAddress(String address) {
        driver.findElement(permanentAddress).sendKeys(address);
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public String getOutputText() {
        return driver.findElement(output).getText();
    }

    public boolean isOutputDisplayed() {
        return driver.findElement(output).isDisplayed();
    }

    public String getFullNameValue() {
        return driver.findElement(fullName).getAttribute("value");
    }

    public String getEmailValue() {
        return driver.findElement(email).getAttribute("value");
    }

    public String getCurrentAddressValue() {
        return driver.findElement(currentAddress).getAttribute("value");
    }

    public String getPermanentAddressValue() {
        return driver.findElement(permanentAddress).getAttribute("value");
    }


}
