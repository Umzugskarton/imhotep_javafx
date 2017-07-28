package create.view;

import chat.presenter.ChatPresenter;
import create.presenter.CreatePresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextFlow;

/**
 * Created by fabianrieger on 28.07.17.
 */
public class CreateViewImpl extends GridPane implements CreateView {
    private CreatePresenter createPresenter;

    public CreateViewImpl(CreatePresenter createPresenter) {
        this.createPresenter = createPresenter;
        buildCreate();
    }

    public void buildCreate() {
        GridPane root = this;
        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(5));

        Label labelUser = new Label("name: "); //Label und Textfelder für den Benutzer
        TextField lobbyName = new TextField();
        lobbyName.setPromptText("Lobbynamen eingeben");

        Label labelSize = new Label("Größe: "); //Label und Textfelder für das Passwort
        TextField lobbySize = new TextField();
        lobbySize.setPromptText("Spieleranzahl eingeben");

        Label labelPassword = new Label("Passwort: "); //Label und Textfelder für das Passwort
        PasswordField passwordUser = new PasswordField();
        passwordUser.setPromptText("Passwort eingeben");


        Button sendButton = new Button("Senden");
        sendButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                createPresenter.createLobby(lobbyName.getText(), Integer.parseInt(lobbySize.getText()), passwordUser.getText());
            }
        });


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

        root.add(labelUser ,0, 1);
        root.add(lobbyName ,1, 1);
        root.add(labelSize ,0, 2);
        root.add(lobbySize ,1, 2);
        root.add(labelPassword ,0, 3);
        root.add(passwordUser ,1, 3);
        root.add(sendButton, 2, 2);
    }

}
