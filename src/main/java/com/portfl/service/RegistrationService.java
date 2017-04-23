package com.portfl.service;

import com.portfl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.UUID;

/**
 * Created by Vlad on 21.03.17.
 */
@Component
public class RegistrationService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private Environment env;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageSource messageSource;

    public void confirmRegistration(User user, Locale locale, String appUrl) {
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(token, user);
        String recipientAddress = user.getEmail();
        String subject = messageSource.getMessage("reg.confirm", null, locale);
        String confirmationUrl = appUrl + "/auth/registrationConfirm.html?token=" + token;
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(env.getProperty("smtp.username"));
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(messageSource.getMessage("reg.go.url", null, locale) + confirmationUrl);
        //mailSender.send(email);
    }
}
