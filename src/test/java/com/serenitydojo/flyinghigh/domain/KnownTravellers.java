package com.serenitydojo.flyinghigh.domain;

public class KnownTravellers {
    public static Traveller aTravellerCalled(String firstName) {
        return Traveller.called(firstName, "Traveller")
                .withUsername(uniqueUsernameIncluding(firstName))
                .build();
    }

    private static String uniqueUsernameIncluding(String actorName) {
        return actorName + "-" + System.currentTimeMillis();
    }

}
