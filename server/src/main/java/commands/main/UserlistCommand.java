package commands.main;

import commands.Command;
import events.app.main.UserListEvent;
import requests.IRequest;
import requests.main.UserlistRequest;
import socket.ClientListener;

public class UserlistCommand implements Command {

  private UserlistRequest request;
  private ClientListener clientListener;

  public UserlistCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    this.request = (UserlistRequest) r;
  }

  /**
   * Beschafft sich ein UserListEvent aus dem clientListener, welches alle eingeloggten User enthält
   * und sendet es an den Anfragenden Client
   */

  public void exec() {
    UserListEvent response = this.clientListener.getServer().getLoggedUsers();
    if (response != null) {
      this.clientListener.send(response);
    }
  }
}
