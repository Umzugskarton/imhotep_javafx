package socket.commands;

import events.app.main.UserListEvent;
import requests.IRequest;
import requests.UserlistRequest;
import socket.ClientListener;

public class UserlistCommand implements Command {

  private UserlistRequest request;
  private ClientListener clientListener;

  UserlistCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    this.request = (UserlistRequest) r;
  }

  public void exec() {
    UserListEvent response = this.clientListener.getServer().getLoggedUsers();
    if (response != null) {
      this.clientListener.send(response);
    }
  }
}
