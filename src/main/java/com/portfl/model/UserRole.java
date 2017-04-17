package com.portfl.model;

/**
 * Created by Vlad on 20.03.17.
 */
public enum UserRole {
    ROLE_USER("User"),
    ROLE_ADMIN("Admin");

    private final String label;

    UserRole(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}