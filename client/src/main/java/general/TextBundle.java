package general;

import java.util.Locale;
import java.util.ResourceBundle;

public class TextBundle {

    private static Locale currentLocale = new Locale("de", "DE");
    private static ResourceBundle textBundle = ResourceBundle.getBundle("TextBundle", currentLocale);

    public static String getString(String string) {
        return textBundle.getString(string);
    }

}