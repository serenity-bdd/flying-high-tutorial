package com.serenitydojo.flyinghigh.tasks.registration;

import net.serenitybdd.screenplay.targets.Target;

class RegistrationForm {
     static Target USERNAME = Target.the("Username field").locatedBy("#username");
     static Target PASSWORD = Target.the("Password field").locatedBy("#password");
     static Target FIRST_NAME = Target.the("First name field").locatedBy("#firstname");
     static Target LAST_NAME = Target.the("Last name field").locatedBy("#lastname");
     static Target ADDRESS = Target.the("Address field").locatedBy("#address");
     static Target COUNTRY = Target.the("Country field").locatedBy("#country");
     static final Target TERMS_AND_CONDITIONS = Target.the("Terms and conditions").locatedBy("#terms-and-conditions");
     static final String REGISTER_BUTTON = "#register-button";
}
