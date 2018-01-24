package socket.commands;

import CLTrequests.IRequest;
import CLTrequests.userlistRequest;
import SRVevents.userListEvent;
import socket.ClientListener;

public class UserlistCommand implements Command {

  private userlistRequest request;
  private ClientListener clientListener;


  public UserlistCommand(ClientListener clientListener) {
    this.clientListener = clientListener;

  }

  public void put(IRequest r) {
    this.request = (userlistRequest) r;
  }

  public void exec() {
    userListEvent response = this.clientListener.getServer().getLoggedUsers();
    if (response != null) {
      this.clientListener.send(response);
    }
  }
}
