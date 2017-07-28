package socket.commands;


import org.json.simple.JSONObject;
import socket.ClientAPI;
import socket.ClientListener;

public class registerCommand implements Command {
    private JSONObject request;
    private ClientListener clientListener;
    private ClientAPI clientAPI;

    public registerCommand(ClientListener clientListener, JSONObject request){
        this.clientListener=clientListener;
        this.request =request;
        this.clientAPI = clientListener.getClientAPI();
    }

    public void exec(){
        JSONObject response = this.clientAPI.register(this.request);
        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
