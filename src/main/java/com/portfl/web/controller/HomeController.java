package com.portfl.web.controller;

import com.portfl.constants.Constants;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Vlad on 06.03.17.
 */

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home() {
        System.out.println("1. " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return Constants.Views.HOME_PAGE;
    }

}
