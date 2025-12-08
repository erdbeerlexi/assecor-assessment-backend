package com.assecor.jobs.assessment.model;

public class Person {
    private String lastname;
    private String firstname;
    private String zipCode;
    private String city;
    //enum draus machen
    private int favouriteColour;

    public Person() {}

    public Person(
        final String lastname,
        final String firstname,
        final String zipCode,
        final String city,
        final int favouriteColour
    ) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.zipCode = zipCode;
        this.city = city;
        this.favouriteColour = favouriteColour;
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

    public void setFavouriteColour(final int favouriteColour) {
        this.zipCode = zipCode;
    }

    public int getFavouriteColour() {
        return this.favouriteColour;
    }
}