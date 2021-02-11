package com.serenitydojo.flyinghigh.tasks.navigate;

import net.serenitybdd.screenplay.targets.Target;

public class MenuBar {
    public static final Target CURRENT_USER = Target.the("Current user").locatedBy("#current-user");
    public static final Target ALERT_DIALOG = Target.the("Error message").locatedBy(".toast-error");
}
