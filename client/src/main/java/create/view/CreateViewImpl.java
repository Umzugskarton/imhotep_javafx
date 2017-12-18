package create.view;

import create.presenter.CreatePresenter;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;


public class CreateViewImpl extends GridPane implements CreateView {

  private CreatePresenter createPresenter;
  private Label message;

  public CreateViewImpl(CreatePresenter createPresenter) {
    this.createPresenter = createPresenter;
    buildCreate();
  }

  public void buildCreate() {
    GridPane root = this;
    root.setHgap(2);
    root.setVgap(2);
    root.setPadding(new Insets(2));

    Label labelUser = new Label("Name: ");
    TextField lobbyName = new TextField();
    lobbyName.setPromptText("Lobbynamen eingeben");

    Label labelSize = new Label("Größe: ");
    String[] playerCount = {"2", "3", "4"};
    ComboBox<String> lobbySize = new ComboBox();
    lobbySize.getItems().addAll(playerCount);
    lobbySize.setValue("2");

    Label labelPassword = new Label("Passwort: ");
    PasswordField passwordUser = new PasswordField();
    passwordUser.setPromptText("Passwort eingeben");

    Button sendButton = new Button("Senden");
    sendButton.addEventHandler(ActionEvent.ACTION, e ->
        createPresenter.createLobby(lobbyName.getText(), Integer.parseInt(lobbySize.getValue()),
            passwordUser.getText()));

    message = new Label();

    ColumnConstraints column = new ColumnConstraints();
    column.setFillWidth(true);
    column.setHgrow(Priority.ALWAYS);
    root.getColumnConstraints().add(column);

    column = new ColumnConstraints();
    column.setFillWidth(false);
    column.setHgrow(Priority.NEVER);
    root.getColumnConstraints().add(column);

    RowConstraints row = new RowConstraints();
    row.setFillHeight(true);
    row.setVgrow(Priority.ALWAYS);
    root.getRowConstraints().add(row);

    row = new RowConstraints();
    row.setFillHeight(false);
    row.setVgrow(Priority.NEVER);
    root.getRowConstraints().add(row);

    root.add(labelUser, 0, 1);
    root.add(lobbyName, 1, 1);
    root.add(labelSize, 0, 2);
    root.add(lobbySize, 1, 2);
    root.add(labelPassword, 0, 3);
    root.add(passwordUser, 1, 3);
    root.add(sendButton, 2, 2);
    root.add(message, 0, 4);
  }

  public void updateStatusLabel(String m) {
      message.setText(m);
  }

}
