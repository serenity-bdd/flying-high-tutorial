package com.serenitydojo.flyinghigh.stepdefinitions;

import com.serenitydojo.flyinghigh.domain.Traveller;
import com.serenitydojo.flyinghigh.tasks.login.Login;
import com.serenitydojo.flyinghigh.tasks.navigate.MenuBar;
import com.serenitydojo.flyinghigh.tasks.navigate.Navigate;
import com.serenitydojo.flyinghigh.tasks.registration.RegisterForNewFrequentFlyerAccount;
import com.serenitydojo.flyinghigh.tasks.registration.api.RegisteredUsersApi;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.Map;

import static com.serenitydojo.flyinghigh.domain.KnownTravellers.aTravellerCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class RegistrationSteps {

    Traveller traveller;

    RegisteredUsersApi registeredUsersApi = new RegisteredUsersApi("Tracy Traveller","admin");

    @Given("{actor} does not have a Frequent Flyer account")
    public void newCustomer(Actor actor) {
        traveller = aTravellerCalled(actor.getName());
        actor.remember("TRAVELLER", traveller);
    }

    @Given("{actor} is a Frequent Flyer member with the following details:")
    public void registerAFrequentFlyerMember(Actor actor, Map<String, String> userDetails) {
        traveller = aTravellerCalled(actor.getName())
                .withUsername(userDetails.getOrDefault("username", actor.getName()))
                .withPassword(userDetails.getOrDefault("password", "secret"));

        Traveller registeredTraveller = registeredUsersApi.registerTraveller(traveller);
        actor.remember("TRAVELLER", registeredTraveller);
    }

    @When("{actor} registers as a Frequent Flyer member")
    public void registers(Actor actor) {
        actor.attemptsTo(
                Navigate.toTheRegisterPage(),
                RegisterForNewFrequentFlyerAccount.as(traveller)
        );
    }

    @When("{actor} tries to register with a username of {string}")
    public void registers(Actor actor, String username) {
        actor.attemptsTo(
                Navigate.toTheRegisterPage(),
                RegisterForNewFrequentFlyerAccount.as(traveller.withUsername(username))
        );
    }

    @When("{actor} tries to register without entering {}")
    public void registersWithoutField(Actor actor, String forgottenField) {
        Traveller forgetfulTraveller = traveller.withEmptyValueFor(forgottenField);
        actor.attemptsTo(
                Navigate.toTheRegisterPage(),
                RegisterForNewFrequentFlyerAccount.as(forgetfulTraveller)
        );
    }

    @When("{actor} tries to register without approving the terms and conditions")
    public void registersWithoutTermsAndConditions(Actor actor) {
        Traveller unwillingTraveller = traveller.whoDoesNotAgreeToTheTermsAndConditions();
        actor.attemptsTo(
                Navigate.toTheRegisterPage(),
                RegisterForNewFrequentFlyerAccount.as(unwillingTraveller)
        );

    }

    @When("^(.*) (?:logs|has logged) on to the Frequent Flyer application$")
    public void logsOnAs(String actorName) {
        Actor actor = OnStage.theActorCalled(actorName);
        Traveller traveller = actor.recall("TRAVELLER");
        actor.attemptsTo(Login.as(traveller));
    }

    @When("{actor} attempts to log on as {string} with password {string}")
    public void logsOnWithUsernameAndPassword(Actor actor, String username, String password) {
        Traveller traveller = actor.recall("TRAVELLER");
        actor.attemptsTo(Login.as(traveller.withUsername(username).withPassword(password)));
    }

    @Then("{actor} should be taken to the Frequent Flyer members area")
    public void shouldGoToMembersArea(Actor actor) {
        actor.attemptsTo(
                Ensure.that(MenuBar.CURRENT_USER).isDisplayed()
        );
    }

    @Then("{actor} should be greeted by his name")
    public void shouldBeGreetedByNameOf(Actor actor) {
        Traveller traveller = actor.recall("TRAVELLER");
        actor.attemptsTo(
                Ensure.that(MenuBar.CURRENT_USER).hasText(traveller.getUsername())
        );
    }

    @Then("{actor} should not be able to log in")
    public void shouldNotBeAbleToLogIn(Actor actor) {
        actor.attemptsTo(
                Ensure.that(MenuBar.CURRENT_USER).isNotDisplayed()
        );
    }

    @Then("{actor} should be presented with an error message containing {string}")
    public void shouldSeeErrorMessage(Actor actor, String expectedMessage) {
        actor.attemptsTo(
                WaitUntil.the(MenuBar.ALERT_DIALOG, isVisible()),
                Ensure.that(MenuBar.ALERT_DIALOG).text().contains(expectedMessage)
        );
    }

    @After
    public void deleteFrequentFlyer() {
        Traveller traveller = theActorInTheSpotlight().recall("TRAVELLER");
        if ((traveller != null) && (traveller.wasCreatedViaTheAPI())) {
            registeredUsersApi.delete(traveller);
        }
    }
}
