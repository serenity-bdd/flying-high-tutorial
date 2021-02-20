package com.serenitydojo.flyinghigh.tasks.login;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

class LoginForm {
     static final Target EMAIL_FIELD = Target.the("email field").locatedBy("#email");
     static final Target PASSWORD_FIELD = Target.the("Password field").locatedBy("#password");
     static final Target LOGIN_BUTTON = Target.the("Login button").locatedBy("#login-button");
     static Target FIELD_ERROR_MESSAGE = Target.the("Error message for {0}")
                                               .locatedBy("//mat-form-field[.//*[@id='{0}']]//mat-error");
}
