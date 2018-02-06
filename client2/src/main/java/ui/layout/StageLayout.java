package ui.layout;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import events.start.LogoutEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import requests.main.LogoutRequest;

public class StageLayout {

  private Stage stage;
  private Scene scene;
  private final EventBus eventBus;
  private Connection connection;

  private Rectangle edgeRect = new Rectangle();

  public StageLayout(Stage stage, Scene scene, EventBus eventBus, Connection connection) {
    this.stage = stage;
    this.scene = scene;
    this.eventBus = eventBus;
    this.connection = connection;

    //Windows Navigation entfernen
    stage.initStyle(StageStyle.TRANSPARENT);

    //Umrandung rund machen
    this.edgeRect.setArcHeight(30.0);
    this.edgeRect.setArcWidth(30.0);

    this.edgeRect.widthProperty().bind(this.stage.widthProperty());
    this.edgeRect.heightProperty().bind(this.stage.heightProperty());

    this.scene.getRoot().setClip(this.edgeRect);

    this.scene.setFill(Color.TRANSPARENT);

    setTitel("Imhotep");
  }

  public void configNavigation(HBox nav, Boolean withLogout) {
    nav.setId("nav");
    nav.setSpacing(5);
    nav.setAlignment(Pos.CENTER_RIGHT);

    final Delta dragDelta = new Delta();
    nav.setOnMousePressed(mouseEvent -> {
      dragDelta.x = this.stage.getX() - mouseEvent.getScreenX();
      dragDelta.y = this.stage.getY() - mouseEvent.getScreenY();
    });

    nav.setOnMouseDragged(mouseEvent -> {
      this.stage.setX(mouseEvent.getScreenX() + dragDelta.x);
      this.stage.setY(mouseEvent.getScreenY() + dragDelta.y);
    });

    Button close = new Button("x");
    close.setFocusTraversable(false);
    close.addEventHandler(ActionEvent.ACTION, e -> System.exit(0));

    Button min = new Button("_");
    min.setFocusTraversable(false);
    min.addEventHandler(ActionEvent.ACTION, e ->
        this.stage.setIconified(true));

    min.setMinWidth(20);
    close.setMinWidth(20);

    if (withLogout) {
      Button logoutButton = new Button();
      logoutButton.setId("logout-button");
      logoutButton.setFocusTraversable(false);
      logoutButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        public void handle(ActionEvent event) {
          connection.send(new LogoutRequest());
        }
      });
      nav.getChildren().addAll(logoutButton, min, close);
    } else {
      nav.getChildren().addAll(min, close);
    }
  }


  public void setTitel(String titel) {
    this.stage.setTitle(titel);
  }

  public void setResizable(Boolean resizable) {
    stage.setResizable(resizable);
  }

  public void setWindowSize(int width, int height) {
    stage.setWidth(width);
    stage.setHeight(height);
  }
}
