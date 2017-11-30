package mainmenu.view;

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

public class MainmenuViewImplFx {

    private MainmenuPresenter mainmenuPresenter;
    private SceneController sc;
    private Integer credType;

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
    private Label usernameLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Button changeEmailButton;

    @FXML
    private Button changePasswordButton;

    @FXML
    private Label newLabel;

    @FXML
    private Label repeatPWLabel;

    @FXML
    private Button confirm;

    @FXML
    private Label userFeedback;

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
    private PasswordField passwordInput;

    @FXML
    private PasswordField passwordInput2;

    @FXML
    private TextField emailInput;

    @FXML
    void createGame_handle(ActionEvent event) {

    }

    @FXML
    void changeEmail_handle(ActionEvent event) {
        passwordInput.setVisible(false);
        passwordInput2.setVisible(false);
        //usernameInput.setVisible(false);
        repeatPWLabel.setVisible(false);
        newLabel.setText("Neue E-Mail: ");
        newLabel.setVisible(true);
        emailInput.setVisible(true);
        credType = 1;
        confirm.setVisible(true);
    }

    @FXML
    void changePassword_handle(ActionEvent event) {
        emailInput.setVisible(false);
        //usernameInput.setVisible(false);
        newLabel.setText("Neues Passwort: ");
        newLabel.setVisible(true);
        repeatPWLabel.setVisible(true);
        passwordInput.setVisible(true);
        passwordInput2.setVisible(true);
        credType = 2;
        confirm.setVisible(true);
    }

    @FXML
    void confirm_handle(ActionEvent event) {
        if (credType == 1) {
            String email = emailInput.getText();
            mainmenuPresenter.sendChangeRequest(email, credType);
        }
        if (credType == 2) {
            String password = passwordInput.getText();
            String confirmPassword = passwordInput2.getText();
            if (password.length() < 8) {
                userFeedback.setText("Passwort muss mindestens 8 Zeichen lang sein.");
            } else {
                if (password.equals(confirmPassword)) {
                    mainmenuPresenter.sendChangeRequest(password, credType);
                } else {
                    userFeedback.setText("Passwort stimmt nicht überein!");
                }
            }
        }
        if (credType == 3) {
            //String username = usernameInput.getText();
            //mainmenuPresenter.sendChangeRequest(username, credType);
        }
        passwordInput.setText("");
        passwordInput2.setText("");
        emailInput.setText("");
    }


    @FXML
    void logout_handle(ActionEvent event) {
        mainmenuPresenter.logout();
        mainmenuPresenter.toLoginScene();
    }

    @FXML
    void sendMsg_handle(ActionEvent event) {
        mainmenuPresenter.sendChatMsg(messageInput.getText());
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

    public void initCreate(CreateView createView) {
        this.newGameTab.setContent((CreateViewImpl) createView);
    }

    public void initGames(GamesView gamesView) {
        this.gamesTab.setContent((GamesViewImpl) gamesView);
    }

    public TextFlow getChatText() {
                return this.chatText;
            }

    public TextField getMessageInput() {
                return this.messageInput;
            }

    public Label getUsernameLabel() {
        return this.usernameLabel;
    }

    public Label getEmailLabel() {
        return this.emailLabel;
    }

    public TextField getEmailInputField() {
        return this.emailInput;
    }

    public void updateStatusLabel(String msg) {
        userFeedback.setText(msg);
    }
}

