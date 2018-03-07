package com.hainet.spring.test.sample.web;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntroductionController {

    private final IntroductionService service;

    public IntroductionController(final IntroductionService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(final Model model) {
        model.addAttribute("key", "value");

        return "index";
    }

    @GetMapping("/introduce")
    @ResponseBody
    public ResponseEntity<String> introduce() {
        final HttpHeaders headers = new HttpHeaders();
        headers.add("key", "value");

        return new ResponseEntity<>(service.introduce(), headers, HttpStatus.OK);
    }
}
