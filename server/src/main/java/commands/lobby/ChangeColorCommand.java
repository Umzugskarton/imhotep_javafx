package commands.lobby;

import commands.Command;
import data.user.User;
import events.app.lobby.changecolor.ChangeLobbyUserColorEvent;
import java.util.ArrayList;
import lobby.Lobby;
import requests.IRequest;
import requests.lobby.ChangeColorRequest;
import socket.ClientListener;

public class ChangeColorCommand implements Command {

  private ChangeColorRequest request;
  private ClientListener clientListener;
  private ArrayList<String> colors;

  public ChangeColorCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    this.request = (ChangeColorRequest) r;
  }

  public void exec() {
    Lobby lobby = clientListener.getLobbyByID(request.getLobbyId());
    System.out.println(lobby.getLobbyID());
    ChangeLobbyUserColorEvent changeColorEvent = lobby.replaceColor(clientListener.getUser());
    User[] users = lobby.getUsers();
    for (User tempUser : users) {
      if (tempUser != null) {
        clientListener.getServer().sendTo(changeColorEvent, tempUser.getUsername());
      }
    }
  }
}
