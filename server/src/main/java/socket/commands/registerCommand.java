package socket.commands;

import CLTrequests.Request;
import CLTrequests.registerRequest;
import SRVevents.registerEvent;
import socket.ClientAPI;
import socket.ClientListener;

public class registerCommand implements Command {

  private registerRequest request;
  private ClientListener clientListener;
  private ClientAPI clientAPI;

  public registerCommand(ClientListener clientListener) {
    this.clientListener = clientListener;

    this.clientAPI = clientListener.getClientAPI();
  }

  public void put(Request r) {
    this.request = (registerRequest) r;
  }

  public void exec() {
    registerEvent response = this.clientAPI.register(this.request);
    if (response != null) {
      this.clientListener.send(response);
    }
  }
}
