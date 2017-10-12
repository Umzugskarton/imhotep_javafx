package socket.commands;

import org.json.simple.JSONObject;
import socket.ClientAPI;
import socket.ClientListener;

public class userlistCommand implements Command {
    private JSONObject request;
    private ClientListener clientListener;
    private ClientAPI clientAPI;

    public userlistCommand(ClientListener clientListener, JSONObject request){
        this.clientListener=clientListener;
        this.request =request;
        this.clientAPI = clientListener.getClientAPI();
    }
    public void exec(){
        String response = this.clientListener.getServer().getLoggedUsers();
        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
