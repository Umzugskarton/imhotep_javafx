package socket.commands;

import CLTrequests.Request;
import CLTrequests.joinRequest;
import commonLobby.CLTLobby;
import lobby.Lobby;
import socket.ClientListener;
import socket.Server;
import SRVevents.joinEvent;
import SRVevents.lobbyInfoEvent;
import user.User;

public class joinCommand implements Command {

  private ClientListener clientListener;
  private joinRequest request;
  private Server server;


  public joinCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.server = clientListener.getServer();
  }

  @Override
  public void put(Request r) {
    this.request = (joinRequest) r;
  }

  @Override
  public void exec() {
    if (clientListener.getLobby() == null) {
      User user = this.clientListener.getUser();
      Lobby lobby = this.clientListener.getServer().getLobbybyID(request.getId());
      joinEvent response;
      clientListener.setLobby(lobby);
      if (lobby.hasPW()) {
        response = lobby.joinPW(user, request.getPassword());
      } else {
        response = lobby.join(user);
      }
      if (response.getSuccess()) {
        this.clientListener.setLobby(lobby);
        this.clientListener.getServer()
            .sendToLoggedIn(this.server.getLobbies(clientListener.getUser()));
        User[] users = lobby.getUsers();
        CLTLobby cltLobby = new CLTLobby(lobby.getLobbyID(), lobby.getName(),
            lobby.getLobbyUserArrayList(), lobby.hasPW(), lobby.getSize(), lobby.isHost(user),
            lobby.getHostName(), lobby.getReady(), lobby.getColors());
        lobbyInfoEvent lobbyInfo = new lobbyInfoEvent(cltLobby);
        for (User tempUser : users) {
          if (tempUser != null) {
            this.server.sendTo(lobbyInfo, tempUser.getUsername());
          }
        }
      }
    }
  }
}
