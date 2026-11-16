package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckBoxPage {

    private WebDriver driver;

    private By home = By.xpath("//span[@class='rct-title' and text()='Home']");

    private By homeCheckBoxInput = By.xpath("\"//span[@class='rct-title' and normalize-space()='Home']\" +\n" +
            "    \"/preceding-sibling::span[contains(@class,'rct-checkbox')]\" +\n" +
            "    \"//input[@type='checkbox']\"");

    private By desktop = By.xpath("//span[@class='rct-title' and text()='Desktop']");

    private By notes = By.xpath("//span[@class='rct-title' and text()='Notes']");

    private By commands = By.xpath("//span[@class='rct-title' and text()='Commands']");

    private By documents = By.xpath("//span[@class='rct-title' and text()='Documents']");

    private By workspace = By.xpath("//span[@class='rct-title' and text()='Workspace']");

    private By react = By.xpath("//span[@class='rct-title' and text()='React']");

    private By angular = By.xpath("//span[@class='rct-title' and text()='Angular']");

    private By vue = By.xpath("//span[@class='rct-title' and text()='Veu']");

    private By office = By.xpath("//span[@class='rct-title' and text()='Office']");

    private By publicCheckBox = By.xpath("//span[@class='rct-title' and text()='Public']");

    private By privateCheckBox = By.xpath("//span[@class='rct-title' and text()='Private']");

    private By classified = By.xpath("//span[@class='rct-title' and text()='Classified']");

    private By general = By.xpath("//span[@class='rct-title' and text()='General']");

    private By downloads = By.xpath("//span[@class='rct-title' and text()='Downloads']");

    private By wordFile = By.xpath("//span[@class='rct-title' and text()='Word File.doc']");

    private By excelFile = By.xpath("//span[@class='rct-title' and text()='Excel File.doc']");

    private By checkBoxPage = By.xpath("//*[@id=\"root\"]/div/div/div/div[2]/h1");

    public CheckBoxPage(WebDriver driver) {
        this.driver = driver;
    }

    private By expandButton(String name) {
        return By.xpath(
                "//span[@class='rct-title' and text()='" + name +
                        "']/preceding-sibling::button"
        );
    }

    public void expand(String name) {
        driver.findElement(expandButton(name)).click();
    }

    public void selectHome() {
        driver.findElement(home).click();
    }

    public void selectDesktop() {
        driver.findElement(desktop).click();
    }

    public void selectNotes() {
        driver.findElement(notes).click();
    }

    public void selectCommands() {
        driver.findElement(commands).click();
    }

    public void selectDocuments() {
        driver.findElement(documents).click();
    }

    public void selectWorkspace() {
        driver.findElement(workspace).click();
    }

    public void selectReact() {
        driver.findElement(react).click();
    }

    public void selectAngular() {
        driver.findElement(angular).click();
    }

    public void selectVue() {
        driver.findElement(vue).click();
    }

    public void selectOffice() {
        driver.findElement(office).click();
    }

    public void selectPublic() {
        driver.findElement(publicCheckBox).click();
    }

    public void selectPrivate() {
        driver.findElement(privateCheckBox).click();
    }

    public void selectClassified() {
        driver.findElement(classified).click();
    }

    public void selectGeneral() {
        driver.findElement(general).click();
    }

    public void selectDownloads() {
        driver.findElement(downloads).click();
    }

    public void selectWordFile() {
        driver.findElement(wordFile).click();
    }

    public void selectExcelFile() {
        driver.findElement(excelFile).click();
    }

    public void expandHome() {
        driver.findElement(home).click();
    }

    public void selectCheckbox(String desktop) {
        driver.findElement(homeCheckBoxInput).click();
    }

    public String getResultsText() {
        return driver.findElement(By.id("result")).getText();
    }

    public boolean isHomeSelected() {
        return driver.findElement(homeCheckBoxInput).isSelected();
    }

    public boolean isHomeExpanded() {
        return driver.findElement(expandButton(String.valueOf(home)))
                .getAttribute("aria-expanded")
                .equals("true");
    }
}
