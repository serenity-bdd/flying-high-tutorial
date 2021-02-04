package com.serenitydojo.flyinghigh.tasks.registration;

import com.serenitydojo.flyinghigh.domain.Traveller;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.RememberThat;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static com.serenitydojo.flyinghigh.api.FlyingHighAPI.REGISTER;
import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class UseTheRegisteredUsersApiTo {
    public static Performable registerATraveller(Traveller traveller) {
        return Task.where("{0} registers a new frequent flyer",
                actor -> {
                    actor.attemptsTo(
                            Post.to(REGISTER).with(
                                    query -> query.accept("*/*").contentType(ContentType.JSON).body(traveller)
                            ),
                            RememberThat.theValueOf("TRAVELLER").is(traveller.withUserId(userId()))
                    );
                }
        ).withNoReporting();
    }

    private static String userId() {
        return lastResponse().jsonPath().getString("userId");
    }
}
