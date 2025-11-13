package org.example.pom;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPom {

    public WebDriver driver;
    public JavascriptExecutor js;

    @FindBy(xpath = "//*[@id='firstName']")
    WebElement firstName;

    @FindBy(xpath = "//*[@id='lastName']")
    WebElement lastName;

    @FindBy(xpath = "//*[@id='userEmail']")
    WebElement userEmail;

    @FindBy(xpath = "//*[@id='userNumber']")
    WebElement userNumber;

    @FindBy(xpath = "//*[@id='subjectsInput']")
    WebElement subjectsInput;

    @FindBy(xpath = "//*[@id='state']")
    WebElement state;

    @FindBy(xpath = "//*[@id='city']")
    WebElement city;

    @FindBy(xpath = "//*[@id='dateOfBirthInput']")
    WebElement dateOfBirthInput;

    @FindBy(xpath = "//*[@id='submit']")
    WebElement submit;

    public FormPom(WebDriver driver) {
        this.driver = driver;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public void clickSubmit() {
        submit.click();
    }

    // ------------ FIX COMPLET PENTRU STATE (React Select) ------------
    public void setstate(String stateParam) {
        // Deschide dropdown-ul State
        state.click();

        // Accesează input-ul real al React-Select
        WebElement input = driver.findElement(By.id("react-select-3-input"));

        // Trimite textul statului
        input.sendKeys(stateParam);

        // Așteaptă opțiunea să apară și apasă ENTER
        input.sendKeys(Keys.ENTER);
    }


    // ------------ FIX COMPLET PENTRU CITY (React Select) ------------
    public void setcity(String cityParam) {
        // Deschide dropdown-ul City
        city.click();

        // Input-ul din React-Select pentru orașe
        WebElement inputCity = driver.findElement(By.id("react-select-4-input"));

        inputCity.sendKeys(cityParam);
        inputCity.sendKeys(Keys.ENTER);
    }


    public void setHobbies(String hobbyParam) {
        WebElement el = driver.findElement(By.xpath("//*[@id='hobbiesWrapper']//label[text()='" + hobbyParam + "']/../input"));
        el.sendKeys(" ");
    }

    public void setDateOfBirthInput(String dateOfBirthParam) {
        dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
        dateOfBirthInput.sendKeys(dateOfBirthParam);
        dateOfBirthInput.sendKeys(Keys.ENTER);
    }

    public void setSubjectsInput(String subjectsInputParam) {
        subjectsInput.clear();
        subjectsInput.sendKeys(subjectsInputParam);
        subjectsInput.sendKeys(Keys.ENTER);
    }

    public void setUserNumber(String userNumberParam) {
        userNumber.clear();
        userNumber.sendKeys(userNumberParam);
    }

    public void setGender(String genderParam) {
        WebElement el = driver.findElement(By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']"));
        el.click();
    }

    public void setUserEmail(String userEmailParam) {
        userEmail.clear();
        userEmail.sendKeys(userEmailParam);
    }

    public void setLastName(String lastNameParam) {
        lastName.clear();
        lastName.sendKeys(lastNameParam);
    }

    public void setFirstName(String firstNameParam) {
        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }

    public void closeAdvert() {
        try {
            js.executeScript("var elem=document.getElementById('adplus-anchor'); if(elem) elem.remove();");
        } catch (Exception ignored) {}

        try {
            js.executeScript("var elem=document.getElementsByTagName('footer')[0]; if(elem) elem.remove();");
        } catch (Exception ignored) {}
    }

    public void scroLlToSubject() {
        scrollToElement(subjectsInput);
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
