package commands.lobby;

import commands.Command;
import events.app.main.LobbyListEvent;
import requests.IRequest;
import requests.lobby.LobbylistRequest;
import socket.ClientListener;

public class LobbylistCommand implements Command {

  private LobbylistRequest request;
  private ClientListener clientListener;

  public LobbylistCommand(ClientListener clientListener) {
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
