package by.bsuir.andrei.http.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleRunner {

    public static void main(String[] args) {
        Locale locale = new Locale("be", "BY");

        ResourceBundle resourceBundle = ResourceBundle.getBundle("translation");
        System.out.println(resourceBundle.getString("page.login.password"));
        resourceBundle = ResourceBundle.getBundle("translation", locale);
        System.out.println(resourceBundle.getString("page.login.password"));
    }
}
