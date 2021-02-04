package com.serenitydojo.flyinghigh.tasks.navigate;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePageIndex {
    public static Target LOGIN = Target.the("Login link").located(By.linkText("Login"));
    public static Target REGISTER = Target.the("Register link").located(By.linkText("Register"));
}
