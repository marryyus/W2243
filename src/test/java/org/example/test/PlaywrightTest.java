package org.example.test;

import com.microsoft.playwright.*;
import org.testng.annotations.Test;

import java.nio.file.Paths;

public class PlaywrightTest {

    @Test
    public void runPlaywright() {
        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(true) // GitHub Actions = true
            );

            BrowserContext context = browser.newContext(
                    new Browser.NewContextOptions()
                            .setRecordVideoDir(Paths.get("videos"))     // ✔ corect
                            .setRecordVideoSize(1280, 720)              // rezoluție video
            );

            Page page = context.newPage();
            page.navigate("https://demoqa.com/automation-practice-form");

            System.out.println("Playwright merge!");

            // Închidem contextul pentru a salva video-ul
            context.close();
        }
    }
}
