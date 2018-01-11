package login.view;

import static general.TextBundle.getString;

import general.Delta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import login.presenter.LoginPresenter;


public class LoginViewImpl implements LoginView {

  private Scene loginScene;
  private LoginPresenter loginPresenter;

  private Label loginStatus;

  public LoginViewImpl() {
    buildLogin();
  }

  public void buildLogin() {
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

    Rectangle rect = new Rectangle(720, 480);
    rect.setArcHeight(30.0);
    rect.setArcWidth(30.0);
    main.setClip(rect);
    loginScene = new Scene(main);
    loginScene.setFill(Color.TRANSPARENT);

    Label labelUser = new Label(getString("username") + " ");
    TextField userName = new TextField();
    userName.setPromptText(getString("enterUsername"));

    Label labelPassword = new Label(getString("password") + " ");
    PasswordField passwordUser = new PasswordField();
    passwordUser.setPromptText(getString("enterPassword"));

    loginStatus = new Label();    //gibt an, ob Login erfolgreich war
    loginStatus.setTextFill(Color.RED);

    Button loginNow = new Button(getString("login")); //Buttons werden angelegt
    loginNow.addEventHandler(ActionEvent.ACTION, e ->
        loginPresenter.sendLoginRequest(userName.getText(), passwordUser.getText()));

    loginScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER) {
          loginNow.fire();
        }
      }
    });

    Button registerNow = new Button(getString("goToRegister"));
    registerNow.addEventHandler(ActionEvent.ACTION, e -> loginPresenter.toRegisterScene());

    Button close = new Button("x");
    close.addEventHandler(ActionEvent.ACTION, e -> System.exit(0));

    Button min = new Button("_");
    min.addEventHandler(ActionEvent.ACTION, e ->
        loginPresenter.getSceneController().getStage().setIconified(true));

    min.setMinWidth(20);
    close.setMinWidth(20);

    final Delta dragDelta = new Delta();
    nav.setOnMousePressed(mouseEvent -> {
      dragDelta.x =
          loginPresenter.getSceneController().getStage().getX() - mouseEvent.getScreenX();
      dragDelta.y =
          loginPresenter.getSceneController().getStage().getY() - mouseEvent.getScreenY();
    });
    nav.setOnMouseDragged(mouseEvent -> {
      loginPresenter.getSceneController().getStage().setX(mouseEvent.getScreenX() + dragDelta.x);
      loginPresenter.getSceneController().getStage().setY(mouseEvent.getScreenY() + dragDelta.y);
    });

    //Hier werden die Positionen "angelegt"
    GridPane.setConstraints(labelUser, 0, 0);
    GridPane.setConstraints(userName, 1, 0);
    GridPane.setConstraints(labelPassword, 0, 1);
    GridPane.setConstraints(passwordUser, 1, 1);
    GridPane.setConstraints(loginStatus, 0, 2, 2, 1);
    GridPane.setConstraints(loginNow, 0, 3);
    GridPane.setConstraints(registerNow, 1, 3);

    nav.getChildren().addAll(min, close);
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


  @Override
  public void setLoginPresenter(LoginPresenter loginPresenter) {
    this.loginPresenter = loginPresenter;
  }


}
