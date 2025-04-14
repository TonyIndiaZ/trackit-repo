package com.trackit.trackit_server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/app-name")
    public String appName() {
        log.info("Accessed /test/app-name endpoint");
        return "Application name is TrackIt";
    }

    @GetMapping("/welcome")
    public String greet() {
        log.info("Accessed /test/welcome endpoint");
        return "Welcome to TrackIt Application";
    }
    
    @GetMapping("/about")
    public String about() {
        log.info("Accessed /test/about endpoint");
        return " About Page";
    }
}
