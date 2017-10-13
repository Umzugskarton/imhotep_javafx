package socket.commands;


import CLTrequests.registerRequest;
import SRVevents.registerEvent;
import org.json.simple.JSONObject;
import socket.ClientAPI;
import socket.ClientListener;

public class registerCommand implements Command {
    private registerRequest request;
    private ClientListener clientListener;
    private ClientAPI clientAPI;

    public registerCommand(ClientListener clientListener, registerRequest request){
        this.clientListener=clientListener;
        this.request =request;
        this.clientAPI = clientListener.getClientAPI();
    }

    public void exec(){
        registerEvent response = this.clientAPI.register(this.request);
        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
