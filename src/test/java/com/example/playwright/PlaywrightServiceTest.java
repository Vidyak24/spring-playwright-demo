package com.example.playwright;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PlaywrightServiceTest {

    private static Playwright playwright;
    private static Browser browser;

    @BeforeAll
    static void setUp() {
        boolean headless = Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS", "true"));
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(headless));
    }

    @AfterAll
    static void tearDown() {
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }

    @Test
    void testBrowserLaunch() {
        BrowserContext context = browser.newContext();
        Page page = context.newPage();
        page.navigate("https://example.com");
        String title = page.title();
        assertTrue(title.toLowerCase().contains("example"));
        context.close();
    }
}
