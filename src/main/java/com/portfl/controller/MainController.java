package com.portfl.controller;

import com.portfl.model.Photo;
import com.portfl.model.User;
import com.portfl.service.PhotoService;
import com.portfl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Samsung on 19.04.2017.
 */
@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;

    @GetMapping(value = "/")
    public String homePage() {
        return "home";
    }

    @GetMapping(value = "/profile/{profileId}")
    public String profilePage(@PathVariable Long profileId, Model model) {
        if(profileId != -1) {
            User user = userService.findOne(profileId);
            if(user == null) {
                return "redirect:/";
            } else {
                model.addAttribute("user", user);
                return "profile";
            }
        } else {
            return "redirect:/auth/login";
        }
    }

    @PostMapping(value = "/loadPhoto", consumes = "application/json")
    public @ResponseBody
    String loadPhoto(@RequestBody List<Map<String, Object>> photos, ModelMap map) {
        photoService.addPhotos(photos);
        return "saved";
    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }
}
