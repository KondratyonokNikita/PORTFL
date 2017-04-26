package com.portfl.controller;

import com.portfl.model.Commentary;
import com.portfl.model.Photo;
import com.portfl.model.User;
import com.portfl.service.CommentaryService;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
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
    @Autowired
    private CommentaryService commentaryService;

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

    @GetMapping(value = "/photo/{photoId}")
    public String openPhoto(@PathVariable Long photoId, Model model, Commentary commentary) {
        Photo photo = photoService.findOne(photoId);
        model.addAttribute("photo", photo);
        model.addAttribute("user", photo.getUser());
        model.addAttribute("currentUser", userService.getUser());
        model.addAttribute("comments", commentaryService.findAll());
        return "photo";
    }

    @PostMapping(value = "/photo/{photoId}")
    public String registrationSubmit(@PathVariable @Valid Long photoId, Commentary commentary, BindingResult result, WebRequest request, Model model) {
        if (result.hasErrors()) {
            return "profile";
        }
        commentary.setPhoto(photoService.findOne(photoId));
        User user=userService.getUser();
        if(user!=null){
            commentary.setCreatedBy(userService.getUser().getId());
            commentary.setSender(userService.getUser().getFirstName()+userService.getUser().getLastName());
        }
        else{
            commentary.setCreatedBy(Long.valueOf(0));
            commentary.setSender("anonimys");
        }
        commentaryService.addCommenatry(commentary);
        return "redirect:/photo/"+photoId;
    }

    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }
}
