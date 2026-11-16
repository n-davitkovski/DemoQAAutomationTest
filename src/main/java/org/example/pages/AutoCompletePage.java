package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class AutoCompletePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Input fields
    private final By singleColorInput = By.id("autoCompleteSingleInput");
    private final By multipleColorsInput = By.id("autoCompleteMultipleInput");

    // Selected values
    private final By selectedSingleColor =
            By.cssSelector(".auto-complete__single-value");

    private final By selectedMultipleColors =
            By.cssSelector(".auto-complete__multi-value__label");

    // Suggestions
    private final By suggestions =
            By.cssSelector(".auto-complete__option");

    // Remove buttons for selected multiple colors
    private final By removeColorButtons =
            By.cssSelector(".auto-complete__multi-value__remove");

    public AutoCompletePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Select a color in the single-color field
    public void selectSingleColor(String color) {
        WebElement input = wait.until(
                elementToBeClickable(singleColorInput)
        );

        input.click();
        input.sendKeys(color);

        selectSuggestion(color);
    }

    // Select a color in the multiple-colors field
    public void selectMultipleColor(String color) {
        WebElement input = wait.until(
                elementToBeClickable(multipleColorsInput)
        );

        input.click();
        input.sendKeys(color);

        selectSuggestion(color);
    }

    // Select the matching autocomplete suggestion
    private void selectSuggestion(String color) {
        By matchingSuggestion = By.xpath(
                "//div[contains(@class,'auto-complete__option')"
                        + " and normalize-space()='" + color + "']"
        );

        wait.until(
                elementToBeClickable(matchingSuggestion)
        ).click();
    }

    // Get the selected single color
    public String getSelectedSingleColor() {
        List<WebElement> values = driver.findElements(selectedSingleColor);

        if (values.isEmpty()) {
            return "";
        }

        return values.get(0).getText().trim();
    }

    // Get all selected colors
    public List<String> getSelectedMultipleColors() {
        return driver.findElements(selectedMultipleColors)
                .stream()
                .map(element -> element.getText().trim())
                .toList();
    }

    // Remove a specific color from the multiple-color field
    public void removeMultipleColor(String color) {
        List<WebElement> labels = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                        selectedMultipleColors
                )
        );

        for (int i = 0; i < labels.size(); i++) {
            if (labels.get(i).getText().trim().equals(color)) {
                List<WebElement> removeButtons =
                        driver.findElements(removeColorButtons);

                wait.until(
                        elementToBeClickable(removeButtons.get(i))
                ).click();

                return;
            }
        }

        throw new AssertionError(
                "The selected color was not found: " + color
        );
    }

    // Clear the single-color selection
    public void clearSingleColor() {
        List<WebElement> values = driver.findElements(selectedSingleColor);

        if (!values.isEmpty()) {
            WebElement input = wait.until(
                    elementToBeClickable(singleColorInput)
            );

            input.click();
            input.sendKeys(Keys.CONTROL, "a");
            input.sendKeys(Keys.BACK_SPACE);
        }
    }

    // Clear all selected colors
    public void clearMultipleColors() {
        WebElement input = wait.until(
                elementToBeClickable(multipleColorsInput)
        );

        input.click();
        input.sendKeys(Keys.CONTROL, "a");
        input.sendKeys(Keys.BACK_SPACE);

        wait.until(
                ExpectedConditions.numberOfElementsToBe(
                        selectedMultipleColors, 0
                )
        );
    }
}
