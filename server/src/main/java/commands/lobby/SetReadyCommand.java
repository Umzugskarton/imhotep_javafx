package commands.lobby;

import commands.Command;
import data.user.User;
import events.app.lobby.SetReadyToPlayEvent;
import lobby.Lobby;
import requests.IRequest;
import requests.lobby.SetReadyRequest;
import socket.ClientListener;

public class SetReadyCommand implements Command {

  private ClientListener clientListener;
  private SetReadyRequest request;

  public SetReadyCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    this.request = (SetReadyRequest) r;
  }

  public void exec() {
    Lobby lobby = clientListener.getLobbyByID(request.getLobbyId());
    SetReadyToPlayEvent setReadyEvent = lobby.setReady(clientListener.getUser());
    User[] users = lobby.getUsers();
    for (User tempUser : users) {
      if (tempUser != null) {
        clientListener.getServer().sendTo(setReadyEvent, tempUser.getUsername());
      }
    }
  }
}
