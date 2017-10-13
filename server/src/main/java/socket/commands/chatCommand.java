package socket.commands;

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

    public chatCommand(ClientListener clientListener, chatRequest request){
        this.clientListener=clientListener;
        this.request =request;
        this.server=clientListener.getServer();
        this.clientAPI=clientListener.getClientAPI();
    }
    public void exec(){
        chatEvent chatMessage = this.clientAPI.chat(request, this.clientListener.getUser());
        this.server.sendToLoggedIn(chatMessage);
    }
}
