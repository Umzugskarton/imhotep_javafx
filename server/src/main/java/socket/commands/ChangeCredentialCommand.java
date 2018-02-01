package socket.commands;

import requests.IRequest;
import requests.changeCredentialRequest;
import events.app.profil.ChangeProfilDataEvent;
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
    ChangeProfilDataEvent response = this.clientAPI
        .changeCredential(this.request, this.clientListener.getUser());
    response.setCredential(request.getCredential());
    response.setType(request.getCrednr());
    this.clientListener.send(response);
  }
}
