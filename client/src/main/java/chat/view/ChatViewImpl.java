package chat.view;

import static general.TextBundle.getString;

import chat.presenter.ChatPresenter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.TextFlow;

public class ChatViewImpl extends GridPane implements ChatView {

  private ChatPresenter chatPresenter;
  private TextField messageInput;
  private TextFlow chatText;

  public ChatViewImpl(ChatPresenter chatPresenter) {
    this.chatPresenter = chatPresenter;
    buildChat();
  }

  public void buildChat() {
    GridPane root = this;
    root.setHgap(5);
    root.setVgap(5);
    root.setPadding(new Insets(5));

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setFitToWidth(true);
    scrollPane.setStyle("-fx-border-radius: 5px;-fx-background-radius: 5px;-fx-background: white;");

    TextFlow chatText = new TextFlow();
    this.chatText = chatText;
    this.chatText.setPadding(new Insets(5));
    this.chatText.setId("#msg");

    scrollPane.setContent(chatText);

    Button sendButton = new Button(getString("send"));
    sendButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        chatPresenter.sendChatMsg(messageInput.getText());
        messageInput.clear();
        messageInput.requestFocus();
      }
    });

    this.messageInput = new TextField();
    this.messageInput.setPromptText(getString("enterMessage"));
    this.messageInput.setOnKeyPressed(new EventHandler<KeyEvent>() {
      @Override
      public void handle(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
          if (!messageInput.getText().isEmpty()) {
            sendButton.fire();
          }
        }
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

    root.add(scrollPane, 0, 0, 2, 1);
    root.add(messageInput, 0, 1);
    root.add(sendButton, 1, 1);
  }

  public TextFlow getChatText() {
    return this.chatText;
  }

  public TextField getMessageInput() {
    return this.messageInput;
  }
}
