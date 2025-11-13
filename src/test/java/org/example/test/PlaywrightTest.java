package org.example.test;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

public class PlaywrightTest {

    @Test
    public void runPlaywright() {
        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setHeadless(true)); // pe GitHub trebuie true

            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir("videos/")   // folder local pentru video
                    .setRecordVideoSize(1280, 720)  // rezoluție video
            );

            Page page = context.newPage();
            page.navigate("https://demoqa.com/automation-practice-form");

            System.out.println("Playwright merge!");

            context.close(); // salvează video la închidere
        }
    }
}
