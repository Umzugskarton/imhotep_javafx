package socket.commands;

import requests.IRequest;
import requests.whisperRequest;
import events.app.chat.UserNotFoundErrorEvent;
import events.Event;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

public class WhisperCommand implements Command {

  private whisperRequest request;
  private ClientListener clientListener;
  private Server server;
  private ClientAPI clientAPI;

  public WhisperCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.server = clientListener.getServer();
    this.clientAPI = clientListener.getClientAPI();
  }

  public void put(IRequest r) {
    this.request = (whisperRequest) r;
  }

  public void exec() {
    Event response = null;
    String receiverUsername = this.server
        .getLoggedInUsername(request.getTo());
    if (receiverUsername != null) {
      this.server
          .sendTo(this.clientAPI.whisper(request, this.clientListener.getUser()), receiverUsername);
    } else {
      UserNotFoundErrorEvent error = new UserNotFoundErrorEvent();
      error.setMsg(request.getTo());
      response = error;
    }

    if (response != null) {
      this.clientListener.send(response);
    }
  }
}
