package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ButtonsPage {

    private final WebDriver driver;
    private final Actions actions;

    private By doubleClickButton = By.id("doubleClickBtn");

    private By rightClickButton = By.id("rightClickBtn");

    private By clickButton = By.xpath("//button[text()='Click Me']");

    private By doubleClickMessage = By.id("doubleClickMessage");

    private By rightClickMessage = By.id("rightClickMessage");

    private By clickMessage = By.id("dynamicClickMessage");

    private final By dynamicClickMessage =
            By.id("dynamicClickMessage");
    private final By dynamicClickBtn =
            By.xpath("//button[text()='Click Me']");

    public ButtonsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void doubleClickButton() {
        WebElement button = driver.findElement(doubleClickButton);
        actions.doubleClick(button).perform();
    }

    public void rightClickButton() {
        WebElement button = driver.findElement(rightClickButton);
        actions.contextClick(button).perform();
    }

    public boolean isRightClickMessageDisplayed() {
        return driver.findElement(rightClickMessage).isDisplayed();
    }

    public void clickButton() {
        driver.findElement(clickButton).click();
    }

    public String getDoubleClickMessage() {
        return driver.findElement(doubleClickMessage).getText();
    }

    public String getRightClickMessage() {
        return driver.findElement(rightClickMessage).getText();
    }

    public String getClickMessage() {
        return driver.findElement(clickMessage).getText();
    }

    public void clickDynamicButton() {
        driver.findElement(dynamicClickBtn).click();
    }

    public boolean isDoubleClickMessageDisplayed() {
        return driver.findElement(doubleClickMessage).isDisplayed();
    }

    public boolean isDynamicClickMessageDisplayed() {
        return driver.findElement(dynamicClickMessage).isDisplayed();
    }

    public String getDynamicClickMessage() {
        return driver.findElement(dynamicClickMessage).getText();
    }
}
