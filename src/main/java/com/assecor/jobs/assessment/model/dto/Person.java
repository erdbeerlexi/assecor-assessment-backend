package com.assecor.jobs.assessment.model.dto;

import com.assecor.jobs.assessment.model.enums.Color;

/**
 * Copyright 2025 (C) Alexandra Fengler
 * 
 * Author: Alexandra Fengler
 */
public class Person {
    private Long id;
    private String lastname;
    private String firstname;
    private String zipCode;
    private String city;
    private Color favoriteColor;

    public Person() {}

    public Person(
        final Long id,
        final String lastname,
        final String firstname,
        final String zipCode,
        final String city,
        final Color favoriteColor
    ) {
        this(id, lastname, firstname, favoriteColor);
        this.zipCode = zipCode;
        this.city = city;
    }

    public Person(
        final Long id,
        final String lastname,
        final String firstname,
        final String zipCodeAndCity,
        final Color favoriteColor
    ) {
        this(id, lastname, firstname, favoriteColor);
        this.setZipCodeAndCity(zipCodeAndCity);
    }

    public Person(
        final Long id,
        final String lastname,
        final String firstname,
        final Color favoriteColor
    ) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.favoriteColor = favoriteColor;
    }

    public void setZipCodeAndCity(final String zipCodeAndCity) {
        if (zipCodeAndCity != null) {
            String[] splitted = zipCodeAndCity.split(" ");
            this.zipCode = splitted[0];
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 1; i < splitted.length; i++) { 
                stringBuffer.append(splitted[i]);

                if (i < splitted.length - 1) {
                    stringBuffer.append(" ");
                }
            }

            this.city = stringBuffer.toString();
        }
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

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return this.firstname;
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