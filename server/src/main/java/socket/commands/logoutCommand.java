package socket.commands;

import CLTrequests.Request;
import CLTrequests.logoutRequest;
import SRVevents.userListEvent;
import org.json.simple.JSONObject;
import socket.ClientListener;

public class logoutCommand implements Command {
    private logoutRequest request;
    private ClientListener clientListener;

    public logoutCommand(ClientListener clientListener){
        this.clientListener=clientListener;
    }

    public void put(Request r){
        this.request =(logoutRequest) r;
    }

    public void exec(){
        this.clientListener.getServer().sendToAll(this.clientListener.getServer().getLoggedUsers());
        this.clientListener.setUser(null);
    }
}

