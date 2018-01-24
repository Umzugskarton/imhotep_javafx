package socket.commands;

import CLTrequests.Request;
import CLTrequests.logoutRequest;
import socket.ClientListener;

public class LogoutCommand implements Command {

  private logoutRequest request;
  private ClientListener clientListener;

  public LogoutCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
  }

  public void put(Request r) {
    this.request = (logoutRequest) r;
  }

  public void exec() {
    if (clientListener.getLobby()!=null){
      clientListener.getLobby().leave(clientListener.getUser());
    }
    this.clientListener.setUser(null);
    this.clientListener.getServer().sendToAll(this.clientListener.getServer().getLoggedUsers());

  }
}

