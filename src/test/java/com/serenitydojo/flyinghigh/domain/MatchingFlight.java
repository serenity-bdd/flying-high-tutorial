package com.serenitydojo.flyinghigh.domain;

public class MatchingFlight {
    String departure;
    String destination;
    String cabinClass;

    public MatchingFlight(String departure, String destination, String cabinClass) {
        this.departure = departure;
        this.destination = destination;
        this.cabinClass = cabinClass;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public String getCabinClass() {
        return cabinClass;
    }
}
