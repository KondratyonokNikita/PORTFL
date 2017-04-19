package com.portfl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Samsung on 19.04.2017.
 */
@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping
    public String homePage() {
        return "home";
    }

    @GetMapping(value = "/profile")
    public String profilePage() {
        return "profile";
    }
}
