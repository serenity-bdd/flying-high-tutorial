package com.serenitydojo.flyinghigh.domain;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

public class Traveller {

    private String email;
    private String password;
    private String title;
    private String firstName;
    private String lastName;
    private String address;
    private String country;
    private String seatPreference;
    private Boolean newsletterSub;
    private boolean agreesToTermsAndConditions;
    private String userid;

    public Traveller(String email,
                     String password,
                     String title,
                     String firstName,
                     String lastName,
                     String address,
                     String country,
                     String seatPreference,
                     Boolean newsletterSub,
                     boolean agreesToTermsAndConditions) {
        this.email = email;
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

    public String getEmail() {
        return email;
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

    public Traveller withEmail(String newUsername) {
        return new Traveller(newUsername, password, title, firstName, lastName, address, country, seatPreference, newsletterSub, agreesToTermsAndConditions);
    }

    public Traveller whoDoesNotAgreeToTheTermsAndConditions() {
        return new Traveller(email, password, title, firstName, lastName, address, country, seatPreference, newsletterSub, false);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSeatPreference(String seatPreference) {
        this.seatPreference = seatPreference;
    }

    public void setNewsletterSub(Boolean newsletterSub) {
        this.newsletterSub = newsletterSub;
    }

    public void setAgreesToTermsAndConditions(boolean agreesToTermsAndConditions) {
        this.agreesToTermsAndConditions = agreesToTermsAndConditions;
    }

    public Traveller withUserId(String userid) {
        this.userid = userid;
        return this;
    }

    public Traveller withPassword(String newPassword) {
        return new Traveller(email, newPassword, title, firstName, lastName, address, country, seatPreference, newsletterSub, agreesToTermsAndConditions);
    }

    public Traveller withEmptyValueFor(String field) {
        try {
            Traveller emptyTraveller = this.withEmail(email);
            BeanUtils.setProperty(emptyTraveller, field, "");
            return emptyTraveller;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Unknown Traveller field: " + field);
        }
    }
}

