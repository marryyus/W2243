package org.example.test;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class PlaywrightFormTest {

    @Test
    public void formTestPlaywright() {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions().setHeadless(true) // pe GitHub true
            );

            // înregistrează video în folderul videos/
            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setRecordVideoDir(java.nio.file.Paths.get("videos"))
                            .setRecordVideoSize(1280, 720)
            );

            Page page = context.newPage();

            // Accesează formularul
            page.navigate("https://demoqa.com/automation-practice-form");

            // 🔥 Șterge reclama + footer (OBLIGATORIU!!! altfel click intercepted)
            page.evaluate("document.querySelector('#fixedban')?.remove()");
            page.evaluate("document.querySelector('footer')?.remove()");

            // -------- Începem completarea formularului --------

            page.fill("#firstName", "Marius");
            page.fill("#lastName", "Cociug");
            page.fill("#userEmail", "mariuscociug096@gmail.com");

            page.click("label[for='gender-radio-1']"); // Male
            page.fill("#userNumber", "1234567890");

            // Data nașterii
            page.click("#dateOfBirthInput");
            page.fill("#dateOfBirthInput", "22 May 2006");
            page.keyboard().press("Enter");

            // Subjects
            page.fill("#subjectsInput", "Maths");
            page.keyboard().press("Enter");

            // Scroll la Hobbies
            page.locator("#hobbiesWrapper").scrollIntoViewIfNeeded();
            page.click("//label[text()='Music']");

            // Select State
            page.click("#state");
            page.fill("#react-select-3-input", "Rajasthan");
            page.keyboard().press("Enter");

            // Select City
            page.click("#city");
            page.fill("#react-select-4-input", "Jaipur");
            page.keyboard().press("Enter");

            // Submit
            page.click("#submit");

            // așteaptă 2 secunde să se vadă în video
            page.waitForTimeout(2000);

            // Închide contextul => salvează video
            context.close();
        }
    }
}
