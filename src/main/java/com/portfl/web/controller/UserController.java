package com.portfl.web.controller;

import com.portfl.constants.Constants;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Vlad on 18.03.17.
 */

@Controller
public class UserController {
    @RequestMapping(value = "/profile")
    public String profile() {
        return Constants.Views.PROFILE_PAGE;
    }
}
