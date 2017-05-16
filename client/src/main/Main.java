package main;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    private Stage stage;

    public static void main(String[] args){
        launch();
    }

    public void start(Stage stage) {
        SceneController sc = new SceneController(stage);
    }
}
