package com.serenitydojo.flyinghigh.domain;

public class TravellerBuilder {
    private String email = "some@email.org";
    private String firstName;
    private String lastName;
    private String password = "secret";
    private String title = "Mr";
    private String address = "1 main street, Big Town";
    private String country = "United Kingdom";
    private String seatPreference = "aisle";
    private Boolean newsletterSub = true;

    public TravellerBuilder(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public TravellerBuilder withUsername(String username) {
        this.email = username;
        return this;
    }

    public TravellerBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public TravellerBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public TravellerBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    public TravellerBuilder withCountry(String country) {
        this.country = country;
        return this;
    }

    public Traveller build() {
        return new Traveller(email, password, title, firstName, lastName, address, country, seatPreference, newsletterSub, true);
    }
}