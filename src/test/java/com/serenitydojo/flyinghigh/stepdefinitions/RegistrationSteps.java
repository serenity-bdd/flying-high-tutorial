package com.serenitydojo.flyinghigh.stepdefinitions;

import com.serenitydojo.flyinghigh.domain.Traveller;
import com.serenitydojo.flyinghigh.tasks.login.Login;
import com.serenitydojo.flyinghigh.tasks.login.VisibleErrorMessage;
import com.serenitydojo.flyinghigh.tasks.navigate.MenuBar;
import com.serenitydojo.flyinghigh.tasks.navigate.Navigate;
import com.serenitydojo.flyinghigh.tasks.registration.CheckErrorMessagesForEachMissingField;
import com.serenitydojo.flyinghigh.tasks.registration.RegisterForNewFrequentFlyerAccount;
import com.serenitydojo.flyinghigh.tasks.registration.RegistrationForm;
import com.serenitydojo.flyinghigh.tasks.registration.api.RegisteredUsersApi;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.ensure.Ensure;

import java.util.List;
import java.util.Map;

import static com.serenitydojo.flyinghigh.domain.KnownTravellers.aTravellerCalled;
import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.containsText;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;
import static org.hamcrest.Matchers.equalTo;

public class RegistrationSteps {

    Traveller traveller;

    RegisteredUsersApi registeredUsersApi = new RegisteredUsersApi("Tracy Traveller","admin");

    @Given("{actor} does not have a Frequent Flyer account")
    public void newCustomer(Actor actor) {
        traveller = aTravellerCalled(actor.getName());
        actor.remember("TRAVELLER", traveller);
    }

    @Given("{actor} wants to register for a Frequent Flyer account")
    public void wantsToRegister(Actor actor) {
        traveller = aTravellerCalled(actor.getName());
        actor.remember("TRAVELLER", traveller);
        actor.attemptsTo(Navigate.toTheRegisterPage());
    }

    @Given("{actor} is a Frequent Flyer member with the following details:")
    public void registerAFrequentFlyerMember(Actor actor, Map<String, String> userDetails) {
        traveller = aTravellerCalled(actor.getName())
                .withEmail(userDetails.getOrDefault("email", actor.getName()))
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
                RegisterForNewFrequentFlyerAccount.as(traveller.withEmail(username))
        );
    }

    @When("{actor} tries to register without entering her {}")
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
    public void logsOnWithEmailAndPassword(Actor actor, String email, String password) {
        Traveller traveller = actor.recall("TRAVELLER");
        actor.attemptsTo(Login.as(traveller.withEmail(email).withPassword(password)));
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
                Ensure.that(MenuBar.CURRENT_USER).hasText(traveller.getEmail())
        );
    }

    @Then("{actor} should not be able to log in")
    public void shouldNotBeAbleToLogIn(Actor actor) {
        actor.attemptsTo(
                Ensure.that(MenuBar.CURRENT_USER).isNotDisplayed()
        );
    }

    @Then("{actor} should be told {string} about {word}")
    public void shouldSeeErrorMessageForField(Actor actor, String expectedMessage, String field) {
        actor.should(
                seeThat(VisibleErrorMessage.forField(field), equalTo(expectedMessage))
        );
    }

    @Then("{actor} should be presented with an error message containing {string}")
    public void shouldSeeAlert(Actor actor, String expectedMessage) {
        actor.should(
                eventually(seeThat(the(MenuBar.ALERT_DIALOG), containsText(expectedMessage)))
        );
    }

    @Then("{actor} should be told {string}")
    public void shouldSeeErrorMessage(Actor actor, String expectedMessage) {
        actor.attemptsTo(
                Ensure.that(RegistrationForm.ERROR_MESSAGE).hasTextContent(expectedMessage)
        );
    }

    @Then("the following information should be mandatory to register:")
    public void checkMandatoryFieldsForRegistration(List<String> mandatoryFields) {
        theActorInTheSpotlight().attemptsTo(
                CheckErrorMessagesForEachMissingField.from(mandatoryFields).as(traveller)
        );
    }

    @After
    public void deleteFrequentFlyer() {
        Traveller traveller = theActorInTheSpotlight().recall("TRAVELLER");
        if ((traveller != null) && (traveller.wasCreatedViaTheAPI())) {
            registeredUsersApi.delete(traveller);
        }
    }

    @Given("{actor} is a registered Frequent Flyer")
    public void registeredFrequentFlyer(Actor actor) {
        traveller = Traveller.called(actor.getName(),"Traveller").build();
        registeredUsersApi.registerTraveller(traveller);
    }

    @And("{actor} has logged on")
    public void hasLoggedOn(Actor actor) {
        actor.attemptsTo(
                Login.as(traveller)
        );
    }
}
