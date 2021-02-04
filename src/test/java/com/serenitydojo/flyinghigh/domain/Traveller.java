package com.serenitydojo.flyinghigh.domain;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class Traveller {

    private final String username;
    private final String password;
    private final String title;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String country;
    private final String seatPreference;
    private final Boolean newsletterSub;
    private final boolean agreesToTermsAndConditions;
    private String userid;

    public Traveller(String username,
                     String password,
                     String title,
                     String firstName,
                     String lastName,
                     String address,
                     String country,
                     String seatPreference,
                     Boolean newsletterSub,
                     boolean agreesToTermsAndConditions) {
        this.username = username;
        this.password = password;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.country = country;
        this.seatPreference = seatPreference;
        this.newsletterSub = newsletterSub;
        this.agreesToTermsAndConditions = agreesToTermsAndConditions;
    }

    public static TravellerBuilder called(String firstName, String lastName) {
        return new TravellerBuilder(firstName, lastName);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getTitle() {
        return title;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getSeatPreference() {
        return seatPreference;
    }

    public Boolean getNewsletterSub() {
        return newsletterSub;
    }

    public String getUserid() {
        return userid;
    }

    public boolean wasCreatedViaTheAPI() {
        return userid != null;
    }

    public boolean agreesToTermsAndConditions() {
        return agreesToTermsAndConditions;
    }

    public Traveller withUsername(String newUsername) {
        return new Traveller(newUsername, password, title, firstName, lastName, address, country, seatPreference, newsletterSub, agreesToTermsAndConditions);
    }

    public Traveller whoDoesNotAgreeToTheTermsAndConditions() {
        return new Traveller(username, password, title, firstName, lastName, address, country, seatPreference, newsletterSub, false);
    }


    public Traveller withUserId(String userid) {
        this.userid = userid;
        return this;
    }

    public Traveller withPassword(String newPassword) {
        return new Traveller(username, newPassword, title, firstName, lastName, address, country, seatPreference, newsletterSub, agreesToTermsAndConditions);
    }

    public Traveller withEmptyValueFor(String field) {
        try {
            Traveller emptyTraveller = this.withUsername(username);
            BeanUtils.setProperty(emptyTraveller, field, "");
            return emptyTraveller;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Unknown Traveller field: " + field);
        }
    }
}

