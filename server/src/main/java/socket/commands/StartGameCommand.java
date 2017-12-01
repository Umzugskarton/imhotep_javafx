package socket.commands;


import CLTrequests.Request;

import CLTrequests.startGameRequest;
import socket.ClientListener;

public class StartGameCommand implements Command{
  private startGameRequest request;
  private ClientListener clientListener;

  public StartGameCommand(ClientListener clientListener){
    this.clientListener=clientListener;
  }

  public void put(Request r){
    request =(startGameRequest) r;
  }

  public void exec(){
    clientListener.getLobby().startGame(clientListener);
  }
}
