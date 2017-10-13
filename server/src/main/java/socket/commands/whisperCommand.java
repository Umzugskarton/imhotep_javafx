package socket.commands;

import json.ServerCommands;
import org.json.simple.JSONObject;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

public class whisperCommand implements Command {
    private JSONObject request;
    private ClientListener clientListener;
    private Server server;
    private ClientAPI clientAPI;

    public whisperCommand(ClientListener clientListener, JSONObject request){
        this.clientListener=clientListener;
        this.request =request;
        this.server=clientListener.getServer();
        this.clientAPI=clientListener.getClientAPI();
    }
    public void exec() {
        String response = null;
        String receiverUsername = this.server
                .getLoggedInUsername((String) request.get("to"));
        if (receiverUsername != null) {
            String chatMessage = this.clientAPI.whisper(request, this.clientListener.getUser());
            this.server.sendTo(chatMessage, receiverUsername);
        } else {
            response = ServerCommands.userNotFoundError((String) request.get("to"));
        }

        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
