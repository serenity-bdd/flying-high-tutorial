package com.serenitydojo.flyinghigh.tasks.login;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class VisibleErrorMessage {
    public static Question<String> forField(String fieldName) {
        return Text.of(LoginForm.FIELD_ERROR_MESSAGE.of(fieldName)).asAString();
    }
}
