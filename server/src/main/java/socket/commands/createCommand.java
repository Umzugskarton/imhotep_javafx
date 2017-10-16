package socket.commands;

import CLTrequests.Request;
import CLTrequests.createRequest;
import SRVevents.createEvent;
import SRVevents.lobbyInfoEvent;
import SRVevents.lobbylistEvent;
import lobby.Lobby;
import socket.ClientAPI;
import socket.ClientListener;

/**
 * Created on 14.10.2017.
 */
public class createCommand implements Command {
    private ClientListener clientListener;
    private createRequest request;
    private ClientAPI clientAPI;


    public createCommand(ClientListener clientListener){
        this.clientListener= clientListener;
        this.clientAPI = clientListener.getClientAPI();
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
            lobbylistEvent lobbyList = this.clientListener.getServer().getLobbies(clientListener.getUser());
            this.clientListener.getServer().sendToLoggedIn(lobbyList);
        }
    }
}
