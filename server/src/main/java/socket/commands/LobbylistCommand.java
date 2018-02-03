package socket.commands;

import events.app.lobby.LobbyListEvent;
import requests.IRequest;
import requests.LobbylistRequest;
import socket.ClientListener;

public class LobbylistCommand implements Command {

  private LobbylistRequest request;
  private ClientListener clientListener;

  LobbylistCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    this.request = (LobbylistRequest) r;
  }

  public void exec() {
    LobbyListEvent response = this.clientListener.getServer().getLobbies(clientListener.getUser());
    if (response != null) {
      this.clientListener.send(response);
    }
  }
}
