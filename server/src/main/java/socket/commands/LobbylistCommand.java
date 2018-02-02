package socket.commands;

import requests.IRequest;
import requests.lobbylistRequest;
import events.app.lobby.LobbyListEvent;
import socket.ClientListener;

public class LobbylistCommand implements Command {

  private lobbylistRequest request;
  private ClientListener clientListener;

  LobbylistCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    this.request = (lobbylistRequest) r;
  }

  public void exec() {
    LobbyListEvent response = this.clientListener.getServer().getLobbies(clientListener.getUser());
    if (response != null) {
      this.clientListener.send(response);
    }
  }
}
