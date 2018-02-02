package socket.commands;

import requests.IRequest;
import requests.startGameRequest;
import socket.ClientListener;

public class StartGameCommand implements Command {

  private startGameRequest request;
  private ClientListener clientListener;

  StartGameCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    request = (startGameRequest) r;
  }

  public void exec() {
    clientListener.getLobbyByID(request.getLobbyId()).startGame(clientListener);
  }
}
