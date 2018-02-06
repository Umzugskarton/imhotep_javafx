package ui.app.game.chat;

import static misc.language.TextBundle.getString;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import mvp.view.INavigateableView;

public class ChatView implements IChatView {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private VBox rootParent;

  @FXML
  private TextFlow chatFlow;

  @FXML
  private TextField chatTextField;

  @FXML
  private Button sendButton;

  private Parent myParent = rootParent;

  private final INavigateableView parentView;
  private final ChatPresenter chatPresenter;
  private final EventBus eventBus;
  private final User user;

  public ChatView(INavigateableView parentView, EventBus eventBus, Connection connection,
      CommonLobby lobby, User user) {
    this.parentView = parentView;
    this.chatPresenter = new ChatPresenter(this, eventBus, connection, lobby, user);
    this.eventBus = eventBus;
    this.user = user;
    initOwnView();
  }

  @Override
  public void initOwnView() {
    if (this.myParent == null) {
      this.myParent = GenerateFXMLView.getINSTANCE()
          .loadView("/ui/fxml/app/game/chat/GameChatView.fxml", this, eventBus);
    }
  }

  @FXML
  void initialize() {
    this.chatTextField.requestFocus();
    this.chatFlow.setId("#msg");
    addInfoMessage(getString("welcome")+ " " + this.user.getUsername(), Color.GRAY);
  }

  @FXML
  public void handleSendChatButton(ActionEvent event) {
    chatPresenter.sendChatMsg(chatTextField.getText());
    clearForm();
    chatTextField.requestFocus();
  }

  @FXML
  public void handleKeyPressed(KeyEvent event) {
    if (event.getCode() == KeyCode.ENTER) {
      if (!chatTextField.getText().isEmpty()) {
        sendButton.fire();
      }
    }
  }

  @Override
  public Parent getRootParent() {
    return myParent;
  }

  @Override
  public void clearForm() {
    this.chatTextField.setText("");
  }

  @Override
  public void addChatMessage(String user, String msg) {
    Text userText = new Text(user + ": ");
    userText.setStyle("-fx-font-weight: bold");
    Text messageText = new Text(msg + "\n");
    this.chatFlow.getChildren().addAll(userText, messageText);
  }

  @Override
  public void addInfoMessage(String msg, Color color) {
    Text text = new Text(msg.toUpperCase() + "\n");
    text.setFill(color);
    text.setFont(new Font(null, 10));

    this.chatFlow.getChildren().add(text);
  }

  @Override
  public void addWhisper(String user, String msg, boolean isClientReceiver) {
    String recipientText = "from";
    Color color = Color.web("#8A2BE2");

    if (!isClientReceiver) {
      recipientText = "to";
      color = Color.web("#9c31ff");
    }

    Text userText = new Text(recipientText + " @" + user + ": ");
    userText.setStyle("-fx-font-weight: bold");
    userText.setFill(color);
    Text messageText = new Text(msg + "\n");
    messageText.setFill(color);

    this.chatFlow.getChildren().addAll(userText, messageText);
  }

  public TextField getChatTextField() {
    return this.chatTextField;
  }
}