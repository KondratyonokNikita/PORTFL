package com.portfl.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by Samsung on 13.04.2017.
 */
public enum Gender {
    MAN("Man"),
    WOMAN("Woman");

    private final String label;

    Gender(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
