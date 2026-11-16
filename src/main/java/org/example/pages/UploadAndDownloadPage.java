package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadAndDownloadPage {

    private WebDriver driver;

    private final By downloadButton = By.id("downloadButton");

    private final By uploadFileInput = By.id("uploadFile");

    private final By uploadedFilePath = By.id("uploadedFilePath");

    public UploadAndDownloadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickDownloadButton() {
        driver.findElement(downloadButton).click();
    }

    public void uploadFile(String filePath) {
        driver.findElement(uploadFileInput).sendKeys(filePath);
    }

    public boolean isDownloadButtonDisplayed() {
        return driver.findElement(downloadButton).isDisplayed();
    }

    public boolean isUploadedFilePathDisplayed() {
        return driver.findElement(uploadedFilePath).isDisplayed();
    }

    public String getUploadedFilePath() {
        return driver.findElement(uploadedFilePath).getText();
    }
}
