package com.portfl.controller;

import com.portfl.model.LoginForm;
import com.portfl.model.User;
import com.portfl.service.RegistrationService;
import com.portfl.service.SecurityService;
import com.portfl.service.UserService;
import com.portfl.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Created by Vlad on 22.03.17.
 */

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    public RegistrationService registrationService;
    @Autowired
    private UserService userService;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @GetMapping(value="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/auth/login";
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

        userService.create(user);
        registrationService.confirmRegistration(user, request.getLocale(), UrlUtils.getAppUrl(request));
        return "redirect:/auth/login";
    }

    @GetMapping(value = "/registrationConfirm.html")
    public String registrationConfirm(@RequestParam("token") String token) {
        if (userService.enableAccount(token)) {
            return "redirect:/profile";
        }
        return "redirect:/home";
    }
}
