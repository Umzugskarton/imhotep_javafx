package socket.commands;

import CLTrequests.IRequest;
import CLTrequests.createRequest;
import SRVevents.CreateEvent;
import SRVevents.lobbyInfoEvent;
import SRVevents.lobbylistEvent;
import commonLobby.CLTLobby;
import lobby.Lobby;
import socket.ClientAPI;
import socket.ClientListener;


public class CreateCommand implements Command {
    private ClientListener clientListener;
    private createRequest request;
    private ClientAPI clientAPI;


    public CreateCommand(ClientListener clientListener){
        this.clientListener= clientListener;
        this.clientAPI = clientListener.getClientAPI();
    }

    @Override
    public void put(IRequest r) {
        this.request =(createRequest) r;
    }

  @Override
  public void exec() {
    Lobby lobby = this.clientAPI.createLobby(request, this.clientListener.getUser());
    clientListener.addLobby(lobby);
    CreateEvent response = this.clientListener.getServer().addLobby(lobby);
    this.clientListener.send(response);
    if (response.getSuccess()) {
      CLTLobby cltLobby = new CLTLobby(lobby.getLobbyID(), lobby.getName(),
              lobby.getLobbyUserArrayList(), lobby.hasPW(), lobby.getSize(),
              lobby.isHost(this.clientListener.getUser()), lobby.getHostName(), lobby.getReady(),
              lobby.getColors());
      lobbyInfoEvent lobbyInfo = new lobbyInfoEvent(cltLobby);
      this.clientListener.send(lobbyInfo);
      lobbylistEvent lobbyList = this.clientListener.getServer()
              .getLobbies(clientListener.getUser());
      this.clientListener.getServer().sendToLoggedIn(lobbyList);
    }
  }
}
