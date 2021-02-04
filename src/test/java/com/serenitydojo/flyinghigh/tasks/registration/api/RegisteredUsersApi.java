package com.serenitydojo.flyinghigh.tasks.registration.api;

import com.serenitydojo.flyinghigh.domain.Traveller;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

import static com.serenitydojo.flyinghigh.api.FlyingHighAPI.*;

public class RegisteredUsersApi {

    private final String token;

    public RegisteredUsersApi(String apiUser, String apiPassword) {
        token = authenticationTokenFor(apiUser, apiPassword);
    }

    public void delete(Traveller traveller) {
        RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept("*/*")
                .header("Authorization", "Bearer " + token)
                .body(traveller)
                .queryParam("userId", traveller.getUserid())
                .delete(USERS)
                .then()
                .statusCode(200);
    }

    public Traveller registerTraveller(Traveller traveller) {
        String userId = RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept("*/*")
                .body(traveller)
                .post(REGISTER)
                .jsonPath()
                .getString("userId");
        return traveller.withUserId(userId);
    }

    private String authenticationTokenFor(String apiUser, String apiPassword) {

        Map<String, String> credentials = new HashMap<>();
        credentials.put("username", apiUser);
        credentials.put("password", apiPassword);

        return RestAssured.given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .accept("*/*")
                .body(credentials)
                .post(LOGIN)
                .jsonPath()
                .getString("access_token");
    }
}
