package socket.commands;

import CLTrequests.Request;
import CLTrequests.lobbylistRequest;
import SRVevents.lobbylistEvent;

import socket.ClientListener;

/**
 * Created on 15.10.2017.
 */
public class lobbylistCommand implements Command {
    private lobbylistRequest request;
    private ClientListener clientListener;

    public lobbylistCommand(ClientListener clientListener){
        this.clientListener=clientListener;

    }

    public void put(Request r){
        this.request =(lobbylistRequest) r;
    }

    public void exec(){
        lobbylistEvent response= this.clientListener.getServer().getLobbies();
        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
