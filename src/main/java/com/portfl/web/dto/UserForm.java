package com.portfl.web.dto;

import com.portfl.constants.Constants;

import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * Created by Vlad on 18.03.17.
 */

public class UserForm {
    @Pattern(regexp = Constants.Regex.USERNAME)
    private String username;
    @Pattern(regexp = Constants.Regex.PASSWORD)
    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private Long weight;

    private Long height;

    private Long birthday;

    public Long getWeight() {
        return weight;
    }

    public void setWeight(Long weight) {
        this.weight = weight;
    }

    public Long getHeight() {
        return height;
    }

    public void setHeight(Long height) {
        this.height = height;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

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
