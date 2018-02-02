package socket.commands;

import requests.IRequest;
import requests.chatRequest;
import events.app.chat.ChatMessageEvent;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

public class ChatCommand implements Command {

  private chatRequest request;
  private ClientListener clientListener;
  private Server server;
  private ClientAPI clientAPI;

  ChatCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.server = clientListener.getServer();
    this.clientAPI = clientListener.getClientAPI();
  }

  public void put(IRequest r) {
    this.request = (chatRequest) r;
  }

  public void exec() {
    ChatMessageEvent chatMessage = this.clientAPI.chat(request, this.clientListener.getUser());
    if (request.getLobbyId() != null) {
      server.sendToLobby(chatMessage, server.getLobbybyID(request.getLobbyId()));
    } else {
      this.server.sendToLoggedIn(chatMessage);
    }
  }
}
