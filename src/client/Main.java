package client;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    private Stage stage;

    public static void main(String[] args){
        launch();       //Startet Java-FX-Thread und ruft die "start"-Methode auf
    }

    public void start(Stage stage){
        stage = new Stage();        /*Stage-Objekt wird erzeugt, welches an die Scenes weitergegeben werden kann
        Bsp.:
        Login login = new Login(stage);
        */
    }
}
