package com.hainet.spring.test.sample.web;

import org.springframework.stereotype.Service;

@Service
public class IntroductionService {

    public String introduce() {
        return "I'm hainet!";
    }
}
