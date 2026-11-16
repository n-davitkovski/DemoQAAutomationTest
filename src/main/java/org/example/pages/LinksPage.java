package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LinksPage {

    private WebDriver driver;

    private final By homeLink = By.id("simpleLink");
    private final By createdLink = By.id("created");
    private final By noContentLink = By.id("no-content");
    private final By movedLink = By.id("moved");
    private final By badRequestLink = By.id("bad-request");
    private final By unauthorizedLink = By.id("unauthorized");
    private final By forbiddenLink = By.id("forbidden");
    private final By invalidUrlLink = By.id("invalid-url");

    private final By linkResponse = By.id("linkResponse");


    public LinksPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickHomeLink() {
        driver.findElement(homeLink).click();
    }

    public void clickCreatedLink() {
        driver.findElement(createdLink).click();
    }

    public void clickNoContentLink() {
        driver.findElement(noContentLink).click();
    }

    public void clickMovedLink() {
        driver.findElement(movedLink).click();
    }

    public void clickBadRequestLink() {
        driver.findElement(badRequestLink).click();
    }

    public void clickUnauthorizedLink() {
        driver.findElement(unauthorizedLink).click();
    }

    public void clickForbiddenLink() {
        driver.findElement(forbiddenLink).click();
    }

    public void clickInvalidUrlLink() {
        driver.findElement(invalidUrlLink).click();
    }

    public String getLinkResponse() {
        return driver.findElement(linkResponse).getText();
    }
}
