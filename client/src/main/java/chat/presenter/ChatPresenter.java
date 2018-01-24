package chat.presenter;

import static general.TextBundle.getString;

import CLTrequests.IRequest;
import CLTrequests.chatRequest;
import CLTrequests.whisperRequest;
import chat.view.ChatView;
import chat.view.ChatViewImpl;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import main.SceneController;

public class ChatPresenter {

  private ChatView view;
  private SceneController sceneController;

  public ChatPresenter(SceneController sc) {
    this.sceneController = sc;

    this.view = new ChatViewImpl(this);
  }

  public void sendChatMsg(String text) {
    IRequest chatCommand = null;

    if (text.startsWith("/w") || text.startsWith("@")) {
      Pattern whisperPattern = Pattern.compile("(\\/w |@)([^\\s]+) (.+)");
      Matcher whisperMatcher = whisperPattern.matcher(text);
      if (whisperMatcher.find()) {
        String receiver = whisperMatcher.group(2);
        String message = whisperMatcher.group(3);

        chatCommand = new whisperRequest(receiver, message);
        addWhisper(receiver, message, false);
      } else {
        addInfoMessage(getString("invalidWhisperSyntax"));
      }
    } else if (!text.isEmpty()) {
      chatCommand = new chatRequest(text);
    } else if (text.isEmpty()) {
      addInfoMessage(getString("enterMessageToChat"));
    }

    if (chatCommand != null) {
      this.sceneController.getClientSocket().send(chatCommand);
    }
  }

  public void addChatMessage(String user, String msg) {
    Text userText = new Text(user + ": ");
    userText.setStyle("-fx-font-weight: bold");
    Text messageText = new Text(msg + "\n");

    this.view.getChatText().getChildren().addAll(userText, messageText);
  }

  public void addWhisper(String user, String msg, boolean isClientReceiver) {
    String recipientText = getString("from");
    Color color = Color.web("#8A2BE2");

    if (!isClientReceiver) {
      recipientText = getString("to");
      color = Color.web("#9c31ff");
    }

    Text userText = new Text(recipientText + " @" + user + ": ");
    userText.setStyle("-fx-font-weight: bold");
    userText.setFill(color);
    Text messageText = new Text(msg + "\n");
    messageText.setFill(color);

    this.view.getChatText().getChildren().addAll(userText, messageText);
  }

  public void addInfoMessage(String msg, Color color) {
    Text text = new Text(msg.toUpperCase() + "\n");
    text.setFill(color);
    text.setFont(new Font(null, 10));

    this.view.getChatText().getChildren().add(text);
  }

  public void addInfoMessage(String msg) {
    addInfoMessage(msg, Color.GRAY);
  }

  public ChatView getChatView() {
    return this.view;
  }
}
