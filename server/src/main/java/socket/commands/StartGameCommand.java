package socket.commands;


import CLTrequests.IRequest;

import CLTrequests.startGameRequest;
import socket.ClientListener;

public class StartGameCommand implements Command{
  private startGameRequest request;
  private ClientListener clientListener;

  public StartGameCommand(ClientListener clientListener){
    this.clientListener=clientListener;
  }

  public void put(IRequest r){
    request =(startGameRequest) r;
  }

  public void exec(){
    clientListener.getLobbyByID(request.getLobbyId()).startGame(clientListener);
  }
}
