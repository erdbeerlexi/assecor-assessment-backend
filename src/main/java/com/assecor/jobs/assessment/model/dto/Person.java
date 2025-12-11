package com.assecor.jobs.assessment.model.dto;

import com.assecor.jobs.assessment.model.enums.Color;
import com.assecor.jobs.assessment.util.PersonUtil;

/**
 * Copyright 2025 (C) Alexandra Fengler
 * 
 * Author: Alexandra Fengler
 */
public class Person {
    private Long id;
    private String lastname;
    private String name;
    private String zipCode;
    private String city;
    private Color favoriteColor;

    public Person() {}

    public Person(
        final Long id,
        final String lastname,
        final String name,
        final String zipCode,
        final String city,
        final Color favoriteColor
    ) {
        this.id = id;
        this.lastname = lastname;
        this.name = name;
        this.favoriteColor = favoriteColor;
        this.zipCode = zipCode;
        this.city = city;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setZipCode(final String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setCity(final String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }

    public void setFavouriteColour(final Color favoriteColor) {
        this.favoriteColor = favoriteColor;
    }

    public Color getFavoriteColor() {
        return this.favoriteColor;
    }
}