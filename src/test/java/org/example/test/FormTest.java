package org.example.test;

import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FormTest {

    public WebDriver driver;
    public static String URL = "https://demoqa.com/automation-practice-form";
    public static String FIRST_NAME = "Marius";
    public static String LAST_NAME = "Cociug";
    public static String EMAIL = "mariuscociug096@gmail.com";
    public static String GENDER = "Male";
    public static String USER_NUMBER = "1234567890";
    public static String SUBJECT = "Maths";
    public static String HOBBY = "Music";
    public static String STATE = "Rajasthan";
    public static String CITY = "Jaipur";
    public static String DATE_OF_BIRTH = "22 May,2006";

//    @BeforeClass
//    public void beferoTest() {
//        driver = Driver.getAutoLocalDriver();
//        driver.manage().window().maximize();
//    }
@BeforeClass
public void beferoTest() {
    driver = Driver.getSelenoidDriver();
}


    @Test
    public void formTest() throws InterruptedException {
        driver.get(URL);

        FormPom form = new FormPom(driver);
        form.closeAdvert();
        form.setFirstName(FIRST_NAME);
        form.setLastName(LAST_NAME);
        form.scroLlToSubject();
        form.setUserEmail(EMAIL);
        form.setGender(GENDER);
        form.setUserNumber(USER_NUMBER);
        form.setDateOfBirthInput(DATE_OF_BIRTH);
        form.setSubjectsInput(SUBJECT);
        form.scroLlToSubject();
        form.setHobbies(HOBBY);
        form.setstate(STATE);
        form.setcity(CITY);
        form.clickSubmit();
        Thread.sleep(4000);

        String actualName = driver.findElement(By.xpath("//table//td[text()='Student Name']/../td[2]")).getText();
        String actualEmail = driver.findElement(By.xpath("//table//td[text()='Student Email']/../td[2]")).getText();
        String actualGender = driver.findElement(By.xpath("//table//td[text()='Gender']/../td[2]")).getText();
        String actualNumber = driver.findElement(By.xpath("//table//td[text()='Mobile']/../td[2]")).getText();
        String actualDate_Of_Birth = driver.findElement(By.xpath("//table//td[text()='Date of Birth']/../td[2]")).getText();
        String actualSubjects = driver.findElement(By.xpath("//table//td[text()='Subjects']/../td[2]")).getText();
        String actualHobbies = driver.findElement(By.xpath("//table//td[text()='Hobbies']/../td[2]")).getText();
        String actualState_City = driver.findElement(By.xpath("//table//td[text()='State and City']/../td[2]")).getText();

        Assert.assertEquals(actualState_City, STATE + " " + CITY);
        Assert.assertEquals(actualHobbies, HOBBY);
        Assert.assertEquals(actualSubjects, SUBJECT);
        Assert.assertEquals(actualDate_Of_Birth, DATE_OF_BIRTH);
        Assert.assertEquals(actualNumber, USER_NUMBER);
        Assert.assertEquals(actualGender, GENDER);
        Assert.assertEquals(actualEmail, EMAIL);
        Assert.assertEquals(actualName, FIRST_NAME + " " + LAST_NAME);

        System.out.println();
    }

    @AfterClass
    public void afterTest() {
        driver.quit();
    }
}