package com.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheck {

    @RequestMapping("/")
    public String status() {
        return "Up!";
    }
}
