package commands.start;

import commands.Command;
import events.start.LogoutEvent;
import lobby.Lobby;
import requests.IRequest;
import requests.main.LogoutRequest;
import socket.ClientListener;

public class LogoutCommand implements Command {

  private LogoutRequest request;
  private ClientListener clientListener;

  public LogoutCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    this.request = (LogoutRequest) r;
  }

  public void exec() {
    if (clientListener.getLobbies() != null) {
      for (Lobby l : clientListener.getLobbies()){
        l.leave(clientListener.getUser());
      }
    }
    clientListener.send(new LogoutEvent());
    this.clientListener.setUser(null);
    this.clientListener.getServer().sendToAll(this.clientListener.getServer().getLoggedUsers());
  }
}

