package socket.commands;

import CLTrequests.IRequest;
import CLTrequests.changeCredentialRequest;
import SRVevents.changeCredentialEvent;
import socket.ClientAPI;
import socket.ClientListener;

public class ChangeCredentialCommand implements Command {

  private changeCredentialRequest request;
  private ClientListener clientListener;
  private ClientAPI clientAPI;

  public ChangeCredentialCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.clientAPI = clientListener.getClientAPI();
  }

  public void put(IRequest r) {
    this.request = (changeCredentialRequest) r;
  }

  public void exec() {
    changeCredentialEvent response = this.clientAPI
        .changeCredential(this.request, this.clientListener.getUser());
    response.setCredential(request.getCredential());
    response.setType(request.getCrednr());
    this.clientListener.send(response);
  }
}
