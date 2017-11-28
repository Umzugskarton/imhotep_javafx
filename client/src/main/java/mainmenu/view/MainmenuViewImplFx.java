package mainmenu.view;

import chat.view.ChatView;
import chat.view.ChatViewImpl;
import create.view.CreateView;
import create.view.CreateViewImpl;
import games.view.GamesView;
import games.view.GamesViewImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.text.TextFlow;
import main.SceneController;
import mainmenu.presenter.MainmenuPresenter;
import javafx.scene.input.KeyEvent;
import profile.view.ProfileView;
import profile.view.ProfileViewImpl;

public class MainmenuViewImplFx {

    private MainmenuPresenter mainmenuPresenter;
    private SceneController sc;

    @FXML
    private Button sendButton;

    @FXML
    private Tab chatTab;

    @FXML
    private TextFlow chatText;

    @FXML
    private Tab gamesTab;

    @FXML
    private Tab profileTab;

    @FXML
    private Tab newGameTab;

    @FXML
    private Tab rulesTab;

    @FXML
    private TextField messageInput;

    @FXML
    private ListView<String> listView;

    @FXML
    private Button logoutButton;

    @FXML
    void createGame_handle(ActionEvent event) {

    }

    @FXML
    void logout_handle(ActionEvent event) {
        mainmenuPresenter.logout();
        mainmenuPresenter.toLoginScene();
    }

    @FXML
    void sendMsg_handle(ActionEvent event) {
        mainmenuPresenter.getChatPresenter().sendChatMsg(messageInput.getText());
        messageInput.clear();
        messageInput.requestFocus();
    }

    @FXML
    void enter_handle(KeyEvent keyEvent) {
        if(keyEvent.getCode() == KeyCode.ENTER) {
            if(!messageInput.getText().isEmpty()) {
                sendButton.fire();
            }
        }
    }

    public void setMainmenuPresenter(MainmenuPresenter mainmenuPresenter) {
    this.mainmenuPresenter = mainmenuPresenter;
    }

    public void initPlayerList() {
        //Liste der eingeloggten Spieler als Item der View setzen
        listView.setItems(mainmenuPresenter.getPlayerList().getPlayers());

        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    String selectedUser = (String) listView.getSelectionModel().getSelectedItem();
                    messageInput.setText("@" + selectedUser + " ");
                    messageInput.requestFocus();
                    messageInput.end();
                }
            }
        });
    }

    public void setSceneController(SceneController sc){ this.sc = sc; }

    public void initChat(ChatView chatView) {
        this.chatTab.setContent((ChatViewImpl) chatView);
    }

    public void initCreate(CreateView createView) {
        this.newGameTab.setContent((CreateViewImpl) createView);
    }

    public void initGames(GamesView gamesView) {
        this.gamesTab.setContent((GamesViewImpl) gamesView);
    }

    public void initProfile(ProfileView profileView) {
        this.profileTab.setContent((ProfileViewImpl) profileView);
    }

    public TextFlow getChatText() {
                return this.chatText;
            }

    public TextField getMessageInput() {
                return this.messageInput;
            }
}

