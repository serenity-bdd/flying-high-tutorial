package com.serenitydojo.flyinghigh.stepdefinitions;

import com.serenitydojo.flyinghigh.domain.MatchingFlight;
import com.serenitydojo.flyinghigh.tasks.search.BookTheFirstFlight;
import com.serenitydojo.flyinghigh.tasks.search.BookingResultsPage;
import com.serenitydojo.flyinghigh.tasks.search.MatchingFlights;
import com.serenitydojo.flyinghigh.tasks.search.SearchForFlights;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

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
    public void shouldBeShownAllMatchingFlights(Actor actor) {

        List<MatchingFlight> matchingFlights = actor.asksFor(MatchingFlights.displayed());

        assertThat(matchingFlights).isNotEmpty().allMatch(
                matchingFlight -> matchingFlight.getDeparture().equals(departure)
                                  && matchingFlight.getDestination().equals(destination)
                                  && matchingFlight.getCabinClass().equals(cabinClass)
        );
    }

    @Given("{actor} has found a flight from {} to {} in {}")
    public void hasFoundAFlight(Actor actor, String departure, String destination, String cabinClass) {
        actor.attemptsTo(
                SearchForFlights.from(departure).to(destination).flyingIn(cabinClass)
        );
    }

    @When("{actor} books the flight")
    public void sheBooksTheFlight(Actor actor) {
        actor.attemptsTo(
                BookTheFirstFlight.displayed(),
                Ensure.that(BookingResultsPage.CONFIRMATION_MESSAGE).text().isEqualTo("You're all set!")
        );
    }

    @Then("the flight should appear in her bookings")
    public void theFlightShouldAppearInHerBookings() {
    }
}
