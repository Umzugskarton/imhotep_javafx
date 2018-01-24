package socket.commands;

import CLTrequests.IRequest;
import CLTrequests.logoutRequest;
import socket.ClientListener;

public class LogoutCommand implements Command {

  private logoutRequest request;
  private ClientListener clientListener;

  public LogoutCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(IRequest r) {
    this.request = (logoutRequest) r;
  }

  public void exec() {
    if (clientListener.getLobbies()!=null){
      clientListener.getLobbies().leave(clientListener.getUser());
    }
    this.clientListener.setUser(null);
    this.clientListener.getServer().sendToAll(this.clientListener.getServer().getLoggedUsers());

  }
}

