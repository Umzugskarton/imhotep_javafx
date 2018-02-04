package commands.start;

import commands.Command;
import events.EventReason;
import events.start.registration.RegistrationEvent;
import events.start.registration.RegistrationFailedEvent;
import events.start.registration.RegistrationSuccessfulEvent;
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
      if(response.isSuccess()){
        RegistrationSuccessfulEvent event = new RegistrationSuccessfulEvent();
        event.setReason(response.getReason());
        this.clientListener.send(event);
      } else {
        RegistrationFailedEvent event = new RegistrationFailedEvent(response.getReason());
        this.clientListener.send(event);
      }
    } else {
      this.clientListener.send(new RegistrationFailedEvent(EventReason.INVALID_REQUEST));
    }
  }
}
