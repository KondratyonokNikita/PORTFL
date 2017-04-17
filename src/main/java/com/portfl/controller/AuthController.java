package com.portfl.controller;

import com.portfl.listeners.OnRegistrationCompleteEvent;
import com.portfl.model.User;
import com.portfl.service.UserService;
import com.portfl.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

/**
 * Created by Vlad on 22.03.17.
 */

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    public ApplicationEventPublisher eventPublisher;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "home";
    }

    @GetMapping(value = "/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registrationSubmit(@Valid User user, BindingResult result, WebRequest request, Model model) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (userService.isExistUsername(user.getUsername())) {
            model.addAttribute("existUsername", true);
            return "registration";
        }
        if (userService.isExistEmail(user.getEmail())) {
            model.addAttribute("existEmail", true);
            return "registration";
        }
        System.out.println(user.toString());
        userService.create(user);
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(user, request.getLocale(), UrlUtils.getAppUrl(request)));
        return "login";
    }

    @GetMapping(value = "/registrationConfirm.html")
    public String registrationConfirm(@RequestParam("token") String token) {
        if (userService.enableAccount(token)) {
            return "profile";
        }
        return "home";
    }
}
