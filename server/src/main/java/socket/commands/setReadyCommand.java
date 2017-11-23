package socket.commands;

import CLTrequests.Request;
import CLTrequests.setReadyRequest;
import SRVevents.setReadyEvent;
import lobby.Lobby;
import socket.ClientListener;
import user.User;

/**
 * Created by Slothan on 16.11.2017.
 */
public class setReadyCommand implements Command {

    private ClientListener clientListener;
    private setReadyRequest request;

    public setReadyCommand(ClientListener clientListener) {
        this.clientListener = clientListener;

    }

    public void put(Request r) { this.request = (setReadyRequest) r;}

    public void exec() {
        Lobby lobby = clientListener.getLobby();
        setReadyEvent setReadyEvent = lobby.setReady(clientListener.getUser());
        this.clientListener.send(setReadyEvent);
        User[] users = lobby.getUsers();
        for (User tempUser : users) {
            if (tempUser != null) {
                clientListener.getServer().sendTo(setReadyEvent, tempUser.getUsername());
            }
        }

    }



}
