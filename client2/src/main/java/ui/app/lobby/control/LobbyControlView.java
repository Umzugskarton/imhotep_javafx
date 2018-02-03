package ui.app.lobby.control;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import helper.fxml.GenerateFXMLView;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;;
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
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    @FXML
    Button signInButton;

    @FXML
    private Text actiontarget;

    private Parent myParent = rootParent;

    private final INavigateableView parentView;
    private final LobbyControlPresenter loginPresenter;
    private final EventBus eventBus;

    public LobbyControlView(INavigateableView parentView, EventBus eventBus, Connection clientSocket){
        this.parentView = parentView;
        this.loginPresenter = new LobbyControlPresenter(this, eventBus, clientSocket);
        this.eventBus = eventBus;
        initOwnView();
    }

    @Override
    public void initOwnView() {
        if(this.myParent == null) {
            this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/lobby/control/ControlView.fxml", this, eventBus);
        }
    }


    @Override
    public Parent getRootParent() {
        return this.myParent;
    }
}