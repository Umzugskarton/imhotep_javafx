package socket.commands;

import SRVevents.userNotFoundError;
import SRVevents.voidEvent;
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
        voidEvent response = null;
        String receiverUsername = this.server
                .getLoggedInUsername((String) request.get("to"));
        if (receiverUsername != null) {
            this.server.sendTo(this.clientAPI.whisper(request, this.clientListener.getUser()), receiverUsername);
        } else {
            userNotFoundError error = new userNotFoundError();
            error.setMsg((String) request.get("to"));
            response = error;
        }

        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
