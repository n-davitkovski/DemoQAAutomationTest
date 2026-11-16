package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ProfilePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By usernameValue = By.id("userName-value");
    private By booksTable = By.className("rt-table");
    private By bookTitles = By.cssSelector(".rt-tbody .rt-tr-group a");
    private By goToBookStoreButton = By.id("gotoStore");
    private By addToCollectionButton = By.cssSelector("#addNewRecordButton");
    private By deleteBookButton = By.id("delete-record-undefined");
    private By deleteAllBooksButton = By.xpath(
            "//button[contains(text(),'Delete All Books')]"
    );
    private By confirmDeleteButton = By.id("closeSmallModal-ok");
    private By logoutButton = By.id("submit");

    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    // Navigation methods

    public void openProfilePage() {
        driver.get("https://demoqa.com/profile");
    }

    public void clickGoToBookStore() {
        wait.until(ExpectedConditions.elementToBeClickable(goToBookStoreButton))
                .click();
    }

    // Book collection methods

    public void addFirstBookToCollection() {
        By firstBook = By.cssSelector(".rt-tbody .rt-tr-group a");

        wait.until(ExpectedConditions.elementToBeClickable(firstBook)).click();

        By addButton = By.xpath(
                "//button[contains(text(),'Add To Your Collection')]"
        );

        wait.until(ExpectedConditions.elementToBeClickable(addButton)).click();

        driver.switchTo().alert().accept();
    }

    public void deleteFirstBookFromCollection() {
        By deleteButton = By.cssSelector(
                ".rt-tbody .rt-tr-group button[title='Delete']"
        );

        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }

    public void confirmDeletion() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton))
                .click();

        driver.switchTo().alert().accept();
    }

    public void clickLogout() {
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }

    // Verification methods

    public boolean isProfilePageDisplayed() {
        return !driver.findElements(usernameValue).isEmpty();
    }

    public String getUsername() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(usernameValue)
        ).getText();
    }

    public boolean isBooksSectionDisplayed() {
        return !driver.findElements(booksTable).isEmpty();
    }

    public boolean isBookDisplayedInCollection() {
        List<WebElement> books = driver.findElements(bookTitles);

        return !books.isEmpty();
    }
}
