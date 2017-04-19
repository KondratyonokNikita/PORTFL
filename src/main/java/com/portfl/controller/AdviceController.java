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
        System.out.println("get principal1");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (Objects.nonNull(authentication)) {
            System.out.println("get principal2");
            Object principal = authentication.getPrincipal();

            if (principal instanceof UserDetails) {
                System.out.println("get principal3");
                return ((UserDetails) principal).getUsername();
            }
            System.out.println("get principal4");
            return principal.toString();
        }
        System.out.println("get principal5");
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