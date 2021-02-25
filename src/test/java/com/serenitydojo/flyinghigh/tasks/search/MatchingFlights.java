package com.serenitydojo.flyinghigh.tasks.search;

import com.google.common.base.Splitter;
import com.serenitydojo.flyinghigh.domain.MatchingFlight;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class MatchingFlights {
    public static Question<List<MatchingFlight>> displayed() {
        return Question.about("matching flights").answeredBy(
                actor -> {
                    List<MatchingFlight> matchingFlights = new ArrayList<>();

                    BrowseTheWeb.as(actor)
                            .findAll(".flight-container .card")
                            .forEach(
                                    card -> {
                                        String departureAndDestination = card.find(By.tagName("h2")).getText();
                                        String cabinClass = card.thenFindAll(By.tagName("p")).get(1).getText();

                                        List<String> cities = Splitter.on(" -> ").splitToList(departureAndDestination);
                                        String departure = cities.get(0);
                                        String destination = cities.get(1);

                                        MatchingFlight matchingFlight = new MatchingFlight(departure, destination, cabinClass);
                                        matchingFlights.add(matchingFlight);
                                    }
                            );
                    return matchingFlights;
                }
        );
    }
}
