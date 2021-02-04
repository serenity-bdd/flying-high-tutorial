package com.serenitydojo.flyinghigh.stepdefinitions;

import com.serenitydojo.flyinghigh.api.FlyingHighAPI;
import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

public class ScreenplayActors {

    @Before
    public void setTheStage() {
        OnStage.setTheStage(OnlineCast.whereEveryoneCan(CallAnApi.at(FlyingHighAPI.BASE_URL)));
    }

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }
}
