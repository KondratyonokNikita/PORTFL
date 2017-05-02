package com.portfl.model;

import javax.persistence.*;
import java.util.Set;

public class SearchForm {
    Long id = Long.valueOf(1);
    String firstName;
    String lastName;

    Integer weightMin;
    Integer weightMax;

    Integer heightMin;
    Integer heightMax;

    Integer birthyearMin;
    Integer birthyearMax;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "user_types", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<Type> types;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getWeightMin() {
        return weightMin;
    }

    public void setWeightMin(Integer weightMin) {
        this.weightMin = weightMin;
    }

    public Integer getWeightMax() {
        return weightMax;
    }

    public void setWeightMax(Integer weightMax) {
        this.weightMax = weightMax;
    }

    public Integer getHeightMin() {
        return heightMin;
    }

    public void setHeightMin(Integer heightMin) {
        this.heightMin = heightMin;
    }

    public Integer getHeightMax() {
        return heightMax;
    }

    public void setHeightMax(Integer heightMax) {
        this.heightMax = heightMax;
    }

    public Integer getBirthyearMin() {
        return birthyearMin;
    }

    public void setBirthyearMin(Integer birthyearMin) {
        this.birthyearMin = birthyearMin;
    }

    public Integer getBirthyearMax() {
        return birthyearMax;
    }

    public void setBirthyearMax(Integer birthyearMax) {
        this.birthyearMax = birthyearMax;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
