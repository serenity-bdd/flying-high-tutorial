package com.serenitydojo.flyinghigh.tasks.registration;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegistrationForm {
     static Target EMAIL = Target.the("Email field").locatedBy("#email");
     static Target PASSWORD = Target.the("Password field").locatedBy("#password");
     static Target FIRST_NAME = Target.the("First name field").locatedBy("#firstName");
     static Target LAST_NAME = Target.the("Last name field").locatedBy("#lastName");
     static Target ADDRESS = Target.the("Address field").locatedBy("#address");
     static Target COUNTRY = Target.the("Country field").locatedBy("#country");
     static final Target TERMS_AND_CONDITIONS = Target.the("Terms and conditions").locatedBy("#terms");
     static final String REGISTER_BUTTON = "#register-button";

     public static final Target ERROR_MESSAGE = Target.the("error message").located(By.cssSelector("mat-error"));
}
