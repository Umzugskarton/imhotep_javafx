package socket.commands;

import SRVevents.userListEvent;
import org.json.simple.JSONObject;
import socket.ClientAPI;
import socket.ClientListener;

public class userlistCommand implements Command {
    private ClientListener clientListener;


    public userlistCommand(ClientListener clientListener){
        this.clientListener=clientListener;

    }
    public void exec(){
        userListEvent response = this.clientListener.getServer().getLoggedUsers();
        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
