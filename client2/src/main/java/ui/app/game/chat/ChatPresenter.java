package ui.app.game.chat;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.user.User;
import events.app.chat.ChatInfoEvent;
import events.app.chat.ChatMessageEvent;
import events.app.chat.WhisperChatEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.paint.Color;
import mvp.presenter.Presenter;
import requests.IRequest;
import requests.chat.ChatRequest;
import requests.chat.WhisperRequest;

public class ChatPresenter extends Presenter<IChatView> {

  private final Connection connection;
  private User user;
  private CommonLobby lobby;

  public ChatPresenter(IChatView view, EventBus eventBus, Connection connection, CommonLobby lobby,
      User user) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
    this.lobby = lobby;
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

        chatCommand = new WhisperRequest(receiver, message);
        ((WhisperRequest) chatCommand).setLobbyId(this.lobby.getLobbyId());
        getView().addWhisper(receiver, message, false);
      } else {
        getView().addInfoMessage("invalidWhisperSyntax", Color.GRAY);
      }
    } else if (!text.isEmpty()) {
      chatCommand = new ChatRequest(text);
      ((ChatRequest) chatCommand).setLobbyId(this.lobby.getLobbyId());
    } else if (text.isEmpty()) {
      getView().addInfoMessage("enterMessageToChat", Color.GRAY);
    }

    if (chatCommand != null) {
      this.connection.send(chatCommand);
    }
  }

  @Subscribe
  public void onChatEvent(ChatMessageEvent e) {
    if (e.getLobbyId() == this.lobby.getLobbyId()) {
      getView().addChatMessage(e.getUser(), e.getMsg());
    }
  }

  @Subscribe
  public void onChatInfoEvent(ChatInfoEvent e) {
    if (e.getTextColor() == null) {
      getView().addInfoMessage(e.getMsg(), Color.GRAY);
    } else {
      getView().addInfoMessage(e.getMsg(), e.getTextColor());
    }
  }

  @Subscribe
  public void onWhisperEvent(WhisperChatEvent e) {
    if (e.getLobbyId() == this.lobby.getLobbyId()) {
      getView().addWhisper(e.getFrom(), e.getMsg(), true);
    }
  }
}
