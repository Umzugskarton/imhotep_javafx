package socket.commands;

import CLTrequests.IRequest;
import CLTrequests.setReadyRequest;
import SRVevents.setReadyEvent;
import lobby.Lobby;
import socket.ClientListener;
import user.User;

/**
 * Created by Slothan on 16.11.2017.
 */
public class SetReadyCommand implements Command {

    private ClientListener clientListener;
    private setReadyRequest request;

    public SetReadyCommand(ClientListener clientListener) {
        this.clientListener = clientListener;

    }

    public void put(IRequest r) { this.request = (setReadyRequest) r;}

    public void exec() {
        Lobby lobby = clientListener.getLobbyByID(request.getLobbyId());
        setReadyEvent setReadyEvent = lobby.setReady(clientListener.getUser());
        User[] users = lobby.getUsers();
        for (User tempUser : users) {
            if (tempUser != null) {
                clientListener.getServer().sendTo(setReadyEvent, tempUser.getUsername());
            }
        }

    }



}
