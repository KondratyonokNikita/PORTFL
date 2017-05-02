package com.portfl.controller;

import com.portfl.model.*;
import com.portfl.repository.TypeRepository;
import com.portfl.service.CommentaryService;
import com.portfl.service.RateService;
import com.portfl.service.PhotoService;
import com.portfl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.BindingResult;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.portfl.model.Gender.*;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private CommentaryService commentaryService;
    @Autowired
    private RateService rateService;
    @Autowired
    private TypeRepository typeRepository;

    @GetMapping(value = "/")
    public String homePage(Model model) {
        model.addAttribute("top_photos", rateService.getTopPhotos(5));
        return "home";
    }

    @GetMapping(value = "/profile/table/{profileId}")
    public String profileTable(@PathVariable Long profileId, Model model) {
        if (profileId != -1) {
            User user = userService.findOne(profileId);
            if (user == null) {
                return "redirect:/";
            } else {
                model.addAttribute("photos", user.getPhotoInfo());
                return "photo_table";
            }
        } else {
            return "redirect:/auth/login";
        }
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

    @GetMapping(value = "/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @PostMapping(value = "/loadPhoto", consumes = "application/json")
    public @ResponseBody
    String loadPhoto(@RequestBody List<Map<String, Object>> photos, ModelMap map) {
        photoService.addPhotos(photos);
        return "saved";
    }

    @GetMapping(value = "/searchByParam")
    public String searchByParam(Model model) {
        SearchForm searchForm = new SearchForm();
        model.addAttribute("searchForm", searchForm);
        return "searchByParam";
    }

    @GetMapping(value = "/change")
    public String changeColor() {
        User user = userService.getUser();
        if(user.getColor().equals("white"))
            user.setColor("blue");
        else
            user.setColor("white");
        userService.update(user);
        return "redirect:/profile/" + user.getId();
    }

    @PostMapping(value = "/searchByParam")
    public String searchByParamSubmit(@Valid SearchForm searchForm, BindingResult result, WebRequest request, Model model) {
        model.addAttribute("users", userService.getUsersByParam(searchForm));
        return "searchUsers";
    }

    @ModelAttribute("genders")
    public Map<Gender, String> initializeRoles() {
        return Arrays.stream(Gender.values()).collect(Collectors.toMap(value -> value, Gender::getLabel));
    }

    @ModelAttribute("photosession_types")
    public List<Type> getTypes() {
        return typeRepository.findAll();
    }
}
