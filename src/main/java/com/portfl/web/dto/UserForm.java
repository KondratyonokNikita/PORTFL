package com.portfl.web.dto;

import com.portfl.constants.Constants;

import javax.validation.constraints.Pattern;

/**
 * Created by Vlad on 18.03.17.
 */

public class UserForm {
    @Pattern(regexp = Constants.Regex.USERNAME)
    private String username;
    @Pattern(regexp = Constants.Regex.PASSWORD)
    private String password;
    @Pattern(regexp = Constants.Regex.FIRST_NAME)
    private String firstName;
    @Pattern(regexp = Constants.Regex.LAST_NAME)
    private String lastName;
    @Pattern(regexp = Constants.Regex.EMAIL)
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
