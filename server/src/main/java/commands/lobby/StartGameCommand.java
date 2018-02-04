package commands.lobby;

import commands.Command;
import events.app.game.StartGameEvent;
import lobby.Lobby;
import requests.IRequest;
import requests.lobby.StartGameRequest;
import socket.ClientListener;

public class StartGameCommand implements Command {

  private StartGameRequest request;
  private ClientListener clientListener;

  public StartGameCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    request = (StartGameRequest) r;
  }

  public void exec() {
    Lobby lobby = clientListener.getLobbyByID(request.getLobbyId());
    clientListener.getServer().sendToLobby(new StartGameEvent(lobby.getLobbyID()), lobby);
    lobby.startGame(clientListener);
  }
}
