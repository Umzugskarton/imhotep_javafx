package registration.view;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import registration.presenter.RegistrationPresenter;

public class RegistrationViewImpl implements RegistrationView {

  private RegistrationPresenter registrationPresenter;
  private Scene registrationScene;
  private Label registrationStatus;

  public RegistrationViewImpl() {
    buildRegistration();
  }

  public void buildRegistration() {
    BorderPane main = new BorderPane();
    main.setId("regroot");
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setPadding(new Insets(5));
    grid.setHgap(5);
    grid.setVgap(5);

    Rectangle rect = new Rectangle(720, 480);
    rect.setArcHeight(30.0);
    rect.setArcWidth(30.0);
    main.setClip(rect);

    HBox nav = new HBox();
    nav.setId("nav");
    nav.setSpacing(10);
    nav.setAlignment(Pos.CENTER_RIGHT);
    nav.setPadding(new Insets(15, 15, 15, 12));
    main.setTop(nav);
    main.setCenter(grid);

    registrationScene = new Scene(main);
    registrationScene.setFill(Color.TRANSPARENT);

    Label name = new Label(getString("username") + ":");
    TextField nameField = new TextField();
    nameField.setPromptText(getString("enterUsername"));

    Label password = new Label(getString("password") + ":");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText(getString("enterPassword"));

    Label password2 = new Label(getString("repeatPassword") + ":");
    PasswordField passwordField2 = new PasswordField();
    passwordField2.setPromptText(getString("repeatPassword"));

    Label email = new Label(getString("email") + ":");
    TextField emailField = new TextField();
    emailField.setPromptText(getString("enterEmail"));

    registrationStatus = new Label();
    registrationStatus.setTextFill(Color.RED);

    Button register = new Button(getString("register"));
    register.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        String password1 = passwordField.getText();
        String password2 = passwordField2.getText();
        String username = nameField.getText();
        String email = emailField.getText();

        registrationPresenter.sendRegisterRequest(username, password1, password2, email);
      }
    });

    Button close = new Button("x");
    close.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        System.exit(0);
      }
    });

    Button min = new Button("_");
    min.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        registrationPresenter.getSceneController().getStage().setIconified(true);
      }
    });

    min.setMinWidth(20);
    close.setMinWidth(20);
    Button login = new Button(getString("goToLogin"));
    login.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        registrationPresenter.toLoginScene();
      }
    });

    final Delta dragDelta = new Delta();
    nav.setOnMousePressed(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        // record a delta distance for the drag and drop operation.
        dragDelta.x =
            registrationPresenter.getSceneController().getStage().getX() - mouseEvent.getScreenX();
        dragDelta.y =
            registrationPresenter.getSceneController().getStage().getY() - mouseEvent.getScreenY();
      }
    });
    nav.setOnMouseDragged(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent mouseEvent) {
        registrationPresenter.getSceneController().getStage()
            .setX(mouseEvent.getScreenX() + dragDelta.x);
        registrationPresenter.getSceneController().getStage()
            .setY(mouseEvent.getScreenY() + dragDelta.y);
      }
    });

    nav.getChildren().addAll(min, close);
    grid.add(name, 2, 1);
    grid.add(nameField, 3, 1);
    grid.add(password, 2, 2);
    grid.add(passwordField, 3, 2);
    grid.add(password2, 2, 3);
    grid.add(passwordField2, 3, 3);
    grid.add(email, 2, 4);
    grid.add(emailField, 3, 4);
    grid.add(registrationStatus, 3, 5);
    grid.add(register, 2, 6);
    grid.add(login, 3, 6);
  }

  public void updateStatusLabel(String result) {
    registrationStatus.setText(result);
  }

  public Scene getRegistrationScene() {
    return this.registrationScene;
  }

  public void setRegistrationPresenter(RegistrationPresenter registrationPresenter) {
    this.registrationPresenter = registrationPresenter;
  }
}
