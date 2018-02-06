package commands.lobby;

import commands.Command;
import data.lobby.CommonLobby;
import data.user.User;
import events.app.lobby.LobbyInfoEvent;
import events.app.lobby.join.JoinLobbyEvent;
import lobby.Lobby;
import requests.IRequest;
import requests.lobby.JoinRequest;
import socket.ClientListener;
import socket.Server;

public class JoinCommand implements Command {

  private ClientListener clientListener;
  private JoinRequest request;
  private Server server;

  public JoinCommand(ClientListener clientListener) {
    this.clientListener = clientListener;
    this.server = clientListener.getServer();
  }

  @Override
  public void put(IRequest r) {
    this.request = (JoinRequest) r;
  }

  /**
   * Fügt den anfragenden Client in eine Lobby, sollte dieser jene noch nicht betreten haben.
   * Die Information über Status der beitritts Anfrage wird an den Anfragenden Client zurückgeschickt,
   * bei Erfolg wird an alle Lobbyteilnehmer die neue Information über die Lobby geschickt
   *
   */

  @Override
  public void exec() {
    User user = this.clientListener.getUser();
    Lobby lobby = this.clientListener.getServer().getLobbybyID(request.getLobbyId());
    JoinLobbyEvent response;
    if (!clientListener.getLobbies().contains(lobby)) {
      response = lobby.join(user, request.getPassword());
      if (response.getSuccess()) {
        this.clientListener.send(response);
        clientListener.addLobby(lobby);
        clientListener.getServer()
            .sendToLoggedIn(this.server.getLobbies(clientListener.getUser()));
        CommonLobby cltLobby = new CommonLobby(lobby.getLobbyID(), lobby.getName(),
            lobby.getLobbyUserArrayList(), lobby.hasPW(), lobby.getSize(), lobby.isHost(user),
            lobby.getHostName(), lobby.getReady(), lobby.getColors());
        LobbyInfoEvent lobbyInfo = new LobbyInfoEvent(cltLobby);
        server.sendToLobby(lobbyInfo, lobby);
      } else{
        this.clientListener.send(response);
      }
    } else {
      response = new JoinLobbyEvent("Join nicht möglich!",false,this.request.getLobbyId());
      this.clientListener.send(response);
    }
  }
}
