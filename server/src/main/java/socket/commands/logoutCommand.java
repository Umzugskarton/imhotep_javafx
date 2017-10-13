package socket.commands;

import org.json.simple.JSONObject;
import socket.ClientListener;

public class logoutCommand implements Command {
    private JSONObject request;
    private ClientListener clientListener;

    public logoutCommand(ClientListener clientListener){
        this.clientListener=clientListener;
    }

    public void exec(){
        this.clientListener.setUser(null);
    }
}
