package socket.commands;

import requests.IRequest;
import requests.setReadyRequest;
import events.app.lobby.SetReadyToPlayEvent;
import lobby.Lobby;
import socket.ClientListener;
import data.user.User;

public class SetReadyCommand implements Command {

    private ClientListener clientListener;
    private setReadyRequest request;

    public SetReadyCommand(ClientListener clientListener) {
        this.clientListener = clientListener;

    }

    public void put(IRequest r) { this.request = (setReadyRequest) r;}

    public void exec() {
        Lobby lobby = clientListener.getLobbyByID(request.getLobbyId());
        SetReadyToPlayEvent setReadyEvent = lobby.setReady(clientListener.getUser());
        User[] users = lobby.getUsers();
        for (User tempUser : users) {
            if (tempUser != null) {
                clientListener.getServer().sendTo(setReadyEvent, tempUser.getUsername());
            }
        }

    }



}
