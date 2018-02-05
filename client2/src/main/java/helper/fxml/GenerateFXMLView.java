package helper.fxml;

import com.google.common.eventbus.EventBus;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import mvp.view.IView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class GenerateFXMLView {

    Logger log = LoggerFactory.getLogger(getClass().getName());

    private static final GenerateFXMLView INSTANCE = new GenerateFXMLView();

    public static GenerateFXMLView getINSTANCE() {
        return INSTANCE;
    }

    private static Locale currentLocale = new Locale("de", "DE");
    private static ResourceBundle textBundle = ResourceBundle.getBundle("TextBundle", currentLocale);

    public Parent loadView(String local, IView view) {
        Parent startParent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(local), textBundle);
        loader.setController(view);

        try {
            return loader.load();
        } catch (IOException e) {
            log.error("Fehler bei laden des Views", e);
        }
        return null;
    }


    public Parent loadView(String local, IView view, EventBus eventBus) {
        Parent startParent;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(local), textBundle);
        loader.setController(view);

        try {
            return loader.load();
        } catch (IOException e) {
            log.error("Fehler bei laden des Views", e);
        }
        return null;
    }
}
