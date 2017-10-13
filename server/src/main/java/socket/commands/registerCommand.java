package socket.commands;


import CLTrequests.Request;
import CLTrequests.registerRequest;
import SRVevents.registerEvent;
import org.json.simple.JSONObject;
import socket.ClientAPI;
import socket.ClientListener;

public class registerCommand implements Command {
    private Request bare;
    private registerRequest request;
    private ClientListener clientListener;
    private ClientAPI clientAPI;

    public registerCommand(ClientListener clientListener, Request bare){
        this.clientListener=clientListener;
        this.bare = bare;
        this.clientAPI = clientListener.getClientAPI();
    }

    public void exec(){
        this.request =(registerRequest) this.bare;
        registerEvent response = this.clientAPI.register(this.request);
        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
