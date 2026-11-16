package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BookStorePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By searchBox = By.id("searchBox");
    private final By loginButton = By.id("login");

    private final By bookRows =
            By.cssSelector(".rt-tbody .rt-tr-group");

    private final By bookTitleLinks =
            By.cssSelector(".rt-td:nth-child(2) a");

    private final By tableBody =
            By.cssSelector(".rt-tbody");

    private final By profileLink =
            By.cssSelector("a[href='/profile']");

    public BookStorePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );
    }

    // Search for a book by title or author
    public void searchBook(String searchText) {
        WebElement searchField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchBox)
        );

        searchField.clear();
        searchField.sendKeys(searchText);
    }

    // Clear the search field
    public void clearSearch() {
        WebElement searchField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchBox)
        );

        searchField.clear();
    }

    // Get all displayed book titles
    public List<String> getBookTitles() {
        List<String> titles = new ArrayList<>();

        for (WebElement row : driver.findElements(bookRows)) {
            List<WebElement> cells =
                    row.findElements(By.cssSelector(".rt-td"));

            if (cells.size() > 1) {
                String title = cells.get(1).getText().trim();

                if (!title.isEmpty()) {
                    titles.add(title);
                }
            }
        }

        return titles;
    }

    // Get all displayed book authors
    public List<String> getBookAuthors() {
        List<String> authors = new ArrayList<>();

        for (WebElement row : driver.findElements(bookRows)) {
            List<WebElement> cells =
                    row.findElements(By.cssSelector(".rt-td"));

            if (cells.size() > 2) {
                String author = cells.get(2).getText().trim();

                if (!author.isEmpty()) {
                    authors.add(author);
                }
            }
        }

        return authors;
    }

    // Check whether a book is displayed
    public boolean isBookDisplayed(String bookTitle) {
        return getBookTitles().contains(bookTitle);
    }

    // Click a book by its title
    public void clickBookByTitle(String bookTitle) {
        By bookLink = By.xpath(
                "//div[contains(@class,'rt-tbody')]"
                        + "//a[normalize-space()="
                        + xpathLiteral(bookTitle) + "]"
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(bookLink)
        ).click();
    }

    // Get the number of displayed books
    public int getBooksCount() {
        return getBookTitles().size();
    }

    // Check whether the book list is displayed
    public boolean isBookListDisplayed() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(tableBody)
        ).isDisplayed();
    }

    // Click the Login button
    public void clickLoginButton() {
        wait.until(
                ExpectedConditions.elementToBeClickable(loginButton)
        ).click();
    }

    // Navigate to the Profile page
    public void clickProfileLink() {
        wait.until(
                ExpectedConditions.elementToBeClickable(profileLink)
        ).click();
    }

    // Safely construct an XPath string literal
    private String xpathLiteral(String value) {
        if (!value.contains("'")) {
            return "'" + value + "'";
        }

        if (!value.contains("\"")) {
            return "\"" + value + "\"";
        }

        String[] parts = value.split("'", -1);
        StringBuilder result = new StringBuilder("concat(");

        for (int i = 0; i < parts.length; i++) {
            if (i > 0) {
                result.append(", \"'\", ");
            }

            result.append("'").append(parts[i]).append("'");
        }

        result.append(")");
        return result.toString();
    }
}
