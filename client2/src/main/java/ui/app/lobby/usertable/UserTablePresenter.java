package ui.app.lobby.usertable;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import connection.Connection;
import data.lobby.CommonLobby;
import data.lobby.LobbyUser;
import data.user.User;
import events.app.lobby.ChangeLobbyUserColorEvent;
import events.app.lobby.LobbyInfoEvent;
import events.app.lobby.SetReadyToPlayEvent;
import java.util.Arrays;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import mvp.presenter.Presenter;
import requests.IRequest;
import requests.lobby.StartGameRequest;

public class UserTablePresenter extends Presenter<IUserTableView> {

  private final Connection connection;
  private User user;
  private CommonLobby lobby;
  private ObservableList<LobbyUser> lobbies = FXCollections.observableArrayList();


  public UserTablePresenter(IUserTableView view, EventBus eventBus, Connection connection,
      CommonLobby lobby, User user) {
    super(view, eventBus);
    this.connection = connection;
    this.user = user;
    this.lobby = lobby;
    bind();

    LobbyUser lobbyUser = new LobbyUser(user, Color.RED.toString(), false);
    lobbies.add(lobbyUser);
  }

  private void bind() {
    getEventBus().register(this);
  }

  public CommonLobby getLobby() {
    return this.lobby;
  }

  public void updateLobby(CommonLobby lobby) {
    this.lobby = lobby;
    view.updateTable();
  }

  public void setLobby(CommonLobby lobby) {
    this.lobby = lobby;
    getView().initLobbyInfo();

  }

  public boolean checkAllReady() {
    return !Arrays.asList(lobby.getReady()).contains(false);
  }

  public void startGame() {
    if (lobby.getUsers().size() == lobby.getSize()) {
      IRequest request = new StartGameRequest(lobby.getLobbyId());
      this.connection.send(request);
      // Soundtrack.imhotepTheme.loop();
    } else {
      //ToDo: Message ausgeben das noch nicht genug Spieler gejoined sind
    }
  }

  public boolean checkHost() {
    return this.lobby.getHost().equals(user.getUsername());
  }

  @Subscribe
  public void setReadyEventListener(SetReadyToPlayEvent e) {
    if (e.getLobbyId() == lobby.getLobbyId()) {
      lobby.setReady(e.getReady());
      updateLobby(lobby);
    }
  }

  @Subscribe
  public void changeColorEventListener(ChangeLobbyUserColorEvent e) {
    this.lobby.getUserbyLobbyId(e.getUserId()).setColor(e.getColor());
    updateLobby(lobby);
  }

  @Subscribe
  public void lobbyInfoEventListener(LobbyInfoEvent e) {
    if (lobby.getLobbyId() == e.getLobby().getLobbyId()) {
      updateLobby(e.getLobby());
    }
  }

  public User getUser() {
    return user;
  }
}
