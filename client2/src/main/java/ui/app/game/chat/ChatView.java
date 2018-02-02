package ui.app.game.chat;

import com.google.common.eventbus.EventBus;
import connection.Connection;
import data.user.User;
import helper.fxml.GenerateFXMLView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import mvp.view.INavigateableView;
import mvp.view.ShowViewEvent;

import java.net.URL;
import java.util.ResourceBundle;

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

  public ChatView(INavigateableView parentView, EventBus eventBus, Connection connection, User user) {
    this.parentView = parentView;
    this.chatPresenter = new ChatPresenter(this, eventBus, connection, user);
    this.eventBus = eventBus;
    this.user = user;
    initOwnView();
  }

  @Override
  public void initOwnView() {
    if (this.myParent == null)
      this.myParent = GenerateFXMLView.getINSTANCE().loadView("/ui/fxml/app/main/game/GameChatView.fxml", this, eventBus);
  }

  @FXML
  void initialize() {
    this.chatTextField.requestFocus();
    this.chatFlow.setId("#msg");
    this.chatFlow.getChildren().add(new Text("Willkommen " + this.user.getUsername() + "\n"));
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
  public TextFlow getChatText() {
    return this.chatFlow;
  }

  @Override
  public TextField getMessageInput() {
    return this.chatTextField;
  }
}