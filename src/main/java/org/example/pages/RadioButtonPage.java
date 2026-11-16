package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RadioButtonPage {

    private WebDriver driver;

    private By yesRadioButton = By.id("yesRadio");

    private By impressiveRadioButton = By.id("impressiveRadio");

    private By noRadioButton = By.id("noRadio");

    private By selectedResult = By.className("text-success");

    public RadioButtonPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectYes() {
        driver.findElement(yesRadioButton).click();
    }

    public void selectImpressive() {
        driver.findElement(impressiveRadioButton).click();
    }

    public void selectNo() {
        driver.findElement(noRadioButton).click();
    }

    public String getSelectedResult() {
        return driver.findElement(selectedResult).getText();
    }

    public boolean isYesSelected() {
        return driver.findElement(yesRadioButton).isSelected();
    }

    public boolean isImpressiveSelected() {
        return driver.findElement(impressiveRadioButton).isSelected();
    }

    public boolean isNoEnabled() {
        return driver.findElement(noRadioButton).isEnabled();
    }

    public String getResultText() {
        return driver.findElement(By.className("text-success")).getText();
    }
}
