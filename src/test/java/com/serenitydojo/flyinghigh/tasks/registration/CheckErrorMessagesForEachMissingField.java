package com.serenitydojo.flyinghigh.tasks.registration;

import com.serenitydojo.flyinghigh.domain.Traveller;
import com.serenitydojo.flyinghigh.tasks.login.VisibleErrorMessage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.data.Percentage;

import java.util.List;

import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;

public class CheckErrorMessagesForEachMissingField implements Performable {

    private List<String> mandatoryFields;
    private Traveller traveller;

    public CheckErrorMessagesForEachMissingField() {
    }

    public CheckErrorMessagesForEachMissingField(List<String> mandatoryFields) {
        this.mandatoryFields = mandatoryFields;
    }

    public static CheckErrorMessagesForEachMissingField from(List<String> mandatoryFields) {
        return new CheckErrorMessagesForEachMissingField(mandatoryFields);
    }

    public CheckErrorMessagesForEachMissingField as(Traveller traveller) {
        this.traveller = traveller;
        return this;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SoftAssertions softly = new SoftAssertions();
        for(String mandatoryField : mandatoryFields) {
            actor.attemptsTo(RegisterForNewFrequentFlyerAccount.as(traveller.withEmptyValueFor(mandatoryField)));
            softly.assertThat(anErrorMessageIsVisibleFor(mandatoryField,actor)).as(mandatoryField + " should be mandatory");
        }
        softly.assertAll();
    }

    private boolean anErrorMessageIsVisibleFor(String field, Actor actor) {
        return !VisibleErrorMessage.forField(field).answeredBy(actor).isEmpty();
    }
}
