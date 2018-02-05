package commands.profil;

import commands.Command;
import events.app.profil.ChangeProfilDataEvent;
import requests.IRequest;
import requests.profil.ChangeCredentialRequest;
import socket.ClientAPI;
import socket.ClientListener;

public class ChangeCredentialCommand implements Command {

  private ChangeCredentialRequest request;
  private ClientListener clientListener;
  private ClientAPI clientAPI;

  public ChangeCredentialCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.clientAPI = clientListener.getClientAPI();
  }

  public void put(IRequest r) {
    this.request = (ChangeCredentialRequest) r;
  }

  public void exec() {
    ChangeProfilDataEvent response = this.clientAPI
        .changeCredential(this.request, this.clientListener.getUser());
    response.setCredential(request.getCredential());
    response.setType(request.getCrednr());
    this.clientListener.send(response);
  }
}
