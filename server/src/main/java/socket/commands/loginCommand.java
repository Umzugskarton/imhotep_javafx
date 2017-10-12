package socket.commands;

import org.json.simple.JSONObject;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;


public class loginCommand implements Command {
    private JSONObject request;
    private ClientListener clientListener;
    private Server server;
    private ClientAPI clientAPI;

    public loginCommand(ClientListener clientListener, JSONObject request){
        this.clientListener=clientListener;
        this.request =request;
        this.server=clientListener.getServer();
        this.clientAPI=clientListener.getClientAPI();
    }
    public void exec(){
        JSONObject response = this.clientListener.getClientAPI().login(request, this.server.getLoggedUsers());
        if ((boolean) response.get("success")) {
            this.clientListener.setUser(this.clientAPI.getUser((String) request.get("username")));
            this.server.sendToLoggedIn(this.server.getLoggedUsers());
        }
        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
