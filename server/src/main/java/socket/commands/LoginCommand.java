package socket.commands;

import requests.IRequest;
import requests.loginRequest;
import events.start.login.LoginEvent;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;
import data.user.User;

public class LoginCommand implements Command {

  private loginRequest request;
  private ClientListener clientListener;
  private Server server;
  private ClientAPI clientAPI;

  public LoginCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.server = clientListener.getServer();
    this.clientAPI = clientListener.getClientAPI();
  }

  public void put(IRequest r) {
    this.request = (loginRequest) r;
  }

  public void exec() {
    LoginEvent response = this.clientListener.getClientAPI()
        .login(request, this.server.getLoggedUsers().getUserList());
    if (response.getSuccess()) {
      User user = this.clientAPI.getUser(request.getUsername());
      this.clientListener.setUser(user);
      response.setUsername(user.getUsername());
      response.setEmail(user.getEmail());
      this.server.sendToLoggedIn(this.server.getLoggedUsers());
    }
    this.clientListener.send(response);
  }
}
