package commands.chat;

import commands.Command;
import events.Event;
import events.app.chat.UserNotFoundErrorEvent;
import requests.IRequest;
import requests.chat.WhisperRequest;
import socket.ClientAPI;
import socket.ClientListener;
import socket.Server;

public class WhisperCommand implements Command {

    private WhisperRequest request;
    private ClientListener clientListener;
    private Server server;
    private ClientAPI clientAPI;

    public WhisperCommand(ClientListener clientListener) {
        this.clientListener = clientListener;
        this.server = clientListener.getServer();
        this.clientAPI = clientListener.getClientAPI();
    }

    public void put(IRequest r) {
        this.request = (WhisperRequest) r;
    }

    public void exec() {
        Event response = null;
        String receiverUsername = this.server
                .getLoggedInUsername(request.getTo());
        if (receiverUsername != null) {
            this.server
                    .sendTo(this.clientAPI.whisper(request, this.clientListener.getUser()), receiverUsername);
        } else {
            UserNotFoundErrorEvent error = new UserNotFoundErrorEvent();
            error.setMsg(request.getTo());
            response = error;
        }

        if (response != null) {
            this.clientListener.send(response);
        }
    }
}
