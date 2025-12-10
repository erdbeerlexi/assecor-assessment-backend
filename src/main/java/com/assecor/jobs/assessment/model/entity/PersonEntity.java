package com.assecor.jobs.assessment.model.entity;

import com.assecor.jobs.assessment.model.enums.Color;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Copyright 2025 (C) Alexandra Fengler
 * 
 * Author: Alexandra Fengler
 */
@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "lastname", length = 50, nullable = false, unique = false)
    private String lastname;
    @Column(name = "firstname", length = 50, nullable = false, unique = false)
    private String firstname;
    @Column(name = "zip_code", length = 5, nullable = false, unique = false)
    private String zipCode;
    @Column(name = "city", length = 256, nullable = false, unique = false)
    private String city;
    @Enumerated(EnumType.STRING)
    private Color favoriteColor;

    public PersonEntity() {}

    public PersonEntity(
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

    public PersonEntity(
        final Long id,
        final String lastname,
        final String firstname,
        final String zipCodeAndCity,
        final Color favoriteColor
    ) {
        this(id, lastname, firstname, favoriteColor);
        this.setZipCodeAndCity(zipCodeAndCity);
    }

    public PersonEntity(
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

    public int compareTo(final PersonEntity otherPerson) {
        int value = 0;

        if (this.id < otherPerson.getId()) {
            value = -1;
        } else if (this.id > otherPerson.getId()) {
            value = 1;
        }

        return value;
    }

    public String toCsvLine() {
        return this.lastname + ", " + this.firstname + ", " + this.zipCode + " " + this.city + ", " + this.favoriteColor.getNumber();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.lastname == null) ? 0 : this.lastname.hashCode());
        result = prime * result + ((this.firstname == null) ? 0 : this.firstname.hashCode());
        result = prime * result + ((this.zipCode == null) ? 0 : this.zipCode.hashCode());
        result = prime * result + ((this.city == null) ? 0 : this.city.hashCode());
        result = prime * result + ((this.favoriteColor == null) ? 0 : this.favoriteColor.hashCode());
        return result;
    }

    //The id us not used for equals as it does not belong to a person normally and
    //we could not check properly if the person already  exists before creating a new one
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        
        PersonEntity other = (PersonEntity) obj;
        if (this.lastname == null) {
            if (other.lastname != null) {
                return false;
            }
        } else if (!this.lastname.equals(other.lastname)) {
            return false;
        }

        if (this.firstname == null) {
            if (other.firstname != null) {
                return false;
            }
        } else if (!this.firstname.equals(other.firstname)) {
            return false;
        }
        
        if (this.zipCode == null) {
            if (other.zipCode != null) {
                return false;
            }
        } else if (!this.zipCode.equals(other.zipCode)) {
            return false;
        }

        if (this.city == null) {
            if (other.city != null) {
                return false;
            }
        } else if (!this.city.equals(other.city)) {
            return false;
        }

        if (this.favoriteColor != other.favoriteColor) {
            return false;
        }

        return true;
    }
}
