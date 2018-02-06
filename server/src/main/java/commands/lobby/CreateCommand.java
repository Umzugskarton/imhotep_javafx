package commands.lobby;

import commands.Command;
import data.lobby.CommonLobby;
import events.EventReason;
import events.app.lobby.create.CreateLobbyEvent;
import events.app.lobby.LobbyInfoEvent;
import events.app.lobby.create.CreateLobbyFailedEvent;
import events.app.lobby.create.CreateLobbySuccessfulEvent;
import events.app.main.LobbyListEvent;
import lobby.Lobby;
import requests.IRequest;
import requests.lobby.CreateRequest;
import socket.ClientAPI;
import socket.ClientListener;

public class CreateCommand implements Command {

  private ClientListener clientListener;
  private CreateRequest request;
  private ClientAPI clientAPI;

  public CreateCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.clientAPI = clientListener.getClientAPI();
  }

  @Override
  public void put(IRequest r) {
    this.request = (CreateRequest) r;
  }

  @Override
  public void exec() {
    Lobby lobby = this.clientAPI
        .createLobby(request, this.clientListener.getUser(), this.clientListener.getServer());
    clientListener.addLobby(lobby);
    CreateLobbyEvent response = this.clientListener.getServer().addLobby(lobby);

    if (response.getSuccess()) {
      CommonLobby cltLobby = new CommonLobby(lobby.getLobbyID(), lobby.getName(),
          lobby.getLobbyUserArrayList(), lobby.hasPW(), lobby.getSize(),
          lobby.isHost(this.clientListener.getUser()), lobby.getHostName(), lobby.getReady(),
          lobby.getColors());

      clientListener.send(new CreateLobbySuccessfulEvent(cltLobby));

      LobbyListEvent lobbyList = this.clientListener.getServer()
          .getLobbies(clientListener.getUser());
      this.clientListener.getServer().sendToLoggedIn(lobbyList);
    } else {
      CreateLobbyFailedEvent event = new CreateLobbyFailedEvent();
      event.setReason(EventReason.INVALID_REQUEST);
      clientListener.send(new CreateLobbyFailedEvent());
    }
  }
}
