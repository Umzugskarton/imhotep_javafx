package socket.commands;

import data.user.User;
import events.app.lobby.ChangeLobbyUserColorEvent;
import java.util.ArrayList;
import lobby.Lobby;
import requests.ChangeColorRequest;
import requests.IRequest;
import socket.ClientListener;

public class ChangeColorCommand implements Command {

  private ChangeColorRequest request;
  private ClientListener clientListener;
  private ArrayList<String> colors;

  ChangeColorCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    this.request = (ChangeColorRequest) r;
  }

  public void exec() {
    Lobby lobby = clientListener.getLobbyByID(request.getLobbyId());
    ChangeLobbyUserColorEvent changeColorEvent = lobby.replaceColor(clientListener.getUser());
    User[] users = lobby.getUsers();
    for (User tempUser : users) {
      if (tempUser != null) {
        clientListener.getServer().sendTo(changeColorEvent, tempUser.getUsername());
      }
    }
  }
}
