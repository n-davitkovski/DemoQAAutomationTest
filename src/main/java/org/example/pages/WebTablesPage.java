package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class WebTablesPage {

    private WebDriver driver;

    // Registration form fields
    private final By addButton = By.id("addNewRecordButton");
    private final By firstName = By.id("firstName");
    private final By lastName = By.id("lastName");
    private final By email = By.id("userEmail");
    private final By age = By.id("age");
    private final By salary = By.id("salary");
    private final By department = By.id("department");
    private final By submitButton = By.id("submit");

    private final By searchBox = By.id("searchBox");
    private final By tableRows =
            By.cssSelector(".rt-tbody .rt-tr-group");
    private final By tableCells = By.cssSelector(".rt-td");
    private final By editButton = By.cssSelector("span[title='Edit']");
    private final By deleteButton = By.cssSelector("span[title='Delete']");


    public WebTablesPage(WebDriver driver) {
        this.driver = driver;
    }


    // -------------------------
    // Add record
    // -------------------------

    public void clickAdd() {
        driver.findElement(addButton).click();
    }


    public void enterFirstName(String firstNameValue) {

        driver.findElement(firstName)
                .sendKeys(firstNameValue);
    }


    public void enterLastName(String lastNameValue) {

        driver.findElement(lastName)
                .sendKeys(lastNameValue);
    }


    public void enterEmail(String emailValue) {

        driver.findElement(email)
                .sendKeys(emailValue);
    }


    public void enterAge(int ageValue) {

        driver.findElement(age)
                .sendKeys(String.valueOf(ageValue));
    }


    public void enterSalary(int salaryValue) {

        driver.findElement(salary)
                .sendKeys(String.valueOf(salaryValue));
    }


    public void enterDepartment(String departmentValue) {

        driver.findElement(department)
                .sendKeys(departmentValue);
    }


    // -------------------------
    // Edit record
    // -------------------------

    public void clickEditForUser(String firstNameValue) {

        By editButton =
                By.xpath(
                        "//div[@role='row']" +
                                "[.//div[text()='" + firstNameValue + "']]" +
                                "//span[@title='Edit']"
                );

        driver.findElement(editButton).click();
    }


    // -------------------------
    // Delete record
    // -------------------------

    public void clickDeleteForUser(String firstNameValue) {

        By deleteButton =
                By.xpath(
                        "//div[@role='row']" +
                                "[.//div[text()='" + firstNameValue + "']]" +
                                "//span[@title='Delete']"
                );

        driver.findElement(deleteButton).click();
    }


    // -------------------------
    // Search
    // -------------------------




    public void searchUser(String searchText) {

        driver.findElement(searchBox)
                .clear();

        driver.findElement(searchBox)
                .sendKeys(searchText);
    }

    public int getRecordsCount() {
        List<WebElement> rows = driver.findElements(tableRows);
        int count = 0;

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(tableCells);

            boolean hasData = cells.stream()
                    .anyMatch(cell -> !cell.getText().trim().isEmpty());

            if (hasData) {
                count++;
            }
        }

        return count;
    }

    public void clickAddButton() {
        driver.findElement(addButton).click();
    }

    public void clickSubmit() {
        driver.findElement(submitButton).click();
    }

    public void clearFirstName() {
        driver.findElement(firstName).clear();
    }

    public void searchRecord(String searchText) {
        WebElement search = driver.findElement(searchBox);
        search.clear();
        search.sendKeys(searchText);
    }

    public boolean isRecordDisplayed(String uniqueValue) {
        return findRecordRow(uniqueValue) != null;
    }

    // Returns all text from the matching record row
    public String getRecordText(String uniqueValue) {
        WebElement row = findRecordRow(uniqueValue);

        if (row == null) {
            throw new AssertionError(
                    "No table record found with value: " + uniqueValue
            );
        }

        return row.getText();
    }

    public void clickEditRecord(String uniqueValue) {
        WebElement row = findRecordRow(uniqueValue);

        if (row == null) {
            throw new AssertionError(
                    "Cannot edit: no record found with value: " + uniqueValue
            );
        }

        row.findElement(editButton).click();
    }

    public void deleteRecord(String uniqueValue) {
        WebElement row = findRecordRow(uniqueValue);

        if (row == null) {
            throw new AssertionError(
                    "Cannot delete: no record found with value: " + uniqueValue
            );
        }

        row.findElement(deleteButton).click();
    }

    private WebElement findRecordRow(String uniqueValue) {
        List<WebElement> rows = driver.findElements(tableRows);

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(tableCells);

            for (WebElement cell : cells) {
                if (cell.getText().trim().equals(uniqueValue)) {
                    return row;
                }
            }
        }

        return null;
    }
}
