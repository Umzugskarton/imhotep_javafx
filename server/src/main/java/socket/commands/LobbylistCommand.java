package socket.commands;

import CLTrequests.IRequest;
import CLTrequests.lobbylistRequest;
import SRVevents.lobbylistEvent;
import socket.ClientListener;

public class LobbylistCommand implements Command {

  private lobbylistRequest request;
  private ClientListener clientListener;

  public LobbylistCommand(ClientListener clientListener) {
    this.clientListener = clientListener;

  }

  public void put(IRequest r) {
    this.request = (lobbylistRequest) r;
  }

  public void exec() {
    lobbylistEvent response = this.clientListener.getServer().getLobbies(clientListener.getUser());
    if (response != null) {
      this.clientListener.send(response);
    }
  }
}
