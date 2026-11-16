package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrokenLinksImagesPage {

    private WebDriver driver;

    private final By validImage =
            By.xpath("//p[normalize-space()='Valid image']/following-sibling::img[1]");

    private final By brokenImage =
            By.xpath("//p[normalize-space()='Broken image']/following-sibling::img[1]");

    private final By validLink =
            By.xpath("//a[normalize-space()='Click Here for Valid Link']");

    private final By brokenLink =
            By.xpath("//a[normalize-space()='Click Here for Broken Link']");

    public BrokenLinksImagesPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isValidImageDisplayed() {
        WebElement image = driver.findElement(validImage);
        return image.isDisplayed();
    }

    public boolean isBrokenImageDisplayed() {
        WebElement image = driver.findElement(brokenImage);
        return image.isDisplayed();
    }

    public boolean isValidImageLoaded() {
        WebElement image = driver.findElement(validImage);

        return (Boolean) ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript(
                        "return arguments[0].complete && arguments[0].naturalWidth > 0;",
                        image
                );
    }

    public boolean isBrokenImageLoaded() {
        WebElement image = driver.findElement(brokenImage);

        return (Boolean) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].complete && arguments[0].naturalWidth > 0;",
                image
        );
    }

    public String getValidLinkHref() {
        return driver.findElement(validLink).getAttribute("href");
    }

    public String getBrokenLinkHref() {
        return driver.findElement(brokenLink).getAttribute("href");
    }

    public void clickValidLink() {
        driver.findElement(validLink).click();
    }

    public void clickBrokenLink() {
        driver.findElement(brokenLink).click();
    }

    public String getValidLinkUrl() {
        return driver.findElement(validLink).getAttribute("href");
    }

    public String getBrokenLinkUrl() {
        return driver.findElement(brokenLink).getAttribute("href");
    }


}
