package socket.commands;

import CLTrequests.Request;
import CLTrequests.loginRequest;
import SRVevents.loginEvent;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;
import user.User;


public class loginCommand implements Command {
    private loginRequest request;
    private ClientListener clientListener;
    private Server server;
    private ClientAPI clientAPI;

    public loginCommand(ClientListener clientListener){
        this.clientListener=clientListener;

        this.server=clientListener.getServer();
        this.clientAPI=clientListener.getClientAPI();
    }

    public void put(Request r){
        this.request =(loginRequest) r;
    }

    public void exec(){
        loginEvent response = this.clientListener.getClientAPI().login(request, this.server.getLoggedUsers().getUserList());
        if (response.getSuccess()) {
            User user = this.clientAPI.getUser(request.getUsername());
            this.clientListener.setUser(user);
            response.setUsername(user.getUsername());
            this.server.sendToLoggedIn(this.server.getLoggedUsers());
        }
        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
