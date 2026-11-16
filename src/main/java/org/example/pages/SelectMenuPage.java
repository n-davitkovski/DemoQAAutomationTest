package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SelectMenuPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle = By.className("main-header");

    // React Select controls
    private By selectValueInput = By.id("react-select-2-input");
    private By selectOneInput = By.id("react-select-3-input");
    private By multiSelectInput = By.id("react-select-4-input");

    private By selectValueContainer = By.id("withOptGroup");
    private By selectOneContainer = By.id("selectOne");
    private By multiSelectContainer = By.id("selectMenuContainer");

    // Old style select
    private By oldStyleSelect = By.id("oldSelectMenu");

    // Standard multi-select
    private By standardMultiSelect = By.id("cars");

    public SelectMenuPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public boolean isPageDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle)
        ).getText().equals("Select Menu");
    }

    // Select Value

    public void selectValue(String option) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(selectValueInput)
        );

        input.sendKeys(option);

        By optionLocator = By.xpath(
                "//div[contains(@class,'-option') and normalize-space()='" + option + "']"
        );

        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    public String getSelectedValue() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectValueContainer)
        ).getText();
    }

    // Select One

    public void selectOne(String option) {
        WebElement input = wait.until(
                ExpectedConditions.elementToBeClickable(selectOneInput)
        );

        input.sendKeys(option);

        By optionLocator = By.xpath(
                "//div[contains(@class,'-option') and normalize-space()='" + option + "']"
        );

        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    public String getSelectedOne() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(selectOneContainer)
        ).getText();
    }

    // Old Style Select Menu

    public void selectOldStyleOption(String option) {
        WebElement element = wait.until(
                ExpectedConditions.elementToBeClickable(oldStyleSelect)
        );

        Select select = new Select(element);
        select.selectByVisibleText(option);
    }

    public String getOldStyleSelectedOption() {
        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOfElementLocated(oldStyleSelect))
        );

        return select.getFirstSelectedOption().getText();
    }

    // Multiselect Drop Down

    public void selectMultipleColors(String... colors) {
        for (String color : colors) {
            WebElement input = wait.until(
                    ExpectedConditions.elementToBeClickable(multiSelectInput)
            );

            input.sendKeys(color);

            By optionLocator = By.xpath(
                    "//div[contains(@class,'-option') and normalize-space()='" + color + "']"
            );

            wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
        }
    }

    public String getSelectedMultipleColors() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(multiSelectContainer)
        ).getText();
    }

    public void removeMultipleColor(String color) {
        By removeButton = By.cssSelector(
                "[aria-label='Remove " + color + "']"
        );

        wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
    }

    // Standard Multi Select

    public void selectStandardMultipleOptions(String... options) {
        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOfElementLocated(standardMultiSelect))
        );

        for (String option : options) {
            select.selectByVisibleText(option);
        }
    }

    public List<String> getStandardSelectedOptions() {
        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOfElementLocated(standardMultiSelect))
        );

        List<String> selectedOptions = new ArrayList<>();

        for (WebElement option : select.getAllSelectedOptions()) {
            selectedOptions.add(option.getText());
        }

        return selectedOptions;
    }

    public void deselectStandardOption(String option) {
        Select select = new Select(
                wait.until(ExpectedConditions.visibilityOfElementLocated(standardMultiSelect))
        );

        select.deselectByVisibleText(option);
    }
}
