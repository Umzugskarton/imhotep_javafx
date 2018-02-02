package socket.commands;

import requests.IRequest;
import requests.userlistRequest;
import events.app.main.UserListEvent;
import socket.ClientListener;

public class UserlistCommand implements Command {

  private userlistRequest request;
  private ClientListener clientListener;

  UserlistCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    this.request = (userlistRequest) r;
  }

  public void exec() {
    UserListEvent response = this.clientListener.getServer().getLoggedUsers();
    if (response != null) {
      this.clientListener.send(response);
    }
  }
}
