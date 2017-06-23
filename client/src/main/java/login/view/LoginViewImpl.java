package login.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import login.presenter.LoginPresenter;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;


public class LoginViewImpl implements LoginView {

  private Scene loginScene;
  private LoginPresenter loginPresenter;

  private Label loginStatus;

  public LoginViewImpl() {
    buildLogin();
  }

  public void buildLogin() { //created by mircoskrzipczyk, annkristinklopp
    BorderPane main = new BorderPane();
    main.setId("loginroot");
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setPadding(new Insets(5));
    grid.setHgap(5);
    grid.setVgap(5);


    HBox nav = new HBox();
    nav.setId("nav");
    nav.setSpacing(10);
    nav.setAlignment(Pos.CENTER_RIGHT);
    nav.setPadding(new Insets(15, 15, 15, 12));
    main.setTop(nav);
    main.setCenter(grid);

    Rectangle rect = new Rectangle(720,480);
    rect.setArcHeight(30.0);
    rect.setArcWidth(30.0);
    main.setClip(rect);
    loginScene = new Scene(main);
    loginScene.setFill(Color.TRANSPARENT);
   // loginScene.getStylesheets().add("style.css");

    Label labelUser = new Label("Benutzername: "); //Label und Textfelder für den Benutzer
    TextField userName = new TextField();
    userName.setPromptText("Benutzernamen eingeben");


    Label labelPassword = new Label("Passwort: "); //Label und Textfelder für das Passwort
    PasswordField passwordUser = new PasswordField();
    passwordUser.setPromptText("Passwort eingeben");

    loginStatus = new Label();    //gibt an, ob Login erfolgreich war
    loginStatus.setTextFill(Color.RED);

    Button loginNow = new Button("Login"); //Buttons werden angelegt
    loginNow.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        loginPresenter.sendLoginRequest(userName.getText(), passwordUser.getText());
      }
    });

    Button registerNow = new Button("Zur Registrierung wechseln");
    registerNow.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        loginPresenter.toRegisterScene();
      }
    });

   Button close = new Button("x");
    close.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        System.exit(0);
      }
    });

    Button min =  new Button("_");
    min.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        loginPresenter.getSceneController().getStage().setIconified(true);
      }
    });

    min.setMinWidth(20);
    close.setMinWidth(20);

    final Delta dragDelta = new Delta();
    nav.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        // record a delta distance for the drag and drop operation.
        dragDelta.x =  loginPresenter.getSceneController().getStage().getX() - mouseEvent.getScreenX();
        dragDelta.y =  loginPresenter.getSceneController().getStage().getY() - mouseEvent.getScreenY();
      }
    });
    nav.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override public void handle(MouseEvent mouseEvent) {
        loginPresenter.getSceneController().getStage().setX(mouseEvent.getScreenX() + dragDelta.x);
        loginPresenter.getSceneController().getStage().setY(mouseEvent.getScreenY() + dragDelta.y);
      }
    });


    GridPane.setConstraints(labelUser, 0, 0); //Hier werden die Positionen "angelegt"
    GridPane.setConstraints(userName, 1, 0);
    GridPane.setConstraints(labelPassword, 0, 1);
    GridPane.setConstraints(passwordUser, 1, 1);
    GridPane.setConstraints(loginStatus, 0, 2, 2, 1);
    GridPane.setConstraints(loginNow, 0, 3);
    GridPane.setConstraints(registerNow, 1, 3);


    nav.getChildren().addAll(min,close);
    grid.getChildren()
        .addAll(labelUser, userName, labelPassword, passwordUser, loginStatus, loginNow,
            registerNow); //hier werden dem grid die buttons, textfelder und labels übergeben
  }

  public void updateStatusLabel(String text) {
    loginStatus.setText(text);
  }

  public Scene getLoginScene() {
    return this.loginScene;
  }
  class Delta { double x, y; }

  @Override
  public void setLoginPresenter(LoginPresenter loginPresenter) {
    this.loginPresenter = loginPresenter;
  }
}
