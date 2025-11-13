package org.example.test;

import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.nio.file.Paths;

public class PlaywrightFormTest {

    @Test
    public void formTestPlaywright() {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true)
            );

            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setRecordVideoDir(Paths.get("videos/"))
                            .setRecordVideoSize(1280, 720)
            );

            Page page = context.newPage();

            page.navigate("https://demoqa.com/automation-practice-form");

            String FIRST_NAME = "Marius";
            String LAST_NAME = "Cociug";
            String EMAIL = "mariuscociug096@gmail.com";
            String GENDER = "Male";
            String USER_NUMBER = "1234567890";
            String SUBJECT = "Maths";
            String HOBBY = "Music";
            String STATE = "Rajasthan";
            String CITY = "Jaipur";
            String DATE_OF_BIRTH = "22 May,2006";

            page.fill("#firstName", FIRST_NAME);
            page.fill("#lastName", LAST_NAME);
            page.fill("#userEmail", EMAIL);

            page.click("label[for='gender-radio-1']");
            page.fill("#userNumber", USER_NUMBER);

            page.click("#dateOfBirthInput");
            page.fill("#dateOfBirthInput", DATE_OF_BIRTH);
            page.keyboard().press("Enter");

            page.fill("#subjectsInput", SUBJECT);
            page.keyboard().press("Enter");

            page.click("label[for='hobbies-checkbox-3']");

            page.click("#state");
            page.click("text=" + STATE);

            page.click("#city");
            page.click("text=" + CITY);

            page.click("#submit");

            Assert.assertEquals(page.textContent("//td[text()='Student Name']/following-sibling::td"),
                    FIRST_NAME + " " + LAST_NAME);

            Assert.assertEquals(page.textContent("//td[text()='Student Email']/following-sibling::td"),
                    EMAIL);

            Assert.assertEquals(page.textContent("//td[text()='Gender']/following-sibling::td"),
                    GENDER);

            Assert.assertEquals(page.textContent("//td[text()='Mobile']/following-sibling::td"),
                    USER_NUMBER);

            Assert.assertEquals(page.textContent("//td[text()='Date of Birth']/following-sibling::td"),
                    DATE_OF_BIRTH);

            Assert.assertEquals(page.textContent("//td[text()='Subjects']/following-sibling::td"),
                    SUBJECT);

            Assert.assertEquals(page.textContent("//td[text()='Hobbies']/following-sibling::td"),
                    HOBBY);

            Assert.assertEquals(page.textContent("//td[text()='State and City']/following-sibling::td"),
                    STATE + " " + CITY);

            context.close();
        }
    }
}
