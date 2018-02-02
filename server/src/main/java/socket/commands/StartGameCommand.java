package socket.commands;

import requests.IRequest;
import requests.StartGameRequest;
import socket.ClientListener;

public class StartGameCommand implements Command {

  private StartGameRequest request;
  private ClientListener clientListener;

  StartGameCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    request = (StartGameRequest) r;
  }

  public void exec() {
    clientListener.getLobbyByID(request.getLobbyId()).startGame(clientListener);
  }
}
