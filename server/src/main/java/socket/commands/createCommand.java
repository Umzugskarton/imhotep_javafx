package socket.commands;

import CLTrequests.Request;
import CLTrequests.createRequest;
import SRVevents.createEvent;
import SRVevents.lobbyInfoEvent;
import SRVevents.lobbylistEvent;
import commonLobby.CLTLobby;
import lobby.Lobby;
import socket.ClientAPI;
import socket.ClientListener;


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
        Lobby lobby = clientAPI.createLobby(request, clientListener.getUser());
        clientListener.setLobby(lobby);
        createEvent response = clientListener.getServer().addLobby(lobby);
        this.clientListener.send(response);
        if (response.getSuccess()) {
            CLTLobby cltLobby = new CLTLobby(lobby.getLobbyID(),  lobby.getName(), lobby.getLobbyUserArrayList(),lobby.hasPW(), lobby.getSize(), lobby.isHost(clientListener.getUser()), lobby.getHostName(),lobby.getReady(), lobby.getColors());
            lobbyInfoEvent lobbyInfo = new lobbyInfoEvent(cltLobby);
            clientListener.send(lobbyInfo);
            lobbylistEvent lobbyList = clientListener.getServer().getLobbies(clientListener.getUser());
            clientListener.getServer().sendToLoggedIn(lobbyList);
        }
    }
}
