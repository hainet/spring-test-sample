package com.hainet.spring.test.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntroductionController {

    private final IntroductionService service;

    public IntroductionController(final IntroductionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/introduce")
    @ResponseBody
    public String introduce() {
        return service.introduce();
    }
}
