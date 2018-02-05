package ui.app.lobby.control;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import helper.fxml.GenerateFXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import mvp.view.INavigateableView;

import java.net.URL;
import java.util.ResourceBundle;


public class LobbyControlView implements ILobbyControlView {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private GridPane rootParent;

    @FXML
    private Label userSizeLabel;

    @FXML
    Button setReadyButton;

    @FXML
    Button startGameButton;

    @FXML
    Button changeColorButton;

    @FXML
    private Text actiontarget;

    private Parent myParent = rootParent;

    private final INavigateableView parentView;
    private final LobbyControlPresenter presenter;
    private final EventBus eventBus;

    public LobbyControlView(INavigateableView parentView, EventBus eventBus, Connection clientSocket, CommonLobby lobby) {
        this.parentView = parentView;
        this.presenter = new LobbyControlPresenter(this, eventBus, clientSocket, lobby);
        this.eventBus = eventBus;
        initOwnView();
    }

    @Override
    public void initOwnView() {
        if (this.myParent == null) {
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/lobby/control/LobbyControlView.fxml", this, eventBus);
        }
    }

    @Override
    public Parent getRootParent() {
        return this.myParent;
    }

    @FXML
    private void handlesetReadyButtonAction(ActionEvent event) {
        presenter.sendSetReadyRequest();
    }

    @FXML
    private void handlestartGameButtonAction(ActionEvent event) {
        System.out.println("Ich starte das Spiel!");
        presenter.startGame();
    }

    @FXML
    private void handlechangeColorButtonAction(ActionEvent event) {
        presenter.sendChangeColorRequest();
    }

    public Button getSetReadyButton() {
        return setReadyButton;
    }

    public Button getStartGameButton() {
        return startGameButton;
    }

    public Button getChangeColorButton() {
        return changeColorButton;
    }

    public Label getUserSizeLabel() {
        return userSizeLabel;
    }
}