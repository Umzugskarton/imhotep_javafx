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
    private Request bare;
    private ClientListener clientListener;
    private Server server;
    private ClientAPI clientAPI;

    public chatCommand(ClientListener clientListener, Request bare){
        this.clientListener=clientListener;
        this.bare = bare;
        this.server=clientListener.getServer();
        this.clientAPI=clientListener.getClientAPI();
    }
    public void exec(){
        this.request =(chatRequest) this.bare;
        chatEvent chatMessage = this.clientAPI.chat(request, this.clientListener.getUser());
        this.server.sendToLoggedIn(chatMessage);
    }
}
