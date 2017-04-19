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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

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

    @GetMapping(value = "/logout")
    public String logout() {
        SecurityContextHolder.clearContext();
        return "redirect:/";
    }

    @GetMapping(value = "/login")
    public String loginPage(Model model) {
        LoginForm loginForm = new LoginForm();
        model.addAttribute("loginform", loginForm);
        return "login";
    }

    @PostMapping(value = "/login")
    public String loginSubmit(@ModelAttribute LoginForm form, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "login";
        }
        if (userService.isAccountEnabled(form.getUsername())) {
            securityService.autoLogin(form.getUsername(), form.getPassword());
            return "redirect:/";
        }
        model.addAttribute("enabled", true);
        return "redirect:/";
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
