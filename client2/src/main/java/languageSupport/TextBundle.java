package languageSupport;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextBundle {

  private static Locale currentLocale = new Locale("de", "DE");
  private static ResourceBundle textBundle = ResourceBundle.getBundle("TextBundle", currentLocale);
  private static final Logger log = LoggerFactory.getLogger(TextBundle.class.getName());

  public static String getString(String string) {
    String result = "";
    try {
      result = textBundle.getString(string);
    } catch (MissingResourceException mre) {
      log.error("MissingResourceException aufgetreten für Key " + string, mre);
    }
    return result;
  }
}