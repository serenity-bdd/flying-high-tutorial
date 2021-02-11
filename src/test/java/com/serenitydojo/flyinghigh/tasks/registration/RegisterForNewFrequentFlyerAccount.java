package com.serenitydojo.flyinghigh.tasks.registration;

import com.serenitydojo.flyinghigh.domain.Traveller;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.conditions.Check;

public class RegisterForNewFrequentFlyerAccount {
    public static Performable as(Traveller traveller) {
        return Task.where("{0} registers for a frequent flyer account as " + traveller.getFirstName(),
                Enter.theValue(traveller.getEmail()).into(RegistrationForm.EMAIL),
                Enter.theValue(traveller.getPassword()).into(RegistrationForm.PASSWORD),
                Enter.theValue(traveller.getFirstName()).into(RegistrationForm.FIRST_NAME),
                Enter.theValue(traveller.getLastName()).into(RegistrationForm.LAST_NAME),
                Enter.theValue(traveller.getAddress()).into(RegistrationForm.ADDRESS),
                Enter.theValue(traveller.getCountry()).into(RegistrationForm.COUNTRY),
                Check.whether(traveller.agreesToTermsAndConditions())
                        .andIfSo(
                                Click.on(RegistrationForm.TERMS_AND_CONDITIONS)
                        ),
                Click.on(RegistrationForm.REGISTER_BUTTON)
        );
    }
}
