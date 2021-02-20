package com.serenitydojo.flyinghigh.tasks.login;

import com.serenitydojo.flyinghigh.domain.Traveller;
import com.serenitydojo.flyinghigh.tasks.navigate.Navigate;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;

import static com.serenitydojo.flyinghigh.tasks.login.LoginForm.*;

public class Login {

    public static Performable as(Traveller traveller) {
        return Task.where("{) logs in as " + traveller.getEmail(),
                actor -> {
                    actor.attemptsTo(
                            Navigate.toTheLoginPage()
                    );
                    actor.attemptsTo(Enter.theValue(traveller.getEmail()).into(EMAIL_FIELD));
                    actor.attemptsTo(Enter.theValue(traveller.getPassword()).into(PASSWORD_FIELD));
                    actor.attemptsTo(Click.on(LOGIN_BUTTON));
                }
        );
    }
}
