package ui.dialog.lobby.createlobby;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import helper.fxml.GenerateFXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateLobbyView implements ICreateLobbyView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane createLobbyRoot;

    @FXML
    private ChoiceBox<Integer> choiseBox;

    @FXML
    private TextField lobbyNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusMessageLabel;

    private final CreateLobbyPresenter presenter;
    private final EventBus eventBus;

    // Own Parent
    private Parent myParent;

    public CreateLobbyView(EventBus eventBus, Connection connection) {
        this.eventBus = eventBus;
        this.presenter = new CreateLobbyPresenter(this, eventBus, connection);
        bind();
        initOwnView();
    }

    private void bind() {
        eventBus.register(this);
    }

    @Override
    public void initOwnView() {
        if (this.myParent == null)
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/dialog/createLobby/CreateLobbyView.fxml", this, eventBus);
        System.out.println("CreateLobbyView " + this.myParent);
    }

    @FXML
    void initialize() {
        this.lobbyNameField.requestFocus();
        this.choiseBox.setValue(2);
        this.choiseBox.getItems().setAll(2, 3, 4);
    }

    @FXML
    public void handleSendButtonAction(ActionEvent event) {
        this.presenter.createLobby(this.lobbyNameField.getText(), this.choiseBox.getValue(), this.passwordField.getText());
    }

    @Override
    public void updateStatusLabel(String m) {
        this.statusMessageLabel.setText(m);
    }

    @Override
    public String getTitle() {
        return "CreateLobby";
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }
}
