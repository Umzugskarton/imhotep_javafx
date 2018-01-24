package socket.commands;

import CLTrequests.IRequest;
import CLTrequests.leaveLobbyRequest;
import SRVevents.leaveLobbyEvent;
import SRVevents.lobbyInfoEvent;
import commonLobby.CLTLobby;
import lobby.Lobby;
import socket.ClientListener;
import socket.Server;
import user.User;

/**
 * Created by Slothan on 18.12.2017.
 */
public class leaveLobbyCommand implements Command {

    private ClientListener clientListener;
    private leaveLobbyRequest request;
    private CLTLobby cltLobby;
    private Server server;

    public leaveLobbyCommand(ClientListener clientListener) {
        this.clientListener = clientListener;
        this.server = clientListener.getServer();
    }

    @Override
    public void put(IRequest r) {
        this.request = (leaveLobbyRequest) r;
    }

    @Override
    public void exec() {
        User user = this.clientListener.getUser();
        Lobby lobby = this.clientListener.getServer().getLobbybyID(request.getLobbyId());
        //clientListener.addLobby(lobby);

        leaveLobbyEvent response = lobby.leave(user);
        server.sendTo(response, clientListener.getUser().getUsername());
        this.clientListener.addLobby(null);
        if(lobby.getUsers()[0] != null) {
            this.clientListener.getServer()
                    .sendToLoggedIn(this.server.getLobbies(clientListener.getUser()));

            CLTLobby cltLobby = new CLTLobby(lobby.getLobbyID(), lobby.getName(),
                    lobby.getLobbyUserArrayList(), lobby.hasPW(), lobby.getSize(), lobby.isHost(user),
                    lobby.getHostName(), lobby.getReady(), lobby.getColors());
            lobbyInfoEvent lobbyInfo = new lobbyInfoEvent(cltLobby);
            server.sendToLobby(lobbyInfo, lobby);
        } else {
            lobbyInfoEvent lobbyInfoEmpty = new lobbyInfoEvent();
            server.sendToLobby(lobbyInfoEmpty, lobby);

        }
    }
}
