package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DatePickerPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By selectDateInput =
            By.id("datePickerMonthYearInput");

    private final By dateAndTimeInput =
            By.id("dateAndTimePickerInput");

    public DatePickerPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Check whether the Select Date field is displayed
    public boolean isSelectDateFieldDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectDateInput)
        ).isDisplayed();
    }

    // Check whether the Date And Time field is displayed
    public boolean isDateAndTimeFieldDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(dateAndTimeInput)
        ).isDisplayed();
    }

    // Enter a date in the Select Date field
    public void enterDate(String date) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(selectDateInput)
        );

        input.click();
        input.sendKeys(Keys.CONTROL, "a");
        input.sendKeys(date);
        input.sendKeys(Keys.ENTER);
    }

    // Get the selected date
    public String getSelectedDate() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectDateInput)
        ).getAttribute("value");
    }

    // Enter a date and time
    public void enterDateAndTime(String dateAndTime) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(dateAndTimeInput)
        );

        input.click();
        input.sendKeys(Keys.CONTROL, "a");
        input.sendKeys(dateAndTime);
        input.sendKeys(Keys.ENTER);
    }

    // Get the selected date and time
    public String getSelectedDateAndTime() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(dateAndTimeInput)
        ).getAttribute("value");
    }
}
