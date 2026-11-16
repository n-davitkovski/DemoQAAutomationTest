package org.example;

import org.example.pages.UploadAndDownloadPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UploadAndDownloadTests{
    private WebDriver driver;
    private UploadAndDownloadPage uploadAndDownloadPage;

    private Path downloadDirectory;
    private Path uploadFilePath;

    @BeforeEach
    public void setUp() throws Exception {
        downloadDirectory = Paths.get(
                System.getProperty("java.io.tmpdir"),
                "demoqa-downloads"
        );

        java.nio.file.Files.createDirectories(downloadDirectory);

        // Configure Chrome to save downloaded files to our test directory.
        org.openqa.selenium.chrome.ChromeOptions options =
                new org.openqa.selenium.chrome.ChromeOptions();

        java.util.Map<String, Object> preferences = new java.util.HashMap<>();
        preferences.put(
                "download.default_directory",
                downloadDirectory.toAbsolutePath().toString()
        );
        preferences.put("download.prompt_for_download", false);
        preferences.put("download.directory_upgrade", true);

        options.setExperimentalOption("prefs", preferences);

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/upload-download");

        uploadAndDownloadPage = new UploadAndDownloadPage(driver);

        // Create a file for the upload test.
        uploadFilePath = downloadDirectory.resolve("upload-test.txt");
        java.nio.file.Files.writeString(
                uploadFilePath,
                "This is a test file for DemoQA."
        );
    }

    @Test
    public void verifyFileCanBeUploadedTest() {
        uploadAndDownloadPage.uploadFile(
                uploadFilePath.toAbsolutePath().toString()
        );

        assertTrue(
                uploadAndDownloadPage.isUploadedFilePathDisplayed(),
                "The uploaded file path should be displayed."
        );

        assertTrue(
                uploadAndDownloadPage.getUploadedFilePath()
                        .contains("upload-test.txt"),
                "The uploaded file name should be displayed correctly."
        );
    }

    @Test
    public void verifyDownloadButtonIsDisplayedTest() {
        assertTrue(
                uploadAndDownloadPage.isDownloadButtonDisplayed(),
                "The Download button should be displayed."
        );
    }

    @Test
    public void verifyFileCanBeDownloadedTest() throws Exception {
        // The downloaded file has a different name from our upload test file.
        Path downloadedFile =
                downloadDirectory.resolve("sampleFile.jpeg");

        // Remove an old copy so the test checks the current download.
        java.nio.file.Files.deleteIfExists(downloadedFile);

        uploadAndDownloadPage.clickDownloadButton();

        // Wait for the download to finish.
        long timeoutMillis = 15000;
        long startTime = System.currentTimeMillis();

        while (!java.nio.file.Files.exists(downloadedFile)
                && System.currentTimeMillis() - startTime < timeoutMillis) {
            Thread.sleep(200);
        }

        assertTrue(
                java.nio.file.Files.exists(downloadedFile),
                "The downloaded file should exist in the download directory."
        );

        assertTrue(
                java.nio.file.Files.size(downloadedFile) > 0,
                "The downloaded file should not be empty."
        );
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
