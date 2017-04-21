package com.portfl.controller;

import com.portfl.model.Photo;
import com.portfl.model.User;
import com.portfl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Created by Samsung on 19.04.2017.
 */
@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String homePage() {
        return "home";
    }

    @GetMapping(value = "/profile/{profileId}")
    public String profilePage(@PathVariable Long profileId, Model model) {
        if(profileId != -1) {
            User user = userService.findOne(profileId);
            model.addAttribute("user", user);
            return "profile";
        } else {
            return "redirect:/auth/login";
        }
    }

    @PostMapping(value = "/load")
    public String loadPhoto(@RequestParam String result) {
        System.out.println(result);
        return "redirect:/";
    }
}
