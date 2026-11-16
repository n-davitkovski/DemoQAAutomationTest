package org.example;

import org.example.pages.ModalDialogsPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.*;


public class ModalDialogsTests extends BaseTest{
    private WebDriver driver;
    private ModalDialogsPage modalDialogsPage;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://demoqa.com/modal-dialogs");

        modalDialogsPage = new ModalDialogsPage(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifySmallModalOpensTest() {
        modalDialogsPage.clickSmallModalButton();

        assertTrue(
                modalDialogsPage.isSmallModalDisplayed(),
                "The small modal should be displayed."
                        );
    }

    @Test
    public void verifySmallModalDisplaysCorrectTitleTest() {
        modalDialogsPage.clickSmallModalButton();

        assertEquals(
                "Small Modal",
                modalDialogsPage.getSmallModalTitle(),
                "The small modal should display the correct title."
        );
    }

    @Test
    public void verifySmallModalDisplaysCorrectContentTest() {
        modalDialogsPage.clickSmallModalButton();

        assertTrue(
                modalDialogsPage.getSmallModalContent().contains(
                                        "This is a small modal. It has very less content"
                                ),
                "The small modal should display the expected content."
                        );
    }

    @Test
    public void verifySmallModalClosesTest() {
        modalDialogsPage.clickSmallModalButton();

        modalDialogsPage.clickSmallModalCloseButton();

        assertFalse(
                modalDialogsPage.isSmallModalDisplayed(),
                "The small modal should be closed."
                        );
    }

    @Test
    public void verifyLargeModalOpensTest() {
        modalDialogsPage.clickLargeModalButton();

        assertTrue(
                modalDialogsPage.isLargeModalDisplayed(),
                "The large modal should be displayed."
                        );
    }

    @Test
    public void verifyLargeModalDisplaysCorrectTitleTest() {
        modalDialogsPage.clickLargeModalButton();

        assertEquals(
                "Large Modal",
                modalDialogsPage.getLargeModalTitle(),
                "The large modal should display the correct title."
        );
    }

    @Test
    public void verifyLargeModalDisplaysCorrectContentTest() {
        modalDialogsPage.clickLargeModalButton();

        assertTrue(
                modalDialogsPage.getLargeModalContent().contains(
                                        "Lorem Ipsum"
                                ),
                "The large modal should display its expected content."
                        );
    }

    @Test
    public void verifyLargeModalClosesTest() {
        modalDialogsPage.clickLargeModalButton();

        modalDialogsPage.clickLargeModalCloseButton();

        assertFalse(
                modalDialogsPage.isLargeModalDisplayed(),
                "The large modal should be closed."
                        );
    }
}
