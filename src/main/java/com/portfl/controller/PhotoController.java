package com.portfl.controller;

import com.portfl.model.Commentary;
import com.portfl.model.Photo;
import com.portfl.model.Rate;
import com.portfl.model.User;
import com.portfl.service.CommentaryService;
import com.portfl.service.PhotoService;
import com.portfl.service.RateService;
import com.portfl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Created by Samsung on 29.04.2017.
 */
@Controller
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentaryService commentaryService;
    @Autowired
    private RateService rateService;

    @GetMapping(value = "/{profileId}")
    public String openPhotos(@PathVariable Long profileId, Model model) {
        User user = userService.findOne(profileId);
        if (user == null) {
            return "redirect:/";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("photos", user.getPhotoInfo());
            return "photo";
        }
    }

    @PostMapping(value = "/comment/{photoId}")
    public @ResponseBody
    String registrationSubmit(@PathVariable Long photoId, String text) {
        Commentary commentary = new Commentary();
        commentary.setText(text);
        commentary.setPhoto(photoService.findOne(photoId));
        User user = userService.getUser();
        if (user != null) {
            commentary.setCreatedBy(userService.getUser().getId());
            commentary.setSender(userService.getUser().getFirstName() + " " + userService.getUser().getLastName());
        } else {
            commentary.setCreatedBy(Long.valueOf(0));
            commentary.setSender("anonim");
        }
        commentaryService.addCommenatry(commentary);
        return "success";
    }

    @PostMapping(value = "/rate/{photoId}")
    public @ResponseBody
    String ratePhoto(@PathVariable Long photoId, Long rate) throws IOException {
        rateService.addRate(photoId, rate);
        return "success";
    }

    @GetMapping(value = "/rating/{photoId}")
    public @ResponseBody
    String getRating(@PathVariable Long photoId) throws IOException {
        return new DecimalFormat("#.##").format(rateService.getRate(photoId));
    }

    @GetMapping(value = "/rating/{photoId}/my")
    @PreAuthorize("isAuthenticated()")
    public @ResponseBody
    Long getMyRating(@PathVariable Long photoId) throws IOException {
        Rate rate = rateService.getMyRate(photoId);
        if (rate == null) {
            return 0L;
        } else {
            return rate.getRate();
        }
    }
}
