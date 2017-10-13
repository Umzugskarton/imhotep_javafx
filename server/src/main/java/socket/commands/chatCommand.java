package socket.commands;

import CLTrequests.Request;
import CLTrequests.chatRequest;
import SRVevents.chatEvent;
import org.json.simple.JSONObject;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

public class chatCommand implements Command {
    private chatRequest request;
    private ClientListener clientListener;
    private Server server;
    private ClientAPI clientAPI;

    public chatCommand(ClientListener clientListener){
        this.clientListener=clientListener;
        this.server=clientListener.getServer();
        this.clientAPI=clientListener.getClientAPI();
    }

    public void put(Request r){
        this.request =(chatRequest) r;
    }

    public void exec(){
        chatEvent chatMessage = this.clientAPI.chat(request, this.clientListener.getUser());
        this.server.sendToLoggedIn(chatMessage);
    }
}
