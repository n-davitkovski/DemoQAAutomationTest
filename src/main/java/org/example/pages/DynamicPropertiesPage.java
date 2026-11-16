package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicPropertiesPage {

    private WebDriver driver;

    private By enabledAfter5Seconds = By.id("enableAfter");
    private By colorChange = By.id("colorChange");
    private By visibleAfter5Seconds = By.id("visibleAfter");

    private final By enableAfterButton = By.id("enableAfter");
    private final By colorChangeButton = By.id("colorChange");
    private final By visibleAfterButton = By.id("visibleAfter");

    public DynamicPropertiesPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isEnabledAfter5Seconds() {
        return driver.findElement(enabledAfter5Seconds).isEnabled();
    }

    public String getColorChangeColor() {
        return driver.findElement(colorChange).getCssValue("color");
    }

    public boolean isVisibleAfter5Seconds() {
        return driver.findElement(visibleAfter5Seconds).isDisplayed();
    }

    public boolean isEnableAfterButtonEnabled() {
        return driver.findElement(enableAfterButton).isEnabled();
    }

    public String getColorChangeButtonColor() {
        return driver.findElement(colorChangeButton)
                .getCssValue("color");
    }

    public boolean isVisibleAfterButtonDisplayed() {
        return driver.findElement(visibleAfterButton).isDisplayed();
    }
}
