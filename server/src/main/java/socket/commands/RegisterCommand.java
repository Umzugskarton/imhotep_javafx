package socket.commands;

import requests.IRequest;
import requests.registerRequest;
import events.start.registration.RegistrationEvent;
import socket.ClientAPI;
import socket.ClientListener;

public class RegisterCommand implements Command {

  private registerRequest request;
  private ClientListener clientListener;
  private ClientAPI clientAPI;

  RegisterCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.clientAPI = clientListener.getClientAPI();
  }

  public void put(IRequest r) {
    this.request = (registerRequest) r;
  }

  public void exec() {
    RegistrationEvent response = this.clientAPI.register(this.request);
    if (response != null) {
      this.clientListener.send(response);
    }
  }
}
