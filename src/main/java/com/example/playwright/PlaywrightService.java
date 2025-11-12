package com.example.playwright;

import com.microsoft.playwright.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlaywrightService {

    @Value("${playwright.headless:true}")
    private boolean headless;

    public String fillFormAndGetValue(String url) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
            BrowserContext context = browser.newContext();
            Page page = context.newPage();
            page.navigate(url);

            String title = page.title();

            context.close();
            browser.close();
            return "Page title: " + title;
        } catch (Exception e) {
            return "Error interacting with page: " + e.getMessage();
        }
    }
}
