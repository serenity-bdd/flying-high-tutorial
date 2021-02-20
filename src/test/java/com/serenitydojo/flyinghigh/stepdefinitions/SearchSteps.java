package com.serenitydojo.flyinghigh.stepdefinitions;

import com.serenitydojo.flyinghigh.tasks.search.SearchForFlights;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;

public class SearchSteps {

    String departure;
    String destination;
    String cabinClass;

    @Given("{actor} wants to travel from {} to {} in {}")
    public void travelPlansFor(Actor actor,String departure, String destination, String cabinClass) {
        this.departure = departure;
        this.destination = destination;
        this.cabinClass = cabinClass;
    }


    @When("{actor} searches for available flights")
    public void searchesForAvailableFlights(Actor actor) {
        actor.attemptsTo(
                SearchForFlights.from(departure).to(destination).flyingIn(cabinClass)
        );
    }
}
