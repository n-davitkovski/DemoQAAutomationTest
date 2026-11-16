package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PracticeFormPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Text fields
    private final By firstNameField = By.id("firstName");
    private final By lastNameField = By.id("lastName");
    private final By emailField = By.id("userEmail");
    private final By mobileField = By.id("userNumber");
    private final By addressField = By.id("currentAddress");

    // Gender
    private final By maleGender = By.cssSelector("label[for='gender-radio-1']");
    private final By femaleGender = By.cssSelector("label[for='gender-radio-2']");
    private final By otherGender = By.cssSelector("label[for='gender-radio-3']");

    // Date of birth
    private final By dateOfBirthField = By.id("dateOfBirthInput");

    // Subjects
    private final By subjectsField = By.id("subjectsInput");

    // Hobbies
    private final By sportsHobby =
            By.cssSelector("label[for='hobbies-checkbox-1']");
    private final By readingHobby =
            By.cssSelector("label[for='hobbies-checkbox-2']");
    private final By musicHobby =
            By.cssSelector("label[for='hobbies-checkbox-3']");

    // Picture upload
    private final By pictureUpload = By.id("uploadPicture");

    // State and city
    private final By stateDropdown = By.id("state");
    private final By cityDropdown = By.id("city");

    // Submit button
    private final By submitButton = By.id("submit");

    // Confirmation modal
    private final By confirmationModal =
            By.id("example-modal-sizes-title-lg");
    private final By confirmationTable =
            By.className("table-responsive");
    private final By closeModalButton =
            By.id("closeLargeModal");


    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void enterFirstName(String firstNameValue) {
        driver.findElement(firstNameField)
                .sendKeys(firstNameValue);
    }


    public void enterLastName(String lastNameValue) {
        driver.findElement(lastNameField)
                .sendKeys(lastNameValue);
    }


    public void enterEmail(String emailValue) {
        driver.findElement(emailField)
                .sendKeys(emailValue);
    }


    public void selectGender(String gender) {

        switch (gender.toLowerCase()) {

            case "male":
                driver.findElement(maleGender).click();
                break;

            case "female":
                driver.findElement(femaleGender).click();
                break;

            case "other":
                driver.findElement(otherGender).click();
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid gender: " + gender
                );
        }
    }


    public void enterMobile(String mobile) {
        driver.findElement(mobileField).sendKeys(mobile);
    }


    public void enterDateOfBirth(String date) {
        WebElement dateField = wait.until(
                ExpectedConditions.elementToBeClickable(dateOfBirthField));

        dateField.click();
        dateField.clear();
        dateField.sendKeys(date);
        dateField.sendKeys(org.openqa.selenium.Keys.ENTER);
    }


    public void enterSubject(String subject) {
        WebElement field = wait.until(
                ExpectedConditions.visibilityOfElementLocated(subjectsField));

        field.sendKeys(subject);

        By suggestion = By.xpath(
                "//div[contains(@class,'subjects-auto-complete__option')"
                        + " and normalize-space()='" + subject + "']");

        wait.until(ExpectedConditions.elementToBeClickable(suggestion)).click();
    }


    public void selectHobby(String hobby) {

        switch (hobby.toLowerCase()) {

            case "sports":
                driver.findElement(sportsHobby).click();
                break;

            case "reading":
                driver.findElement(readingHobby).click();
                break;

            case "music":
                driver.findElement(musicHobby).click();
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid hobby: " + hobby
                );
        }
    }


    public void uploadPicture(String filePath) {
        driver.findElement(pictureUpload).sendKeys(filePath);
    }

    public String getUploadedPictureName() {
        return driver.findElement(pictureUpload)
                .getAttribute("value");
    }


    public void enterAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }


    public void selectState(String stateName) {

        driver.findElement(stateDropdown)
                .click();

        By stateOption =
                By.xpath(
                        "//div[contains(@id,'react-select-3-option') " +
                                "and text()='" + stateName + "']"
                );

        driver.findElement(stateOption)
                .click();
    }


    public void selectCity(String cityName) {

        driver.findElement(cityDropdown)
                .click();

        By cityOption =
                By.xpath(
                        "//div[contains(@id,'react-select-4-option') " +
                                "and text()='" + cityName + "']"
                );

        driver.findElement(cityOption)
                .click();
    }

    public boolean isConfirmationModalDisplayed() {
        return !driver.findElements(confirmationModal).isEmpty()
                && driver.findElement(confirmationModal).isDisplayed();
    }

    public String getConfirmationTableText() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        confirmationTable)).getText();
    }

    public void closeConfirmationModal() {
        wait.until(ExpectedConditions.elementToBeClickable(closeModalButton))
                .click();
    }


    public void submitForm() {
        WebElement button = driver.findElement(submitButton);

        // Avoid clicking an advertisement or another element
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", button);
    }
}
