package com.serenitydojo.flyinghigh.tasks.search;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class BookingResultsPage {
    public static Target CONFIRMATION_MESSAGE = Target.the("Confirmation message").located(By.cssSelector("h1"));
}
