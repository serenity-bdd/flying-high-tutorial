package com.serenitydojo.flyinghigh.tasks.navigate;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;

public class Navigate {
    public static Performable toTheFrequentFlyerApplication() {
        return Open.url("http://localhost:4200");
    }

    public static Performable toTheRegisterPage() {
        return Task.where("{0} opens the registration page",
                Navigate.toTheFrequentFlyerApplication(),
                Click.on(HomePageIndex.REGISTER)
        );
    }

    public static Performable toTheLoginPage() {
        return Task.where("{0} opens the login page",
                Navigate.toTheFrequentFlyerApplication(),
                Click.on(HomePageIndex.LOGIN)
        );
    }

}
