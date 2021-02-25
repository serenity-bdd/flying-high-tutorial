package com.serenitydojo.flyinghigh.stepdefinitions;

import com.serenitydojo.flyinghigh.domain.MatchingFlight;
import com.serenitydojo.flyinghigh.tasks.search.MatchingFlights;
import com.serenitydojo.flyinghigh.tasks.search.SearchForFlights;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Then("{actor} should be shown all matching flights")
    public void heShouldBeShownAllMatchingFlights(Actor actor) {
        List<MatchingFlight> matchingFlights = new ArrayList<>();

        matchingFlights = actor.asksFor(MatchingFlights.displayed());

        assertThat(matchingFlights).isNotEmpty().allMatch(
                matchingFlight -> matchingFlight.getDeparture().equals(departure)
                                  && matchingFlight.getDestination().equals(destination)
                                  && matchingFlight.getCabinClass().equals(cabinClass)
        );
    }
}
