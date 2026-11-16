package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SliderPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By slider = By.cssSelector("input[type='range']");
    private final By sliderValue = By.id("sliderValue");

    public SliderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public boolean isSliderDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(slider)
        ).isDisplayed();
    }

    public String getSliderValue() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(sliderValue)
        ).getAttribute("value");
    }

    public void setSliderValue(int targetValue) {
        if (targetValue < 0 || targetValue > 100) {
            throw new IllegalArgumentException(
                    "Slider value must be between 0 and 100."
            );
        }

        WebElement sliderElement = wait.until(
                ExpectedConditions.elementToBeClickable(slider)
        );

        sliderElement.click();
        sliderElement.sendKeys(Keys.HOME);

        for (int i = 0; i < targetValue; i++) {
            sliderElement.sendKeys(Keys.ARROW_RIGHT);
        }

        wait.until(
                driver -> String.valueOf(targetValue).equals(
                        driver.findElement(sliderValue).getAttribute("value")
                )
        );
    }
}
