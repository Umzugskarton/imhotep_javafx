package ui.app.game.chat;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.user.User;
import events.app.chat.ChatMessageEvent;
import events.app.chat.ChatInfoEvent;
import events.app.chat.WhisperChatEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mvp.presenter.Presenter;
import requests.IRequest;
import requests.Request;
import requests.chatRequest;
import requests.whisperRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatPresenter extends Presenter<IChatView> {

  private final Connection connection;
  private User user;

  public ChatPresenter(IChatView view, EventBus eventBus, Connection connection, User user) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
    bind();
  }

  private void bind() {
    getEventBus().register(this);
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
        addInfoMessage("invalidWhisperSyntax");
      }
    } else if (!text.isEmpty()) {
      chatCommand = new chatRequest(text);
    } else if (text.isEmpty()) {
      addInfoMessage("enterMessageToChat");
    }

    if (chatCommand != null) {
      this.connection.send(chatCommand);
    }
  }

  public void addChatMessage(String user, String msg) {
    Text userText = new Text(user + ": ");
    userText.setStyle("-fx-font-weight: bold");
    Text messageText = new Text(msg + "\n");

    getView().getChatText().getChildren().addAll(userText, messageText);
  }

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

    getView().getChatText().getChildren().addAll(userText, messageText);
  }

  public void addInfoMessage(String msg, Color color) {
    Text text = new Text(msg.toUpperCase() + "\n");
    text.setFill(color);
    text.setFont(new Font(null, 10));

    getView().getChatText().getChildren().add(text);
  }

  public void addInfoMessage(String msg) {

    addInfoMessage(msg, Color.GRAY);
  }

  @Subscribe
  public void onChatEvent(ChatMessageEvent e) {
    addChatMessage(e.getUser(), e.getMsg());
  }

  @Subscribe
  public void onChatInfoEvent(ChatInfoEvent e) {
    if(e.getTextColor() == null)
      addInfoMessage(e.getMsg());
    else
      addInfoMessage(e.getMsg(), e.getTextColor());

  }

  @Subscribe
  public void onWhisperEvent(WhisperChatEvent e) {
    addWhisper(e.getFrom(), e.getMsg(), true);
  }
}
