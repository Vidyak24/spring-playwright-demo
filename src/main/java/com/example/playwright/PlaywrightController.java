package com.example.playwright;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playwright")
public class PlaywrightController {

    private final PlaywrightService playwrightService;

    public PlaywrightController(PlaywrightService playwrightService) {
        this.playwrightService = playwrightService;
    }

    @GetMapping("/interact")
    public String interactWithPage() throws Exception {
        String url = "https://confluence.greenwayhealth.com/confluence/display/AUT/Bot+29+-+SharePoint+File+Status+Reporting";
        return playwrightService.fillFormAndGetValue(url);
    }
}
