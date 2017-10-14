package socket.commands;

import CLTrequests.Request;
import CLTrequests.createRequest;
import SRVevents.createEvent;
import SRVevents.lobbyInfoEvent;
import lobby.Lobby;
import socket.ClientAPI;
import socket.ClientListener;
import user.User;

/**
 * Created on 14.10.2017.
 */
public class createCommand implements Command {
    private ClientListener clientListener;
    private createRequest request;
    private ClientAPI clientAPI;


    public createCommand(ClientListener clientListener){
        this.clientListener= clientListener;
    }

    @Override
    public void put(Request r) {
        this.request =(createRequest) r;
    }

    @Override
    public void exec() {
        Lobby lobby = this.clientAPI.createLobby(request, this.clientListener.getUser());
        clientListener.setLobby(lobby);
        createEvent response = this.clientListener.getServer().addLobby(lobby);
        if (response.getSuccess()) {
            this.clientListener.getServer().sendToLoggedIn(this.clientListener.getServer().getLobbies());

            lobbyInfoEvent lobbyInfo =new lobbyInfoEvent(lobby.getLobbyID(),
                    lobby.getUsersStringArray(), true, lobby.getReady(), lobby.getColors());

            this.clientListener.getServer().sendTo(lobbyInfo,this.clientListener.getUser().getUsername());

        }
    }
}
