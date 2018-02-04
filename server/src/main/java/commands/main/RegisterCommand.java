package commands.main;

import commands.Command;
import events.start.registration.RegistrationEvent;
import requests.IRequest;
import requests.start.registration.RegisterRequest;
import socket.ClientAPI;
import socket.ClientListener;

public class RegisterCommand implements Command {

  private RegisterRequest request;
  private ClientListener clientListener;
  private ClientAPI clientAPI;

  public RegisterCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.clientAPI = clientListener.getClientAPI();
  }

  public void put(IRequest r) {
    this.request = (RegisterRequest) r;
  }

  public void exec() {
    RegistrationEvent response = this.clientAPI.register(this.request);
    if (response != null) {
      this.clientListener.send(response);
    }
  }
}
