package com.portfl.controller;

import com.portfl.model.*;
import com.portfl.repository.TypeRepository;
import com.portfl.service.RegistrationService;
import com.portfl.service.SecurityService;
import com.portfl.service.UserService;
import com.portfl.utils.UrlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.GeneratedValue;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private TypeRepository typeRepository;

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

    @GetMapping(value = "/edit/{profileId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editAdmin(@PathVariable Long profileId, Model model) {
        User user = userService.findOne(profileId);
        model.addAttribute("user", user);
        return "edit";
    }

    @GetMapping(value = "/edit")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String edit(Model model) {
        User user = userService.getUser();
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping(value = "/edit")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public String editSubmit(@Valid User user, BindingResult result, WebRequest request, Model model) {
        if (user.getId() != userService.getUser().getId()) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            return "edit";
        }
        if (userService.isExistUsername(user.getUsername())) {
            model.addAttribute("existUsername", true);
            return "edit";
        }
        if (userService.isExistEmail(user.getEmail())) {
            model.addAttribute("existEmail", true);
            return "edit";
        }
        userService.update(user);
        return "redirect:/profile/" + user.getId();
    }

    @PostMapping(value = "/edit/{profileId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String editSubmitAdmin(@Valid User user, BindingResult result, WebRequest request, Model model) {
        if (result.hasErrors()) {
            return "edit";
        }
        if (userService.isExistUsername(user.getUsername())) {
            model.addAttribute("existUsername", true);
            return "edit";
        }
        if (userService.isExistEmail(user.getEmail())) {
            model.addAttribute("existEmail", true);
            return "edit";
        }
        userService.update(user);
        return "redirect:/profile/" + user.getId();
    }

    @GetMapping(value = "/makeAdmin/{profileId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String makeAdmin(@PathVariable Long profileId) {
        userService.makeAdmin(profileId);
        return "redirect:/users";
    }

    @GetMapping(value = "/makeUser/{profileId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String makeUser(@PathVariable Long profileId) {
        userService.makeUser(profileId);
        return "redirect:/users";
    }

    @GetMapping(value = "/delete/{profileId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long profileId) {
        if (profileId == userService.getUser().getId()) {
            return "redirect:/users";
        } else {
            userService.delete(profileId);
            return "redirect:/users";
        }
    }

    @ModelAttribute("genders")
    public Map<Gender, String> initializeRoles() {
        return Arrays.stream(Gender.values()).collect(Collectors.toMap(value -> value, Gender::getLabel));
    }

    @ModelAttribute("photosession_types")
    public List<Type> getTypes(){
        return typeRepository.findAll();
    }
}
