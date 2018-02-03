package misc.language;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.EnumMap;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class TextBundle {

  private static final EnumMap<Language, Locale> languageMap;
  private static final Logger log = LoggerFactory.getLogger(TextBundle.class.getName());

  private TextBundle(){}

  static {
    languageMap = new EnumMap<>(Language.class);
    languageMap.put(Language.ENGLISH, new Locale("en", "US"));
    languageMap.put(Language.GERMAN, new Locale("de", "DE"));
  }

  private static Locale currentLocale = languageMap.get(Language.GERMAN);
  private static ResourceBundle resourceBundle = ResourceBundle
      .getBundle("TextBundle", currentLocale);

  public static String getString(String string) {
    String result = "missing String: " + string;
    try {
      result = resourceBundle.getString(string);
    } catch (MissingResourceException mre) {
      log.error("MissingResourceException aufgetreten fÃ¼r Key " + string, mre);
    }
    return result;
  }

  public static String getCompoundString(String string, Object[] arguments) {
    MessageFormat formatter = new MessageFormat("");
    formatter.setLocale(currentLocale);
    formatter.applyPattern(getString(string));
    return formatter.format(arguments);
  }

  public static void setLanguage(Language language) {
    currentLocale = languageMap.get(language);
    resourceBundle = ResourceBundle.getBundle("TextBundle", currentLocale);
  }
}