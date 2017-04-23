package com.portfl.controller;

import com.portfl.model.Gender;
import com.portfl.model.Type;
import com.portfl.model.User;
import com.portfl.repository.TypeRepository;
import com.portfl.repository.UserRepository;
import com.portfl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

/**
 * Created by Samsung on 17.04.2017.
 */
@ControllerAdvice
@SessionAttributes("principal")
@RequestMapping("/")
public class AdviceController {
    @Autowired
    private UserService userService;

    @ModelAttribute("principal")
    public String getPrincipal(){
        User user = userService.getUser();
        if(user != null) {
            return user.getUsername();
        } else {
            return "";
        }
    }

    @ModelAttribute("current_user_id")
    public Long getCurrentUser(){
        User user = userService.getUser();
        if(user != null) {
            return user.getId();
        } else {
            return -1L;
        }
    }

    @ModelAttribute("current_user_name")
    public String getCurrentName(){
        User user = userService.getUser();
        if(user != null) {
            return user.getFirstName() + " " + user.getLastName();
        } else {
            return "";
        }
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