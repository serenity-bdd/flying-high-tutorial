package com.serenitydojo.flyinghigh.tasks.search;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.annotations.Step;

public class SearchForFlights implements Performable {
    String departure;
    String destination;
    String cabinClass;

    public static SearchForFlights from(String departure) {
        SearchForFlights searchForFlights = new SearchForFlights();
        searchForFlights.departure = departure;
        return searchForFlights;
    }

    public SearchForFlights to(String destination) {
        this.destination = destination;
        return this;
    }

    public SearchForFlights flyingIn(String cabinClass) {
        this.cabinClass = cabinClass;
        return this;
    }

    static Target TRAVEL_CLASS = Target.the("{0} Travel Class").locatedBy("//span[@class='mat-option-text'][.=' {0} ']");

    @Override
    @Step("{0} searches for flights from #departure to #destination in #cabinClass")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(departure).into("#departure"),
                Enter.theValue(destination).into("#arrival"),
                Click.on("#travel-class"),
                Click.on(TRAVEL_CLASS.of(cabinClass)),
                Click.on("#search-button")
        );
    }
}
