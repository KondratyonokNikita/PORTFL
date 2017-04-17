package com.portfl.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Objects;

/**
 * Created by Samsung on 17.04.2017.
 */
@ControllerAdvice
@SessionAttributes("principal")
@RequestMapping("/")
public class AdviceController {

    @ModelAttribute("principal")
    public String getPrincipal(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication)) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                return ((UserDetails) principal).getUsername();
            }

            return principal.toString();
        }

        return "";
    }

    @GetMapping(value = "/403")
    public ModelAndView accessDenied(Principal user) {
        ModelAndView model = new ModelAndView();

        if (Objects.nonNull(user)) {
            model.addObject("msg", "Hi " + user.getName()
                    + ", you do not have permission to access this page!");
        } else {
            model.addObject("msg",
                    "You do not have permission to access this page!");
        }

        model.setViewName("403");
        return model;
    }
}