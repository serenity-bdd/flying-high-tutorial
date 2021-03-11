package com.serenitydojo.flyinghigh.tasks.search;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;

public class BookTheFirstFlight {
    public static Performable displayed() {
        return Task.where("{0} books the first displayed flight",
                Click.on(FlightSearchResults.BOOK_FLIGHT)
                );
    }
}
