package com.serenitydojo.flyinghigh.tasks.login;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

class LoginForm {
     static final String EMAIL_FIELD = "#email";
     static final String PASSWORD_FIELD = "#password";
     static final String LOGIN_BUTTON = "#login-button";
     static Target FIELD_ERROR_MESSAGE = Target.the("Error message for {0}")
                                               .locatedBy("//mat-form-field[.//*[@id='{0}']]//mat-error");
}
